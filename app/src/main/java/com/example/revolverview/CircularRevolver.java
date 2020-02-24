package com.example.revolverview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class CircularRevolver extends View {

    float angleFromTop;
    float mainCircleX;
    float mainCircleY;
    float mainCircleRad;
    float smallCircleRad;
    float circlePaddingTop;
    float extraMarginMainCircle;
    int noOfItems;

    Paint mainCirclePaint;
    Paint smallCirclePaint;

    private float mLastTouchAngle;
    private float mDraggedAngle;

    private float mLastWheelTouchX;
    private float mLastWheelTouchY;

    public CircularRevolver(Context context, float x, float y, float r) {
        super(context);
        angleFromTop=0;

        mainCircleX=x;
        mainCircleY=y;
        mainCircleRad=r;
        smallCircleRad=r/4;
        circlePaddingTop=r/6;
        angleFromTop=0;
        noOfItems=5;
        extraMarginMainCircle=r/4;

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

        //angleFromTop+=0.1f;
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if(isInside(x,y)) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    x = event.getX();
                    y = event.getY();

                    break;

                case MotionEvent.ACTION_UP:

                    break;

                case MotionEvent.ACTION_MOVE:
                    float currentX = event.getX();
                    float currentY = event.getY();

                    System.out.println("X - " + x + " Y - " + y);
                    System.out.println("currentX - " + currentX + " currentY - " + currentY);

                    if (currentX - x >= 0 && currentY - y >= 0 || currentX - x <= 0 && currentY - y <= 0) {
                        angleFromTop += 0.1f;
                    } else
                        angleFromTop -= 0.1;
                    postInvalidate();
                    break;

                default:

            }
        }
/*        if (!mWheelBounds.contains(x, y)) {
            if (mIsDraggingWheel) {
                flingWheel();
            }
            return true;
        }

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (!mIsDraggingWheel) {
                    startWheelDrag(event, x, y);
                }

                mClickedItem = getClickedItem(x, y);
                break;
            case MotionEvent.ACTION_UP:
                if (mOnItemClickListener != null && mClickedItem != null
                        && mClickedItem == getClickedItem(x, y)
                        && mDraggedAngle < CLICK_MAX_DRAGGED_ANGLE) {
                    boolean isSelected = Math.abs(mClickedItem.mRelativePos) < 1f;
                    mOnItemClickListener.onWheelItemClick(this,
                            mClickedItem.mAdapterPosition, isSelected);
                }
            case MotionEvent.ACTION_CANCEL:
                if (mIsDraggingWheel) {
                    flingWheel();
                }

                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mIsDraggingWheel) {
                    startWheelDrag(event, x, y);
                    return true;
                }

                mVelocityTracker.addMovement(event);
                mLastWheelTouchX = x;
                mLastWheelTouchY = y;
                setRadiusVector(x, y);

                float wheelRadiusSquared = mWheelBounds.getRadius() * mWheelBounds.getRadius();
                float touchRadiusSquared = mRadiusVector.x * mRadiusVector.x + mRadiusVector.y * mRadiusVector.y;
                float touchFactor = TOUCH_FACTORS[(int) (touchRadiusSquared / wheelRadiusSquared * TOUCH_FACTORS.length)];
                float touchAngle = mWheelBounds.angleToDegrees(x, y);
                float draggedAngle = -1f * Circle.shortestAngle(touchAngle, mLastTouchAngle) * touchFactor;
                addAngle(draggedAngle);
                mLastTouchAngle = touchAngle;
                mDraggedAngle += draggedAngle;

                if (mRequiresUpdate) {
                    mRequiresUpdate = false;
                }
                break;
        }*/
        return true;
    }

    private boolean isInside(float x, float y) {
        float valueOfCxy;
        valueOfCxy = (x-mainCircleX)*(x-mainCircleX) + (y-mainCircleY)*(y-mainCircleY) - (mainCircleRad+extraMarginMainCircle)*(mainCircleRad+extraMarginMainCircle);
        return valueOfCxy <= 0;
    }
}
