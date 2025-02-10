package com.example.carcounter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int countPerson1 = 0; // Счётчик для Олега
    int countPerson2 = 0; // Счётчик для Лии

    TextView scorePerson1; // Поле для счёта Олега
    TextView scorePerson2; // Поле для счёта Лии
    TextView scoreDifference; // Поле для отображения разницы в счёте

    SharedPreferences sharedPreferences; // Для сохранения данных
    private static final String PREFS_NAME = "ScorePrefs";
    private static final String KEY_PERSON1 = "Person1Score";
    private static final String KEY_PERSON2 = "Person2Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Инициализация полей для счёта
        scorePerson1 = findViewById(R.id.score_person1);
        scorePerson2 = findViewById(R.id.score_person2);
        scoreDifference = findViewById(R.id.score_difference);

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadScores(); // Загружаем сохранённые данные

        // Кнопки для Олега
        Button plusPerson1 = findViewById(R.id.button_plus_person1);
        Button minusPerson1 = findViewById(R.id.button_minus_person1);

        // Кнопки для Лии
        Button plusPerson2 = findViewById(R.id.button_plus_person2);
        Button minusPerson2 = findViewById(R.id.button_minus_person2);

        // Обработчики нажатий для Олега
        plusPerson1.setOnClickListener(v -> {
            countPerson1++;
            saveScores();
            updateScores();
        });

        minusPerson1.setOnClickListener(v -> {
            if (countPerson1 > 0) countPerson1--;
            saveScores();
            updateScores();
        });

        // Обработчики нажатий для Лии
        plusPerson2.setOnClickListener(v -> {
            countPerson2++;
            saveScores();
            updateScores();
        });

        minusPerson2.setOnClickListener(v -> {
            if (countPerson2 > 0) countPerson2--;
            saveScores();
            updateScores();
        });

        updateScores(); // Показываем начальный счёт
    }

    // Метод для обновления счёта
    private void updateScores() {
        scorePerson1.setText("Олег: " + countPerson1);
        scorePerson2.setText("Лия: " + countPerson2);

        // Вычисление разницы и определение лидера
        if (countPerson1 > countPerson2) {
            int difference = countPerson1 - countPerson2;
            scoreDifference.setText("Впереди Олег на " + difference);
        } else if (countPerson2 > countPerson1) {
            int difference = countPerson2 - countPerson1;
            scoreDifference.setText("Впереди Лия на " + difference);
        } else {
            scoreDifference.setText("Счёт равный");
        }
    }

    // Сохранение данных
    private void saveScores() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_PERSON1, countPerson1);
        editor.putInt(KEY_PERSON2, countPerson2);
        editor.apply(); // Применение изменений
    }

    // Загрузка сохранённых данных
    private void loadScores() {
        countPerson1 = sharedPreferences.getInt(KEY_PERSON1, 0);
        countPerson2 = sharedPreferences.getInt(KEY_PERSON2, 0);
    }
}
