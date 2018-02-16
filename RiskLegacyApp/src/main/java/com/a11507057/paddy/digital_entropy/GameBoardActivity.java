package com.a11507057.paddy.digital_entropy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class GameBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        TextView  user = findViewById(R.id.HumanPlayerText);
        SharedPreferences sharedPref = getSharedPreferences("username", Context.MODE_PRIVATE);
        String username = sharedPref.getString("username","Player1");
        user.setText("Purple");
        playGame();
    }

    public void playGame(){
        Toast.makeText(this,"Game Starting",Toast.LENGTH_LONG).show();
        GameRun start = new GameRun();
        start.startGame();
    }

}
