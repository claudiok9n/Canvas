package com.example.claudio_pc.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
    protected SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    Bitmap img;
    private Sprite sprite;
    private Invaders invaders;

    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback(){

            @Override
            public void surfaceDestroyed(SurfaceHolder holder){
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {}
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder){
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
           public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

           }
        });
        img = BitmapFactory.decodeResource(getResources(), R.mipmap.inv);
        invaders = new Invaders(this, img);
        sprite = new Sprite(this, img);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        invaders.onDraw(canvas);
        sprite.onDraw(canvas);
    }

}
