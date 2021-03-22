package kr.ac.kpu2014180044.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private TextView mainTextView;
    private ImageButton nextImageButton;
    private ImageButton prevImageButton;
    private ImageView mainImageView;

    private int currentImageIdx;
    private int maxImageSize;
    private Vector<Integer> mainImageList;
    private Vector<Integer> nextImageList;
    private Vector<Integer> prevImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImageList = new Vector();
        nextImageList = new Vector();
        prevImageList = new Vector();

        mainTextView = findViewById(R.id.MainTextView);
        nextImageButton = findViewById(R.id.imageButtonNext);
        prevImageButton = findViewById(R.id.imageButtonPrev);
        mainImageView = findViewById(R.id.MainImageView);

        currentImageIdx = 0;
        maxImageSize = 5;
        mainImageList.add(R.mipmap.cat1);
        mainImageList.add(R.mipmap.cat2);
        mainImageList.add(R.mipmap.cat3);
        mainImageList.add(R.mipmap.cat4);
        mainImageList.add(R.mipmap.cat5);

        nextImageList.add(R.mipmap.next);
        nextImageList.add(R.mipmap.next_p);
        nextImageList.add(R.mipmap.next_d);

        prevImageList.add(R.mipmap.prev);
        prevImageList.add(R.mipmap.prev_p);
        prevImageList.add(R.mipmap.prev_d);

        nextImageButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (currentImageIdx < maxImageSize - 1) {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN :
//                            ++currentImageIdx;
                            nextImageButton.setImageResource(nextImageList.get(1));

                            if (currentImageIdx >= 0)
                                prevImageButton.setImageResource(prevImageList.get(0));

                            break;
                        case MotionEvent.ACTION_UP   :
                            ++currentImageIdx;
                            nextImageButton.setImageResource(nextImageList.get(0));
                            mainImageView.setImageResource(mainImageList.get(currentImageIdx));

                            if (currentImageIdx == maxImageSize - 1)
                                nextImageButton.setImageResource(nextImageList.get(2));
                        break;
                    }
                }

                String str = String.format("%d/%d",currentImageIdx+1,maxImageSize);
                mainTextView.setText(str);

                return false;
            }
        });

        prevImageButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (currentImageIdx > 0) {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN :
//                            --currentImageIdx;
                            prevImageButton.setImageResource(prevImageList.get(1));

                            if (currentImageIdx < maxImageSize)
                                nextImageButton.setImageResource(nextImageList.get(0));

                            break;
                        case MotionEvent.ACTION_UP   :
                            --currentImageIdx;
                            prevImageButton.setImageResource(prevImageList.get(0));
                            mainImageView.setImageResource(mainImageList.get(currentImageIdx));

                            if(currentImageIdx == 0)
                                prevImageButton.setImageResource(prevImageList.get(2));
                        break;
                    }
                }

                String str = String.format("%d/%d",currentImageIdx+1,maxImageSize);
                mainTextView.setText(str);

                return false;
            }
        });
    }

}