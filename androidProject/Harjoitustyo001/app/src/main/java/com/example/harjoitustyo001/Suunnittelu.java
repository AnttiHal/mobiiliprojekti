package com.example.harjoitustyo001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.harjoitustyo001.R;
import com.example.harjoitustyo001.Sijainti;

public class Suunnittelu extends AppCompatActivity {
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suunnittelu);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openkartta();
            }
        });
    }

    public void openkartta(){
        Intent intent = new Intent(this, Sijainti.class);
        startActivity(intent);
        double x1 = 61.6795217;
        double y1 = 27.2591599;
        Intent passdata_intent = new Intent(this, Sijainti.class);
        startActivity(passdata_intent);
        passdata_intent.putExtra("x1",x1);
        passdata_intent.putExtra("y1",y1);
        startActivity(passdata_intent);
    }
}
