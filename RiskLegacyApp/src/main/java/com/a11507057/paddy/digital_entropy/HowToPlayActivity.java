package com.a11507057.paddy.digital_entropy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        Button back = findViewById(R.id.backHowToPlay);

        back.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        backToMenu(v);
                    }
                }
        );
    }

    protected void backToMenu(View v){
        Intent i = new Intent(this,MainMenuActivity.class);
        startActivity(i);
    }
}
