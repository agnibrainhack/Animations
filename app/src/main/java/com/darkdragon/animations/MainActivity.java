package com.darkdragon.animations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.darkdragon.animations.MyAnimations.DrawingView;

public class MainActivity extends AppCompatActivity {
    Button btn, btn2;
    TextView textView;
    DrawingView drawingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.hit);
        btn2 = findViewById(R.id.next);
        drawingView = findViewById(R.id.draw);
        btn.setOnClickListener(v -> sucker());
        btn2.setOnClickListener(v -> nextfire());
    }

    private void nextfire() {
        Intent i = new Intent(MainActivity.this, NextActivity.class);
        startActivity(i);
    }

    private void sucker() {
        drawingView.clearCanvas();
    }
}
