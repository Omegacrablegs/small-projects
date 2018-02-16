package com.a11507057.paddy.digital_entropy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    public EditText usernameInput;
    public String userName = "Player1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button userNameChange = findViewById(R.id.changeUserName);
        Button back = findViewById(R.id.backSettings);


        userNameChange.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        setUserName(v);
                    }
                }
        );

        back.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        backToMenu(v);
                    }
                }
        );
    }

    protected void setUserName(View v){
        usernameInput = findViewById(R.id.userName);
        //Save the userName using Shared Preferences
        SharedPreferences sharedPref = getSharedPreferences("userName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username",usernameInput.getText().toString());
        editor.apply();

        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
    }

    protected void backToMenu(View v){
        Intent i = new Intent(this,MainMenuActivity.class);
        startActivity(i);
    }




}
