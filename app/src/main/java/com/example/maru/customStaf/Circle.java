package com.example.maru.customStaf;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;


public class Circle extends View {
    //la class crée uniquement pour le petit cercle qui change la coumeur
    private Paint mPaint;
    Random random = new Random();

    public Circle(Context context) {
        super(context);
        init(null);
    }

    public Circle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        int color = Color.rgb(r, g, b);
        mPaint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float w, h, cx, cy, radius;
        w = getWidth();
        h = getHeight();
        cx = w / 2;
        cy = h / 2;

        if (w > h) {
            radius = h / 2;
        } else {
            radius = w / 2;
        }

        canvas.drawCircle(cx, cy, radius, mPaint);
    }
}
