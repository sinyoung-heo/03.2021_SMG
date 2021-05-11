package kr.ac.kpu2014180044.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton dirChangeButton;
    private ImageButton upStairButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dirChangeButton = findViewById(R.id.DirChange);
        upStairButton = findViewById(R.id.UpStairs);

        dirChangeButton.setImageResource(R.mipmap.dir_change);
        upStairButton.setImageResource(R.mipmap.up_stairs);
    }
}