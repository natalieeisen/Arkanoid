package com.nataliee_edeno.ex2;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Paddle
{

    private float x, y , len, hei;
    private RectF rec;


    public Paddle(float x, float y)
    {
        len = x / 8;
        hei = y / 25;

        rec = new RectF(x/2 -200 ,y -30 ,x/2 +len, y -30 +hei );
        // canvas.drawRect(canvasW/2 - 200,canvasH - 30,canvasW/2 + len  ,canvasH - 30 + hei  ,pen);

    }
    public RectF getRect(){
        return rec;
    }

    public void draw(Canvas canvas)
    {
        Paint rectPen = new Paint();
        rectPen.setStyle(Paint.Style.STROKE);
        rectPen.setStrokeWidth(5);
        rectPen.setColor(Color.BLACK);

        canvas.drawRect(this.rec, rectPen);

        // canvas.drawRect(x,y, x+len, y+hei, rectPen);
    }

}