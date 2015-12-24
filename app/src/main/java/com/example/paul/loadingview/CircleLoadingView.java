package com.example.paul.loadingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by paul on 15/12/24.
 */
public class CircleLoadingView extends View {
    private Paint mPaintCircle ;
    private Paint mPaintText ;

    private RectF mRectF ;
    private float mSwipeAngle = 360;
    private float mStartAngele=0;
    private float mPercent;

    public CircleLoadingView(Context context) {
        super(context);
        init(context);
    }

    public CircleLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setColor(Color.parseColor("#33000000"));

        
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(Color.parseColor("#000000"));
        mPaintText.setTextSize(30);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radius = width/2;

        mRectF = new RectF(width/2-250, width/2-250, width/2+250,width/2+250);
        //测宽高
        Rect rect = new Rect();
        mPaintText.getTextBounds(mPercent + "", 0, (mPercent + "").length(), rect);

        int strwidth = rect.width();
        canvas.drawText(mPercent + "", width / 2 - strwidth / 2, width / 2, mPaintText);
        canvas.drawArc(mRectF, mStartAngele - 90, mSwipeAngle, true, mPaintCircle);
    }
    public void setPercent(float percent){
        if (percent==100){
            setVisibility(View.INVISIBLE);
        }else {
            setVisibility(View.VISIBLE);
        }
        float start = 360*percent/100;
        mPercent = percent;
        mStartAngele= start;
        mSwipeAngle = 360-start;
        postInvalidate();
    }
}
