package com.yaelya.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yaelya on 21/05/2018.
 */

public class GameView extends View
{
    private static final float BRICK_HEIGHT = 80;
    private static final int ROWS = 5;
    private static final int COLS = 7;
    private static final int GAP = 10;
    private static final float BALL_RADIUS= 40;
    private static final float PADDLE_HEIGTH= 20;
    private static final int GAP_DOWEN= 100;
    private static final int DX= 3;
    private static final int DY= -3;
    private static final int RIGHT= 0;
    private static final int LEFT=1;
    private static final int PADDLE_STEP=30;
    private static final int POINTS=5;
    private static final int STATUS_DOWN=1;
    private static final int STATUS_UP=0;



    private Ball ball;
    private Brick brick;
    private Paddle paddle;
    private BrickCollection brickCollection;
    private Paint pen;
    private int width, height;
    private  int score;
    private int lives;
    private String status;
    private String text;
    private float cx,cy,x_paddle,y_paddle;
    private int num_bricks;
    private int paddle_status;
    private  int position;






    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pen = new Paint();
        pen.setAntiAlias(true);
        score=0;
        lives=3;
        status="GET_READY";
        text="Click to PLAY!";
        num_bricks=0;
        paddle_status=STATUS_UP;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(254,169,169,169));
        ball.draw(canvas);
//        brick.draw(canvas);
        paddle.draw(canvas);
        brickCollection.draw(canvas);
        pen.setColor(Color.BLACK);
        pen.setTextSize(60f);
      //  pen.setFontVariationSettings("bold");
        canvas.drawText("Score: " + score,20,70,pen);
        canvas.drawText("Lives: " + lives,1520,70,pen);
        pen.setColor(Color.BLACK);
        canvas.drawText(text,700,700,pen);

       /* if(ball.check_ball_under_paddle(paddle.getY(),PADDLE_HEIGTH)==true) {
            lives--;
            canvas.drawText("Lives: " + lives,1520,70,pen);
        }
        if (lives==0)
            ready();*/
    }

   public void animationLoop() {
       initGame();
       ready();
       status = "GET_READY";

       while (true) {
           if (status == "PLAYING") {
               //ball.move(width, height);
               palying();
               if (paddle_status==STATUS_DOWN) {
                   if (position == RIGHT)
                       paddle.moveRigth(position);
                   else if (position == LEFT)
                       paddle.moveLeft(position);
                   postInvalidate();
               }
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
   }
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;
        cx=(width / 2 - ((width / COLS / 2))) + ((width / COLS) / 2);
        cy=(height - GAP_DOWEN - PADDLE_HEIGTH - (BALL_RADIUS / 2));
        x_paddle=width / 2 - ((width / COLS) / 2);
        y_paddle=height - GAP_DOWEN;
       initGame();

    }

    private void initGame()
    {
            ball = new Ball(cx, cy, BALL_RADIUS, DX, DY);
            // brick = new Brick(500,500, width/ROWS, BRICK_HEIGHT);
            paddle = new Paddle(x_paddle, y_paddle,width / COLS, PADDLE_HEIGTH,PADDLE_STEP,width);
            brickCollection = new BrickCollection(width, BRICK_HEIGHT, ROWS, COLS, GAP, GAP_DOWEN);

       // ball.setDx(-2);
       // ball.setDy(3);

       // if(status=="GET_READY")
         //   ready();
    }
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction()!= MotionEvent.ACTION_UP)
        {

            if (status=="GET_READY"){
                text=" ";
                postInvalidate();

                status="PLAYING";
            }
            else  if (status=="PLAYING"){
                position=touch_position(event.getX());
                paddle_status=STATUS_DOWN;
                postInvalidate();
            }

        }
        else
        {
            paddle_status=STATUS_UP;
        }
        return true;
    }

    public int touch_position(float x){
        if (x<=width/2)
            return  LEFT;
        else
            return RIGHT;
    }

    public void ready()
    {
         ball.setPosition(cx,cy);
         paddle.setPosition(x_paddle,y_paddle);
         ball.setDx(DX);
         ball.setDy(-DY);
         text="Click to PLAY!";
         postInvalidate();
        }

        public void palying()
        {
            ball.move(width,height);
            if(ball.check_ball_under_paddle(paddle.getY(),PADDLE_HEIGTH)==true) {
                lives--;

                if (lives == 0) {
                    status = "GAME_OVER_LOSS";
                    loss();
                    return;
                }
                postInvalidate();
                status = "GET_READY";
                ready();
            }
           if (paddle.crash_paddle_ball(ball)==true)
            {
                ball.setDx(ball.getDx());
                ball.setDy(-ball.getDy());
            }
             if (brickCollection.crash_brick_ball(ball)==true) {
                 num_bricks++;
                 score+=num_bricks*lives*POINTS;
                 ball.setDy(-ball.getDy());
             }
            if (num_bricks==COLS*ROWS)
            {
                status="GAME OVER- WIN";
                win();
            }
            postInvalidate();

        }

        public void loss()
        {
          ball.setPosition(cx,cy);
          paddle.setPosition(x_paddle,y_paddle);
          text="GAME OVER You Loss!";
          invalidate();
        }

        public void win()
        {
          text="GAME OVER You Win!";
          invalidate();;
        }
    }
