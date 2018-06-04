package com.yaelya.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by yaelya on 21/05/2018.
 */

public class Brick {
    private float x,y;
    private float w,h;
    private int color;
    private Paint pen;

    public Brick(float x, float y,float w, float h)
    {
        this.x = x;
        this.y = y;
        this.w= w;
        this.h= h;
        this.color = Color.argb(235,255,204,0);
        pen = new Paint();
    }

    public void draw(Canvas canvas)
    {
        pen.setColor(color);
        canvas.drawRect(x,y,x+w,y+h,pen);
    }

    public boolean crash_brick_ball(Ball ball)
    {
        float ball_cx,ball_cy,radius,brick_x,brick_y,brick_w,brick_h;
        ball_cx=ball.getCx();
        ball_cy=ball.getCy();
        radius=ball.getRadius();
        brick_x=x-(2*radius);
        brick_y=y-(2*radius);
        brick_w=w+(4*radius);
        brick_h=h+(4*radius);
        if ((ball_cy-radius>=brick_y) && (ball_cy+radius<=brick_y+brick_h)
                && (ball_cx-radius>=brick_x )&&( ball_cx+radius<=brick_x+brick_w))
        {
            ball.setDx(-ball.getDx());
            ball.setDy(-ball.getDy());
            return true;
        }
        return false;

    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public float getW()
    {
        return w;
    }
    public float getH()
    {
        return h;
    }
}
