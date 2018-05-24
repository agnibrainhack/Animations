package com.darkdragon.animations;


import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by hp on 24-05-2018.
 */

public class ThirdActivity extends AppCompatActivity{
    Button btn1, btn2, btn3;
    private ImageView myImage;
    private int mCurrRotation = 0;
    private int rotationAngle = 45;
    private String TAG = ThirdActivity.class.getSimpleName();
    float initialX, initialY;
    private FragmentScroll fragmentScroll;
    private View mover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity3);
        myImage = findViewById(R.id.image);
        btn1 = findViewById(R.id.t1);
        btn2 = findViewById(R.id.t2);
        btn3 = findViewById(R.id.t3);
        btn1.setOnClickListener(v -> animate());
        btn2.setOnClickListener(v -> fire());
        btn3.setOnClickListener(v -> fire2());
        mover = findViewById(R.id.fragment_container);
        fragmentScroll = new FragmentScroll();


        FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, fragmentScroll, "HELLO");
        fragmentTransaction.commit();






        mover.setOnTouchListener(new View.OnTouchListener()
        {
            PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
            PointF StartPT = new PointF(); // Record Start Position of 'img'


            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_MOVE :
                        mover.setX((int)(StartPT.x + event.getX() - DownPT.x));
                        mover.setY((int)(StartPT.y + event.getY() - DownPT.y));
                        StartPT.set( mover.getX(), mover.getY() );
                        break;
                    case MotionEvent.ACTION_DOWN :
                        DownPT.set( event.getX(), event.getY() );
                        StartPT.set( mover.getX(), mover.getY() );
                        break;
                    case MotionEvent.ACTION_UP :
                        // Nothing have to do
                        break;
                    default :
                        break;
                }
                return true;
            }


        });






    }

    private void fire() {
//        Intent intent = new Intent(this, FourthActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_up, R.anim.stay);

        Animation bottomUp = AnimationUtils.loadAnimation(getBaseContext(),
                R.anim.slide_in_up);
        ViewGroup hiddenPanel = (ViewGroup)findViewById(R.id.hidden_layer);
        hiddenPanel.startAnimation(bottomUp);
        hiddenPanel.setVisibility(View.VISIBLE);
    }

    private void fire2() {
//        Intent intent = new Intent(this, FourthActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_up, R.anim.stay);

        Animation bottomDown = AnimationUtils.loadAnimation(getBaseContext(),
                R.anim.slide_down);
        ViewGroup hiddenPanel = (ViewGroup)findViewById(R.id.hidden_layer);
        hiddenPanel.startAnimation(bottomDown);
        hiddenPanel.setVisibility(View.INVISIBLE);
    }


    private void animate(){
        float fromRotation = mCurrRotation;
        float toRotation = mCurrRotation += rotationAngle;

        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation a = new TranslateAnimation(
                Animation.ABSOLUTE,mCurrRotation, Animation.ABSOLUTE,200,
                Animation.ABSOLUTE,mCurrRotation, Animation.ABSOLUTE,200);
        a.setDuration(1000);
        Animation animation = new RotateAnimation(
                fromRotation,
                toRotation,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        animationSet.addAnimation(a);
        animationSet.addAnimation(animation);
        animationSet.setDuration(1000);
        animationSet.setFillAfter(true);
        myImage.startAnimation(animationSet);
    }








}
