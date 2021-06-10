package kr.ac.kpu2014180044.termproject.framework;

import android.graphics.Canvas;

public interface GameObject {
    public void update();
    public void draw(Canvas canvas);

    void downStairs();

    void reset();
}
