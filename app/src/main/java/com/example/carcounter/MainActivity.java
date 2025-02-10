package com.example.carcounter;

import android.os.Bundle;
import android.view.View;
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

        // Кнопки для Олега
        Button plusPerson1 = findViewById(R.id.button_plus_person1);
        Button minusPerson1 = findViewById(R.id.button_minus_person1);

        // Кнопки для Лии
        Button plusPerson2 = findViewById(R.id.button_plus_person2);
        Button minusPerson2 = findViewById(R.id.button_minus_person2);

        // Обработчики нажатий для Олега
        plusPerson1.setOnClickListener(v -> {
            countPerson1++;
            Toast.makeText(this, "Олег: " + countPerson1, Toast.LENGTH_SHORT).show();
            updateScores();
        });

        minusPerson1.setOnClickListener(v -> {
            if (countPerson1 > 0) countPerson1--;
            Toast.makeText(this, "Олег: " + countPerson1, Toast.LENGTH_SHORT).show();
            updateScores();
        });

        // Обработчики нажатий для Лии
        plusPerson2.setOnClickListener(v -> {
            countPerson2++;
            Toast.makeText(this, "Лия: " + countPerson2, Toast.LENGTH_SHORT).show();
            updateScores();
        });

        minusPerson2.setOnClickListener(v -> {
            if (countPerson2 > 0) countPerson2--;
            Toast.makeText(this, "Лия: " + countPerson2, Toast.LENGTH_SHORT).show();
            updateScores();
        });

        updateScores(); // Показываем начальный счёт
    }

    // Метод для обновления счёта
    private void updateScores() {
        scorePerson1.setText("Олег: " + countPerson1);
        scorePerson2.setText("Лия: " + countPerson2);
    }
}
