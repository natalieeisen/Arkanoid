package com.nataliee_edeno.ex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class Ball
{

    private float cx,cy;    // point of center ball for LOCATION
    private float radius;       // ball radius
    private float dx, dy;   // ball motion
    private int color;
    private boolean isMove;
    boolean isFirstTimeB = false;

    public Ball(float cx, float cy, float cr)
    {
        this.cx = cx/2;
        this.cy = cy-50;
        this.radius = cr;
        this.color = Color.RED;
        this.isMove = false;
    }

    public float getCx()
    {
        return cx;
    }

    public void setCx(float cx)
    {
        this.cx = cx;
    }

    public float getCy()
    {
        return cy;
    }

    public void setCy(float cy)
    {
        this.cy = cy;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public float getDx()
    {
        return dx;
    }

    public void setDx(float dx)
    {
        this.dx = dx;
    }

    public float getDy()
    {
        return dy;
    }

    public void setDy(float dy)
    {
        this.dy = dy;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public boolean isPointInside(float x, float y)
    {
//        PointF ballCenter = new PointF(cx,cy);
//        PointF touchPoit = new PointF(x,y);
        float dist = (float) Math.sqrt((cx-x)*(cx-x) + (cy-y)*(cy-y));

        if(dist<=radius)
            return true;
        return false;
    }

    public void draw(Canvas canvas)
    {
        Paint ballPen = new Paint();
        ballPen.setColor(color);
        //canvas.drawCircle(cx,cy, radius, ballPen);

        canvas.drawCircle(cx,cy,radius,ballPen);

        // for block rect
//        Paint rect = new Paint();
//        rect.setColor(Color.WHITE);
//        rect.setStyle(Paint.Style.STROKE);
        //canvas.drawRect(cx-radius,cy-radius, cx+radius, cy+radius, rect);
    }

    public void move(int canvasW, int canvasH)
    {
        if(true) //if(isMove) temp
        {
            cx = cx + dx;
            cy = cy + dy;

            if(cx-radius<0 || cx+radius>canvasW)
                dx = -dx;

            if(cy-radius<0 || cy+radius>canvasH)
                dy = -dy;
        }
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

//    public boolean isCollideWithBall(Ball otherBall)
//    {
//        float dist = (float) Math.sqrt((cx-otherBall.cx)*(cx-otherBall.cx) + (cy-otherBall.cy)*(cy-otherBall.cy));
//
//        if(dist<=radius+otherBall.radius)
//            return true;
//        return false;
//    }

    public int checkCollision(Brick brick)
    {
        RectF ball = new RectF(cx-radius, cy-radius, cx+radius, cy+radius);
        RectF otherRect = new RectF(brick.getRect());

        if(ball.intersect(otherRect))
        {
            brick.setInvisible();
            dy = -dy;
            return 1;
            //todo - update score and sound
        }
     return 0;
    }
    public boolean checkCollisionPaddle(Paddle paddle)
    {
        RectF ball = new RectF(cx-radius, cy-radius, cx+radius, cy+radius);
        RectF otherRect = new RectF(paddle.getRect());

    //   Log.d("debfirst", String.valueOf(isFirstTimeB));
        if(ball.intersect(otherRect) )
        {

            if(isFirstTimeB)
            {
                dy = -dy;
             //   Log.d("debsecond", String.valueOf(isFirstTimeB));
            }

            return true;

            //todo - update score and sound
        }
       return false;
    }


    public void setisFirstTimeB()
    {
         isFirstTimeB = true;
    }

    public boolean checkCollisionScreen(int canvasH)
    {
        Log.d("debcheckCollisnScreenDY", String.valueOf(cy) );
        Log.d("debcheckCollisionSeenR", String.valueOf(radius) );

        if(cy + radius >= canvasH)
            return true;
        return false;
    }

}
