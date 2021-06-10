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
    private float originX;
    private float originY;

    public Brick(float x, float y, int correct_dir) {
        this.x = x;
        this.y = y;
        this.originX = this.x;
        this.originY = this.y;

        this.correct_dir = correct_dir;
        this.brickBitmap = new GameBitmap(R.mipmap.cookierun_platform_120x40);
    }

    float getPosX() {
        return this.x;
    }
    float getPosY() {
        return this.y;
    }
    int getDir() {
        return this.correct_dir;
    }

    public void downStairs() {
        this.y += 75.0f;
    }

    public void reset() {
        this.x = this.originX;
        this.y = this.originY;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        brickBitmap.draw(canvas, x, y);
    }
}
