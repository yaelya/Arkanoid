package com.yaelya.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by yaelya on 21/05/2018.
 */

public class Ball {
    private float dx,dy;
    private float cx,cy;
    private float radius;
    private int color;
    private Paint pen;

    public Ball(float cx, float cy , float radius,float dx, float dy)
    {
        this.cx = cx;
        this.cy =cy;
        this.dx=dx;
        this.dy=dy;
        this.radius = radius;
        this.color = Color.argb(235,255,255,255);
        pen = new Paint();
    }

    public void draw(Canvas canvas)
    {

        pen.setColor(color);
        canvas.drawCircle(cx,cy, radius ,pen);
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getCx() {
        return cx;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public void move(int width, int height)
    {
       cx = cx + dx;
       cy = cy + dy;
       // dx=dx+cx;
       // dy=dy-cy;


        // check out of the right or left
        if (cx + radius >= width || cx - radius < 0 )
            dx = -dx;
        // check out of the down
        if (cy + radius >= height || cy-radius<0 )
            dy =-dy;




    }
    public void setPosition(float cx,float cy){
        this.cx=cx;
        this.cy=cy;
    }

    public boolean check_ball_under_paddle(float y_paddle,float paddle_heigth)
    {
        if (cy - radius >= y_paddle+paddle_heigth)
            return true;
     return false;
    }


}
