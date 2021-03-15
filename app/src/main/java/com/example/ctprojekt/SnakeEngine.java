package com.example.ctprojekt;



import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.Random;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


class SnakeEngine extends SurfaceView implements Runnable {

    public SnakeEngine(Context context, Point size) {
        super(context);

        context = context;

        screenGroesseX = size.x;
        screenGroesseY = size.y;

        // Unterteilung Block zu Pixeln
        blockSize = screenGroesseX / NUM_BLOCKS_WIDE;
        // anzahl der Blockhöhe
        numBlocksHigh = screenGroesseY / blockSize;


        // Initialize Drawing
        surfaceHolder = getHolder();
        paint = new Paint();

        snakeXs = new int[200];
        snakeYs = new int[200];

        newGame();
    }
    private Thread thread = null;

    private Context context;


    // Touch Enum
    public enum Heading {UP, RIGHT, DOWN, LEFT}
    // Erster
    private Heading heading = Heading.RIGHT;

    // screen size
    private int screenGroesseX;
    private int screenGroesseY;

    // Schlangenlinie
    private int snakeLength;

    // Apfel koordinaten
    private int appleX;
    private int appleY;

    // Größe Schlange
    private int blockSize;

    // Spielbarer Bereich
    private final int NUM_BLOCKS_WIDE = 40;
    private int numBlocksHigh;

    private long nextFrameTime;
    // FPS
    private final long FPS = 10;
    // es sind 1000 Millisekunden in einer Sekunde
    private final long MILLIS_PER_SECOND = 1000;


    private int score;

    // Snake standort
    private int[] snakeXs;
    private int[] snakeYs;

    private volatile boolean isPlaying;

    //Canvas zur Darstellung
    private Canvas canvas;

    private SurfaceHolder surfaceHolder;

    private Paint paint;
    public void run() {

        while (isPlaying) {

            if(updateRequired()) {
                update();
                draw();
            }

        }
    }

    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void newGame() {
        // Game start mit Länge 1
        snakeLength = 1;
        snakeXs[0] = NUM_BLOCKS_WIDE / 2;
        snakeYs[0] = numBlocksHigh / 2;


        spawnAppl();

        // reset Score
        score = 0;

        nextFrameTime = System.currentTimeMillis();
    }

    public void spawnAppl() {
        Random random = new Random();
        appleX = random.nextInt(NUM_BLOCKS_WIDE - 1) + 1;
        appleY = random.nextInt(numBlocksHigh - 1) + 1;
    }

    private void eatAppl(){
        // appl gegessen
        // schlangen länge erhöht
        snakeLength++;
        // neuer Apfel
        spawnAppl();
        // score erhöhen
        score++;
    }

    private void moveSnake(){
        // Schlange bewegen
        for (int i = snakeLength; i > 0; i--) {
            // Jede schlange Teil wird eine Position nach vorne verschobene
            snakeXs[i] = snakeXs[i - 1];
            snakeYs[i] = snakeYs[i - 1];
            // Kopf wird gelöscht um in der nächsten funktion die richtung zu bekommen

        }

        // Kopf verschieben in die richtige richtung
        switch (heading) {
            case UP:
                snakeYs[0]--;
                break;

            case RIGHT:
                snakeXs[0]++;
                break;

            case DOWN:
                snakeYs[0]++;
                break;

            case LEFT:
                snakeXs[0]--;
                break;
        }
    }

    private boolean detectDeath(){
        //Schlange gestorben
        boolean dead = false;

        // Wand gestoßen
        if (snakeXs[0] == -1) dead = true;
        if (snakeXs[0] >= NUM_BLOCKS_WIDE) dead = true;
        if (snakeYs[0] == -1) dead = true;
        if (snakeYs[0] == numBlocksHigh) dead = true;

        // Sich selbst gefresssen?
        for (int i = snakeLength - 1; i > 0; i--) {
            if ((i > 4) && (snakeXs[0] == snakeXs[i]) && (snakeYs[0] == snakeYs[i])) {
                dead = true;
            }
        }

        return dead;
    }


    public void update() {
        //Apfel gegessen
        if (snakeXs[0] == appleX && snakeYs[0] == appleY) {
            eatAppl();
        }

        moveSnake();

        if (detectDeath()) {
            //neu start
            newGame();
        }
    }
    // Hintergrund malen
    public void draw() {

        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();

            // background color Blau
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            // snake color White
            paint.setColor(Color.argb(255, 255, 255, 255));

            // Scale the HUD text
            paint.setTextSize(90);
            canvas.drawText("Score:" + score, 10, 70, paint);

            // Schlange mal funktion
            for (int i = 0; i < snakeLength; i++) {
                canvas.drawRect(snakeXs[i] * blockSize,
                        (snakeYs[i] * blockSize),
                        (snakeXs[i] * blockSize) + blockSize,
                        (snakeYs[i] * blockSize) + blockSize,
                        paint);
            }

            // Apple Color
            paint.setColor(Color.argb(255, 255, 0, 0));

            // Darw Apple
            canvas.drawRect(appleX * blockSize,
                    (appleY * blockSize),
                    (appleX * blockSize) + blockSize,
                    (appleY * blockSize) + blockSize,
                    paint);

            //TODO funktioniert nicht so wie ich will
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
    //TODO "Keine ahnung was das ist aber es müsste Funktioniert"
    public boolean updateRequired() {

        if(nextFrameTime <= System.currentTimeMillis()){

            nextFrameTime =System.currentTimeMillis() + MILLIS_PER_SECOND / FPS;

            return true;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (motionEvent.getX() >= screenGroesseX / 2) {
                    switch(heading){
                        case UP:
                            heading = Heading.RIGHT;
                            break;
                        case RIGHT:
                            heading = Heading.DOWN;
                            break;
                        case DOWN:
                            heading = Heading.LEFT;
                            break;
                        case LEFT:
                            heading = Heading.UP;
                            break;
                    }
                } else {
                    switch(heading){
                        case UP:
                            heading = Heading.LEFT;
                            break;
                        case LEFT:
                            heading = Heading.DOWN;
                            break;
                        case DOWN:
                            heading = Heading.RIGHT;
                            break;
                        case RIGHT:
                            heading = Heading.UP;
                            break;
                    }
                }
        }
        return true;
    }
}
