package kr.ac.kpu2014180044.termproject.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.kpu2014180044.termproject.MainActivity;
import kr.ac.kpu2014180044.termproject.R;
import kr.ac.kpu2014180044.termproject.framework.AnimationGameBitmap;
import kr.ac.kpu2014180044.termproject.framework.GameBitmap;
import kr.ac.kpu2014180044.termproject.framework.GameObject;

public class Player implements GameObject {
    private static final String TAG = Player.class.getSimpleName();
    private static final float FRAMES_PER_SECOND = 8.0f;
    private float x;
    private float y;
    private GameBitmap bitmap_left;
    private GameBitmap bitmap_right;
    private int dir;
    private int currentStair = 0;

    public Player(float x, float y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;

        int player_left = R.mipmap.player_left;
        int player_right = R.mipmap.player_right;
        this.bitmap_left = new AnimationGameBitmap(player_left, FRAMES_PER_SECOND, 0);
        this.bitmap_right = new AnimationGameBitmap(player_right, FRAMES_PER_SECOND, 0);
    }

    public void setupDir() { this.dir *= -1.0f;}

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        if (this.dir == MainGame.DIR_LEFT) {
            bitmap_left.draw(canvas, x, y);
        }
        else if (this.dir == MainGame.DIR_RIGHT) {
            bitmap_right.draw(canvas, x, y);
        }
    }

    @Override
    public void downStairs() {
    }

    public void upStairs() {
        MainGame mainGame = MainGame.get();
        ArrayList<GameObject> brickList = mainGame.getLayers().get(3);
        Brick curBrick = (Brick) brickList.get(currentStair);

        Log.d(TAG, "curBrick : " + curBrick.getDir() + "  Player : " + this.dir);

        if (curBrick.getDir() == this.dir) {
            for (GameObject bricks: brickList) {
                bricks.downStairs();
            }

            Brick nextBrick = (Brick) brickList.get(currentStair + 1);
            this.x = nextBrick.getPosX();
            this.y = nextBrick.getPosY() - 90.0f;
            ++currentStair;
        }
    }
}
