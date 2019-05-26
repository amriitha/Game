package com.example.user.flyingfish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static android.R.color.white;

/**
 * Created by user on 3/16/2019.
 */

public class flyingFishView extends View
{
    private Bitmap fish[]=new Bitmap[2];
    private int fishx=10;
    private int fishy;
    private int fishspeed;
    private int canvaswidth;
    private int canvasheight;
    private int yellowx,yellowspeed=16;
    private int yellowy;
    private Paint yellowpaint=new Paint();
    private int greenx,greeny,greenspeed=25;
    private Paint greenpaint= new Paint();
    private int redx,redy,redspeed=25;
    private Paint redpaint= new Paint();
    private int score,lifecounteroffish;
    private boolean touch = false ;
    private Bitmap backgroundimage;
    private Paint scorepoint =new Paint();
    private Bitmap life[]=new Bitmap[2];
    public flyingFishView(Context context) {
        super(context);
        fish[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1]= BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backgroundimage=BitmapFactory.decodeResource(getResources(),R.drawable.background);
        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);
        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);
        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);

        scorepoint.setColor(Color.WHITE);
        scorepoint.setTextSize(50);
        scorepoint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepoint.setAntiAlias(true);
        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        fishy=550;
        score=0;
        lifecounteroffish=3;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvaswidth=canvas.getWidth();
        canvasheight=canvas.getHeight();
        canvas.drawBitmap(backgroundimage,0,0,null);
        int minfishy=fish[0].getHeight();
        int maxfish=canvasheight-fish[0].getHeight()*3;
        fishy=fishy+fishspeed;
        if (fishy<minfishy)
        {
           fishy=minfishy;
        }
        if (fishy>maxfish)
        {
            fishy=maxfish;
        }
        fishspeed=fishspeed+2;
        if (touch)
        {
            canvas.drawBitmap(fish[1],fishx,fishy,null);
            touch=false;
        }
        else
        {
            canvas.drawBitmap(fish[0],fishx,fishy,null);
        }

        yellowx=yellowx-yellowspeed;
        if(hitballchecker(yellowx,yellowy))
        {
            score=score+5;
            yellowx= -100;
        }
        if(yellowx<0)
        {
            yellowx=canvaswidth+21;
            yellowy=(int)Math.floor(Math.random()*(maxfish-minfishy))+minfishy;
        }
        canvas.drawCircle(yellowx,yellowy,20,yellowpaint);
        greenx=greenx-greenspeed;
        if(hitballchecker(greenx,greeny))
        {
            score=score+10;
            greenx= -100;
        }
        if(greenx<0)
        {
            greenx=canvaswidth+21;
            greeny=(int)Math.floor(Math.random()*(maxfish-minfishy))+minfishy;
        }
        canvas.drawCircle(greenx,greeny,15,greenpaint);
        redx=redx-redspeed;
        if(hitballchecker(redx,redy))
        {

            redx= -100;
            lifecounteroffish--;
            if(lifecounteroffish==0)
            {
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT).show();
                Intent gameoverintent=new Intent(getContext(),GameOverActivity.class);
                gameoverintent.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                gameoverintent.putExtra("score",score);
                getContext().startActivity(gameoverintent);

            }
        }
        if(redx<0)
        {
            redx=canvaswidth+21;
            redy=(int)Math.floor(Math.random()*(maxfish-minfishy))+minfishy;
        }
        canvas.drawCircle(redx,redy,25,redpaint);
        canvas.drawText("score:"+score,20,60,scorepoint);
      for(int i=0;i<3;i++)
      {
          int x=(int)(580+life[0].getWidth()*1.5*i);
          int y=30;
          if(i<lifecounteroffish)
          {
              canvas.drawBitmap(life[0],x,y,null);
          }
          else
          {
              canvas.drawBitmap(life[1],x,y,null);
          }
      }



    }
    public boolean hitballchecker(int x,int y)
    {
        if (fishx < x &&  x< (fishx + fish[0].getWidth()) && fishy < y && y<(fishy + fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;
            fishspeed = -25;
        }
        return true;
    }
}
