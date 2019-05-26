package com.example.user.flyingfish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.nio.file.attribute.BasicFileAttributeView;

public class GameOverActivity extends AppCompatActivity {
    private Button startgameagain;
    private TextView displaysxore;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        score = getIntent().getExtras().get("score").toString();
        startgameagain=(Button) findViewById(R.id.play_again_btn);
        displaysxore=(TextView) findViewById(R.id.displayscore);
        startgameagain.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent mainIntent;
                mainIntent = new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });
        displaysxore.setText("score:"+score);
    }
}
