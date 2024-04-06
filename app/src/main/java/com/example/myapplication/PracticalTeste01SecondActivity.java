package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTeste01SecondActivity extends AppCompatActivity {

    Button okButton, cancelButton;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_practical_teste01_second);

        okButton = findViewById(R.id.buttonOK);
        cancelButton = findViewById(R.id.buttonCancel);

        editText = findViewById(R.id.textView);

        Intent intent = getIntent();

        int sum = intent.getIntExtra(Constants.Sum, 0);
        editText.setText(String.valueOf(sum));

        okButton.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        });

        cancelButton.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED, returnIntent);
            finish();
        });
    }
}