package com.yaelya.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by yaelya on 21/05/2018.
 */

public class Paddle {
    private float x,y;
    private float w,h,wScreen;
    private int color,step;
    private Paint pen;

    public Paddle(float x, float y,float w, float h, int step,float wScreen)
    {
        this.x = x;
        this.y = y;
        this.w= w;
        this.h= h;
        this.wScreen=wScreen;
        this.step=step;
        this.color = Color.argb(235,184,134,11);;
        pen = new Paint();
    }

    public void draw(Canvas canvas)
    {
        pen.setColor(color);
        canvas.drawRect(x,y,x+w,y+h,pen);
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x,float y ){
        this.x=x;
        this.y=y;
    }

    public void moveRigth(int position){
        if (x+w+step<=wScreen )
            x=x+step;
        else
            x=wScreen-w;

    }
    public void moveLeft(int position)
    {
        if (x-step>0)
            x=x-step;
        else
            x=0;
    }

    public boolean crash_paddle_ball(Ball ball)
    {
        float ball_cx,ball_cy,radius;
        ball_cx=ball.getCx();
        ball_cy=ball.getCy();
        radius=ball.getRadius();
        if (ball_cy+radius==y && ball_cx+radius>=x && ball_cx-radius<=x+w)
            return true;
        else
            return false;

    }
}
