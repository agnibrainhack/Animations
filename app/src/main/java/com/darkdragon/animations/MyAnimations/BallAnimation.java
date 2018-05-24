package com.darkdragon.animations.MyAnimations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 24-05-2018.
 */


public class BallAnimation extends android.support.v7.widget.AppCompatTextView {
    private Ball ball;
    private Paint paint;
   private class Ball{
       int x = 5,y = 5,rad = 80,dx = 5,dy = 5;
   }


    public BallAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        ball = new Ball();
        paint = new Paint();
        paint.setColor(Color.RED);
        Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();

        moveball()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext( integers -> invalidate())
                .subscribe();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(ball.x, ball.y, ball.rad, paint);
    }

    public Flowable<Integer[]> moveball(){
       return  Flowable.create( sub ->{
           while (true){
               if(ball.x <0) ball.dx = 5;
               if(ball.x >= getWidth()) ball.dx = -5;
               if(ball.y <0) ball.dy = 5;
               if(ball.y > getHeight()) ball.dy = -5;

              ball.x += ball.dx;
              ball.y += ball.dy;
              Thread.sleep(20);
              sub.onNext(new Integer[]{ball.x, ball.y});
           }
       }, BackpressureStrategy.BUFFER);
    }
}
