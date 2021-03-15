package com.example.ctprojekt;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class SnakeActivity1 extends Activity {

    SnakeEngine snakeEngine;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display größe
        Display display = getWindowManager().getDefaultDisplay();

        // erstellen pointer
        Point size = new Point();
        display.getSize(size);

        // Klasse Snakeengin erstellen
        snakeEngine = new SnakeEngine(this, size);
        setContentView(snakeEngine);
    }
}