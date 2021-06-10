package kr.ac.kpu2014180044.termproject.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.kpu2014180044.termproject.R;
import kr.ac.kpu2014180044.termproject.framework.AnimationGameBitmap;
import kr.ac.kpu2014180044.termproject.framework.GameBitmap;
import kr.ac.kpu2014180044.termproject.framework.GameObject;
import kr.ac.kpu2014180044.termproject.framework.Sound;

public class Player implements GameObject {
    private static final String TAG = Player.class.getSimpleName();
    private static final float FRAMES_PER_SECOND = 8.0f;
    private float x;
    private float y;
    private GameBitmap bitmap_left;
    private GameBitmap bitmap_right;
    private int dir;
    private int currentStair = 0;
    private ProgressBar progressBar;
    private float progressGauge = 100.f;
    private TextView scoreTextView;
    private boolean isEndGame = false;
    private float originX;
    private float originY;
    private int originDir;
    private Button retryButton;


    public Player(float x, float y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;

        this.originDir = this.dir;
        this.originX = this.x;
        this.originY = this.y;

        int player_left = R.mipmap.player_left;
        int player_right = R.mipmap.player_right;
        this.bitmap_left = new AnimationGameBitmap(player_left, FRAMES_PER_SECOND, 0);
        this.bitmap_right = new AnimationGameBitmap(player_right, FRAMES_PER_SECOND, 0);

        progressBar = null;
        scoreTextView = null;
        retryButton = null;

        Sound.play(R.raw.map_bgm);
    }

    public void setupDir() {
        this.dir *= -1.0f;
    }
    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
    public void setScoreTextView(TextView scoreTextView) {
        this.scoreTextView = scoreTextView;
    }
    public void setRetryButton(Button retryButton) {
        this.retryButton = retryButton;
    }
    public void reset() {
        this.x = this.originX;
        this.y = this.originY;
        this.dir = this.originDir;
        this.currentStair = 0;
        this.isEndGame = false;
        this.progressGauge = 100.f;
        progressBar.setProgress((int) progressGauge);

        this.progressBar = null;
        this.scoreTextView = null;
        this.retryButton = null;

        MainGame mainGame = MainGame.get();
        mainGame.score.reset();
    }

    @Override
    public void update() {
        if (!isEndGame && null != progressBar){
            progressGauge -= 0.016f * 15.0f;
        }
        if (progressGauge < 0){
            progressGauge = 0;
            isEndGame = true;
        }
        if (null != progressBar) {
            progressBar.setProgress((int) progressGauge);
        }

        if (null != scoreTextView) {
            MainGame mainGame = MainGame.get();
            scoreTextView.setText("Score:" + mainGame.score.getScore());
        }

        if (isEndGame && null != scoreTextView && null != retryButton) {
            scoreTextView.setBackgroundColor(Color.argb(255,255,255,255));
            scoreTextView.setTextColor(Color.argb(255,255,255,255));

            retryButton.setEnabled(true);
            retryButton.setBackgroundColor(Color.argb(255,255,255,255));
            retryButton.setTextColor(Color.argb(255,0,0,0));
        }
        else {
            if (null != scoreTextView) {
                scoreTextView.setBackgroundColor(Color.argb(0,255,255,255));
                scoreTextView.setTextColor(Color.argb(0,255,255,255));
            }

            if (null != scoreTextView) {
                retryButton.setEnabled(false);
                retryButton.setBackgroundColor(Color.argb(0,255,255,255));
                retryButton.setTextColor(Color.argb(0,0,0,0));
            }
        }
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

        if (curBrick.getDir() == this.dir && !isEndGame) {
            for (GameObject bricks: brickList) {
                bricks.downStairs();
            }

            Brick nextBrick = (Brick) brickList.get(currentStair + 1);
            this.x = nextBrick.getPosX();
            this.y = nextBrick.getPosY() - 90.0f;
            ++currentStair;

            mainGame.score.addScore(1);
            ++progressGauge;

            Sound.play(R.raw.up_stairs);
        }
        else {
            isEndGame = true;
            Sound.play(R.raw.game_over);
        }
    }
}
