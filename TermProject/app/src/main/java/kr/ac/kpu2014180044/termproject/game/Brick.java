package kr.ac.kpu2014180044.termproject.game;

import android.graphics.Canvas;

import kr.ac.kpu2014180044.termproject.R;
import kr.ac.kpu2014180044.termproject.framework.GameBitmap;
import kr.ac.kpu2014180044.termproject.framework.GameObject;

public class Brick implements GameObject {
    private float x, y;
    private float tx, ty;
    private int correct_dir = 0;
    private GameBitmap brickBitmap;

    public Brick(float x, float y, int correct_dir) {
        this.x = x;
        this.y = y;
        this.correct_dir = correct_dir;
        this.brickBitmap = new GameBitmap(R.mipmap.cookierun_platform_120x40);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        brickBitmap.draw(canvas, x, y);
    }
}
