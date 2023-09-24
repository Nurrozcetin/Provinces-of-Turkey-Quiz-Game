package com.example.isim_sehir_hayvan_oyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnBasicGame;
    private Button btnTimedGame;
    private Button btnExit;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasicGame = (Button)findViewById(R.id.btnBasicGame);
        btnTimedGame = (Button)findViewById(R.id.btnTimedGame);
        btnExit = (Button)findViewById(R.id.btnExit);

    }

    public void btnGameChoose(View v) {
        int id = v.getId();
        if(id == R.id.btnBasicGame) {
            trans("Basic Game");
        }
        else if( id == R.id.btnTimedGame) {
            trans("Timed Game");
        }
        else if(id == R.id.btnExit) {
            exit();
        }
    }

    private void trans(String activityName) {
        if(activityName.equals("Basic Game")){
            intent = new Intent(this, basicGameActivity.class);
        }
        else {
            intent = new Intent(this, timedGame.class);
        }
        startActivity(intent);
    }
    private void exit(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        exit();
    }
}