package com.yaelya.arkanoid;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//public class MainActivity extends Activity implements View.OnClickListener {
public class MainActivity extends Activity {

        private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("jjj","11s");
        setContentView(R.layout.activity_main);
       gameView= (GameView) findViewById(R.id.gameViewID);
        Log.d("jjj","222");
       startGame();

       // gameView.setOnClickListener(this);

    }

    public void startGame()
    {
        Log.d("jjj","sssss");
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                gameView.animationLoop();
            }
        });
        thread.start();
        Log.d("jjj","tttts");

        gameView.setEnabled(false);
    }

   /* @Override
    public void onClick(View view)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                gameView.animationLoop();
            }
        });
        thread.start();
        gameView.setEnabled(false);
    }*/
}
