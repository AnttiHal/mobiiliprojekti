package com.example.harjoitustyo001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button1A;
    private Button button1B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1A = findViewById(R.id.button1A);
        button1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensuunnittelu();
            }
        });

        button1B = findViewById(R.id.button1B);
        button1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openohjelmointi();
            }
        });
    }

    public void opensuunnittelu(){
        Intent intent = new Intent(this, Suunnittelu.class);
        startActivity(intent);
    }

    public void openohjelmointi(){
        Intent intent = new Intent(this, Ohjelmointi.class);
        startActivity(intent);
    }
}
