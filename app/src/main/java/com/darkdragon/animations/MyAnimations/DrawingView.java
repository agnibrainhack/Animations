package com.darkdragon.animations.MyAnimations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hp on 24-05-2018.
 */

public class DrawingView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();
    private boolean remove = false;
    Canvas c;


    public DrawingView(Context c, AttributeSet set) {
        super(c, set);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(8f);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {


            canvas.drawPath(path, paint);



    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Makes our view repaint and call onDraw
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Set a new starting point
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                // Connect the points
                path.lineTo(eventX, eventY);
                break;
            default:
                return false;
        }
        invalidate();

        return true;

    }
    public void clearCanvas(){

        //canvas.drawColor(0, Mode.CLEAR);

        path.reset();
        invalidate();
    }
}