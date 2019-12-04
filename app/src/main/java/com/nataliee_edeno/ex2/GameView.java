package com.nataliee_edeno.ex2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;


public class GameView extends View {

    private Paint pen,gameOverPen, playPen;

    //  private float ballcx, ballcy,ballcr, len, hei;
    private int canvasW, canvasH, sX,sY;

    //game object
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private int numBricks, score,lives;
    private final int  COLUMN = 8;
    private final int  ROW = 6;

    private boolean gameOn = false;

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);


        // bg color
        canvas.drawColor(Color.rgb(255,204,255));

        //draw object game
        canvas.drawText("Score: " + score,100,80,pen);
        canvas.drawText("Lives: " + lives,1500,80,pen);
        ball.draw(canvas);
       paddle.draw(canvas);
        // Draw the bricks if visible
        for(int i = 0; i < numBricks; i++){
            if(bricks[i].getVisibility()) {
                bricks[i].draw(canvas);
            }
        }

        // move/update all object game
        ball.move(canvasW, canvasH);
        if(!gameOn)
           canvas.drawText("Click to play!" ,550,canvasH/2,playPen);

        // Check for ball colliding with a brick
        for(int i = 0; i < numBricks; i++){

            if (bricks[i].getVisibility())
            {
               if(ball.checkCollision(bricks[i]) == 1)
                   score += 5;
            }

        }


        if (ball.checkCollisionPaddle(paddle) )
            Log.d("yeyeyeyye", "yes");

        if(ball.checkCollisionScreen(canvasH))
        {
            //צריך להוסיף את זה לאחר שניצור את הטרדים
//            lives--;
//            if(lives <= 0)
//            {
//                gameOn = false;
//                ball.setDx(0);
//                ball.setDy(0);
//                canvas.drawText("Game Over - You Loss!" ,450,canvasH/2,gameOverPen);
//            }
            //בינתיים שמתי ככה
              // gameOn = false;
                ball.setDx(0);
                ball.setDy(0);
                canvas.drawText("Game Over - You Loss!" ,450,canvasH/2,gameOverPen);

        }
           // gameOn = false;
        invalidate();
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        canvasW = w;
        canvasH = h;

        //pen
        pen = new Paint();
        pen.setColor(Color.rgb(102,0,51));
        pen.setStyle(Paint.Style.FILL);
        pen.setStrokeWidth(10);
        pen.setTextSize(60);

        // gameOver Pen
        gameOverPen = new Paint();
        gameOverPen.setColor(Color.rgb(102,0,51));
        gameOverPen.setStyle(Paint.Style.FILL);
        gameOverPen.setStrokeWidth(20);
        gameOverPen.setTextSize(100);
        gameOverPen.setFakeBoldText(true);

        playPen = new Paint();
        playPen.setColor(Color.rgb(102,0,51));
        playPen.setStyle(Paint.Style.FILL);
        playPen.setStrokeWidth(20);
        playPen.setTextSize(100);
        playPen.setFakeBoldText(true);


        lives = 3;

        ball = new Ball(canvasW,canvasH,25);
        paddle = new Paddle(canvasW,canvasH);
        // Up to 200 bricks
        bricks = new Brick[200];
        numBricks = 0;

        int brickWidth = canvasW / 8;
        int brickHeight = canvasH / 10;


       // canvas.drawText("Click to play" ,450,canvasH/2,gameOverPen);
      //  int test = 50;

        for(int column = 0; column < COLUMN; column ++ )
        {
            for(int row = 1; row < ROW; row ++ )
            {
                bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                numBricks ++;
            }
        }
       // bricks[0] = new Brick(1 ,0, brickWidth, brickHeight);
       // bricks[1] = new Brick(1 ,0, brickWidth, brickHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //player touched the screen
        Random ranDX = new Random();
        int numDx = ranDX.nextInt(10);
        Random ranDY = new Random();
        int numDy = ranDY.nextInt(10);

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(!gameOn) {
                ball.setDx(numDx);
                ball.setDy(numDy);
                gameOn = true;
                ball.setisFirstTimeB();
                Log.d("debon touch", "1");

            }
        }

        return true;
    }

    public boolean isGameOn()
    {
        return gameOn;
    }

    public void drawFirst(Canvas canvas)
    {

        canvas.drawText("Click to play" ,450,canvasH/2,gameOverPen);
    }
}