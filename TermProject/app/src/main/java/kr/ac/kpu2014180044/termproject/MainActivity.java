package kr.ac.kpu2014180044.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import kr.ac.kpu2014180044.termproject.framework.GameObject;
import kr.ac.kpu2014180044.termproject.game.Brick;
import kr.ac.kpu2014180044.termproject.game.MainGame;
import kr.ac.kpu2014180044.termproject.game.Player;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageButton dirChangeButton;
    private ImageButton upStairButton;
    MainGame mainGame;
    Player player;
    private int startIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGame = MainGame.get();
        player = mainGame.getPlayer();

        dirChangeButton = findViewById(R.id.DirChange);
        upStairButton = findViewById(R.id.UpStairs);

        dirChangeButton.setImageResource(R.mipmap.dir_change);
        dirChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "onBtnClick dirChange");
                player = mainGame.getPlayer();
                player.setupDir();
            }
        });

        upStairButton.setImageResource(R.mipmap.up_stairs);
        upStairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "onBtn upStairs");
                // Player
                player = mainGame.getPlayer();
                player.upStairs();
            }
        });
    }


}