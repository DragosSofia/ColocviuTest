package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private boolean alreadyStarted = false;

    private static class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.TAG, intent.getStringExtra(Constants.MediaAritmetica));
        }

    }

    private MyBroadcastReceiver myBroadcastReceiver;
    private IntentFilter intentFilter;

    Button pressMe1, pressMe2, nextScreen;
    EditText editText1, editText2;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pressMe1 = findViewById(R.id.press_me_1);
        pressMe2 = findViewById(R.id.press_me_2);
        nextScreen = findViewById(R.id.navigate_to_second_activity);

        editText1 = findViewById(R.id.text1);
        editText2 = findViewById(R.id.text2);

//        editText1.setText("0");
//        editText2.setText("0");

        //add a click listner to pressme1 button to increment editText1
        pressMe1.setOnClickListener(v -> {
            String text = editText1.getText().toString();
            String text2 = editText2.getText().toString();
            int number1 = Integer.parseInt(text);
            int number2 = Integer.parseInt(text2);
            number1++;
            editText1.setText(String.valueOf(number1));
            if(number2 + number1 > 5 && !alreadyStarted){
                alreadyStarted = true;
                Intent intent = new Intent(MainActivity.this, PracticalTest01Service.class);
                intent.putExtra(Constants.editText1, number1);
                intent.putExtra(Constants.editText2, number2);
                startForegroundService(intent);
            }
        });

        myBroadcastReceiver = new MyBroadcastReceiver();

        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_STRING);

        pressMe2.setOnClickListener(v -> {
            String text = editText2.getText().toString();
            int number = Integer.parseInt(text);
            number++;
            editText2.setText(String.valueOf(number));
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Log.d(Constants.TAG, "onCreate: Result OK");
                        Toast.makeText(this, "The activity returned with OK  " , Toast.LENGTH_LONG).show();
                    }else if(result.getResultCode() == RESULT_CANCELED){
                        Log.d(Constants.TAG, "onCreate: Result Canceled");
                        Toast.makeText(this, "The activity returned with Camcel  " , Toast.LENGTH_LONG).show();
                    }
                });


        nextScreen.setOnClickListener(v -> {
            Log.d(Constants.TAG, "onClick: Navigating to Second Activity");
            Intent intent = new Intent(MainActivity.this, PracticalTeste01SecondActivity.class);

            //send the sum of values of the editTexts to the second activity
            intent.putExtra(Constants.Sum,  (Integer.parseInt(editText1.getText().toString()) + Integer.parseInt(editText2.getText().toString())));

            activityResultLauncher.launch(intent);
        });
    }

    //Implementați un mecanism pentru salvarea / restaurarea valorii din câmpurile text, dacă
    //sistemul de operare Android distruge activitatea spre a asigura necesarul de resurse.


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(Constants.TAG, "onSaveInstanceState: ");
        outState.putString(Constants.editText1, editText1.getText().toString());
        Log.d(Constants.TAG, "onSaveInstanceState: editText1 " + editText1.getText().toString());
        outState.putString(Constants.editText2, editText2.getText().toString());
    }
//
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.containsKey(Constants.editText1) && savedInstanceState.containsKey(Constants.editText2)){
            editText1.setText(savedInstanceState.getString(Constants.editText1));
            editText2.setText(savedInstanceState.getString(Constants.editText2));
            Log.d(Constants.TAG, "onRestoreInstanceState: Found Restore Values");
        }else{
            editText1.setText("0");
            editText2.setText("0");
            Log.d(Constants.TAG, "onRestoreInstanceState: No Restore Values Found");
        }
    }
}