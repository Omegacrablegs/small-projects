package com.a11507057.paddy.digital_entropy;


import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.DialogInterface;



public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    protected void gameScreen(View view){
        Intent i = new Intent(MainMenuActivity.this,GameBoardActivity.class);
        startActivity(i);
    }

    protected void settingScreen(View view){
        Intent i = new Intent(MainMenuActivity.this,SettingsActivity.class);
        startActivity(i);
    }

    protected void howToPlayScreen(View view){
        Intent i = new Intent(MainMenuActivity.this,HowToPlayActivity.class);
        startActivity(i);
    }

    protected void quitGame(View view){

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainMenuActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit ??");
        builder.setPositiveButton("Yes. Exit now!", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();

            }
        });

        builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}

