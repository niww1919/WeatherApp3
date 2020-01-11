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
    Paint paint;
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

        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        RectF mRectF = new RectF(20, 20, 400, 400);
        Path path = new Path();
        path.addArc(mRectF, 180, (180 * hour) / 24);
//        mRectF.centerX(g)
//        canvas.drawCircle(radius,radius,radius,paint);
        canvas.drawArc(mRectF, 180, 180, false, paintArc);
        canvas.drawArc(mRectF, 180, (180 * hour) / 24, false, paint);
        canvas.drawText("-10 C", 200, 200, paintTextWeather);
        canvas.drawTextOnPath("Hour " + hour, path, 0, 10, paintText);

    }

    private void initPaint(float radius, int color) {

        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);//todo what it is?
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(radius);
        paint.setStrokeWidth(30);

        paintArc = new Paint();
        paintArc.setAntiAlias(true);
        paintArc.setColor(Color.YELLOW);
        paintArc.setStrokeWidth(30);
        paintArc.setStyle(Paint.Style.STROKE);

        paintText = new Paint();
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(30);
        paintText.setTextAlign(Paint.Align.CENTER);

        paintTextWeather = new Paint();
        paintTextWeather.setColor(Color.RED);
        paintTextWeather.setTextSize(100);
        paintTextWeather.setTextAlign(Paint.Align.CENTER);


    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}



