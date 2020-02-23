package com.example.revolverview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CircularRevolver extends View {

    float angleFromTop;
    float mainCircleX;
    float mainCircleY;
    float mainCircleRad;
    float smallCircleRad;
    float circlePaddingTop;
    int noOfItems;

    Paint mainCirclePaint;
    Paint smallCirclePaint;

    public CircularRevolver(Context context, float x, float y, float r) {
        super(context);
        angleFromTop=0;

        mainCircleX=x;
        mainCircleY=y;
        mainCircleRad=r;
        smallCircleRad=r/6;
        circlePaddingTop=r/6;
        angleFromTop=0;
        noOfItems=5;

        mainCirclePaint = new Paint();
        mainCirclePaint.setColor(Color.parseColor("#C0C0C0"));
        smallCirclePaint = new Paint();
        smallCirclePaint.setColor(Color.parseColor("#000000"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mainCircleX,mainCircleY,mainCircleRad,mainCirclePaint);

        canvas.drawCircle(
                (float)(mainCircleX+(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.sin(angleFromTop)),
                (float)(mainCircleY-(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.cos(angleFromTop)),
                smallCircleRad,
                smallCirclePaint);

        canvas.drawCircle(
                (float)(mainCircleX+(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.sin(angleFromTop+2*Math.PI/noOfItems)),
                (float)(mainCircleY-(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.cos(angleFromTop+2*Math.PI/noOfItems)),
                smallCircleRad,
                smallCirclePaint);

        canvas.drawCircle(
                (float)(mainCircleX+(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.sin(angleFromTop+4*Math.PI/noOfItems)),
                (float)(mainCircleY-(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.cos(angleFromTop+4*Math.PI/noOfItems)),
                smallCircleRad,
                smallCirclePaint);

        canvas.drawCircle(
                (float)(mainCircleX+(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.sin(angleFromTop+6*Math.PI/noOfItems)),
                (float)(mainCircleY-(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.cos(angleFromTop+6*Math.PI/noOfItems)),
                smallCircleRad,
                smallCirclePaint);

        canvas.drawCircle(
                (float)(mainCircleX+(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.sin(angleFromTop+8*Math.PI/noOfItems)),
                (float)(mainCircleY-(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.cos(angleFromTop+8*Math.PI/noOfItems)),
                smallCircleRad,
                smallCirclePaint);

        canvas.drawCircle(
                (float)(mainCircleX+(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.sin(angleFromTop+10*Math.PI/noOfItems)),
                (float)(mainCircleY-(mainCircleRad-smallCircleRad-circlePaddingTop)*Math.cos(angleFromTop+10*Math.PI/noOfItems)),
                smallCircleRad,
                smallCirclePaint);

        angleFromTop+=0.1f;
        postInvalidate();
    }
}
