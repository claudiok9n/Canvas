package com.example.claudio_pc.canvas;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Nave {
    private static final int IMG_ROWS = 1;
    private static final int IMG_COLUMNS = 3;
    private int x = 100;
    private int y = 0;
    private int xSpeed = 5;
    private int ySpeed = 5;
    private GameView gameView;
    private Bitmap img;
    private int currentFrameX = 0;
    private int currentFrameY = 0;
    private int width;
    private int height;

    public Nave(GameView gameView, Bitmap img) {
        this.gameView = gameView;
        this.img = img;
        this.width = img.getWidth() / IMG_COLUMNS;
        this.height = img.getHeight() / IMG_ROWS;
        y = gameView.getHeight() + 250;
    }

    public void setPosition(int xTouch){
        this.x = xTouch - 25;
    }

    private void update() {
        currentFrameX = 0;
        currentFrameY = 0;
    }

    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrameX * width;
        int srcY = currentFrameY * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(img, src, dst, null);
    }
}