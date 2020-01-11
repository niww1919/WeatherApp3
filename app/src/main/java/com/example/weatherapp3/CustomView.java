package com.example.weatherapp3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class CustomView extends View {

    public static final int DEF_RADIUS = 0;
    public static final int DEF_COLOR = 0;
    private int mPoint;
    Paint mPaint;
    Paint paintArc;
    Paint paintText;
    Paint paintTextWeather;

    float radius;
    int textSize;
    Color color;
    String mText;
    Calendar calendar;


    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(200,200);
    }



    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paintTextWeather = new Paint();
        paintTextWeather.setColor(Color.RED);
        paintTextWeather.setTextSize(100);
        paintTextWeather.setTextAlign(Paint.Align.CENTER);

        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

//        canvas.drawText("-10 C", 0, 0, paintTextWeather);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(3);

        canvas.drawPaint(mPaint);

    }



    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}



