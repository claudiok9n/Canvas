package com.example.claudio_pc.canvas;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Invaders {
    private static final int IMG_ROWS = 2;
    private static final int IMG_COLUMNS = 3;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 5;
    private int ySpeed = 5;
    private GameView gameView;
    private Bitmap img;
    private int currentFrame = 0;
    private int width;
    private int height;
    private int TypeInv = 0;

    public Invaders(GameView gameView, Bitmap img) {
        this.gameView = gameView;
        this.img = img;
        this.width = img.getWidth() / IMG_COLUMNS;
        this.height = img.getHeight() / IMG_ROWS;
    }

    private void update() {
        if (x > gameView.getWidth() - img.getWidth() - xSpeed) {
            xSpeed = -5;
        }
        if (x + xSpeed < 0) {
            xSpeed = 5;
        }
        if (y > gameView.getWidth() - img.getWidth() - ySpeed) {
            ySpeed = -5;
        }
        if (y + ySpeed < 0) {
            ySpeed = 5;
        }
        x = x + xSpeed;
        y = y + ySpeed;
        currentFrame = ++currentFrame % IMG_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
        update();

        for(int f=0; f<4; f++){
            for(int c=0; c<10; c++){
                int inv = gameView.mapInv[f][c];
                if(inv != 0){
                    x = c * 15 + 25;
                    y = f * 15 + 25;
                    int srcX = currentFrame * width;
                    int srcY = 1 * height;
                    Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
                    Rect dst = new Rect(x, y, x + width, y + height);
                    canvas.drawBitmap(img, src, dst, null);
                }
            }
        }

    }
}