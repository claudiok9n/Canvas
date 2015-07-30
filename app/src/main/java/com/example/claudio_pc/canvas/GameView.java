package com.example.claudio_pc.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
    protected SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private long lastClick;
    Bitmap img;
    private Nave nave;
    private Sprite sprite;
    private Invaders invaders;
    private int[][] mapInv;

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

                for(int f=0; f<4; f++){
                    for(int c=0; c<10; c++){
                        //mapInv[f][c] = 1;
                    }
                }
            }

            @Override
           public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

           }
        });

        nave = new Nave(this, getBitmap(R.mipmap.nave));
        invaders = new Invaders(this, getBitmap(R.mipmap.inv));
        sprite = new Sprite(this, getBitmap(R.mipmap.inv));
    }

        private Bitmap getBitmap(int IdResource){
            return BitmapFactory.decodeResource(getResources(), IdResource);
        }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xTouch = event.getX();
        float yTouch = event.getY();
         lastClick = System.currentTimeMillis();
            synchronized (getHolder()) {
                nave.setPosition(Math.round(xTouch));
            }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        nave.onDraw(canvas);
        invaders.onDraw(canvas);
        sprite.onDraw(canvas);
    }

}
