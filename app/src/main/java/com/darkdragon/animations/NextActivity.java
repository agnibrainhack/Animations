package com.darkdragon.animations;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by hp on 24-05-2018.
 */

public class NextActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_next);
        button = findViewById(R.id.move);
        button.setOnClickListener( v -> fire());
    }

    private void fire() {

        Intent i = new Intent(NextActivity.this, ThirdActivity.class);
        startActivity(i);
    }
}
