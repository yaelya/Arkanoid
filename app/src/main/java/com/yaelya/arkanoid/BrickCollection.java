package com.yaelya.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yaelya on 21/05/2018.
 */

public class BrickCollection {
    private ArrayList<Brick> bricks;
    private Paint pen;

    public BrickCollection(float canvasWidth, float brickHeight, int rows, int cols, int gap1, int gap2)
    {
        bricks = new ArrayList<>();
        float x=gap1;
        float y=gap2;
        Log.d("debugg","****"+canvasWidth);
        float brickWidth = (canvasWidth-cols*(gap1+1))/cols;
        float h=brickHeight;
        for (int i=0;i<rows;i++)
        {
            for(int j=0; j<cols ;j++){
                bricks.add(new Brick(x,y,brickWidth,brickHeight));
                x=x+brickWidth+gap1;
            }
            y+=brickHeight+gap1;
            x=gap1;
        }

    }
    public void draw(Canvas canvas)
    {
        for (int i = 0; i < bricks.size(); i++) {
            bricks.get(i).draw(canvas);
        }

    }

    public boolean crash_brick_ball(Ball ball)
    {
        for (int i = 0; i<bricks.size(); i++)
        {
            if (bricks.get(i).crash_brick_ball(ball)==true) {
                Log.d("777", "crash_brick_ball: lll" + bricks.get(i).crash_brick_ball(ball) + i);
                bricks.remove(bricks.get(i));
                return true;
            }
        }
        return false;
    }
}
