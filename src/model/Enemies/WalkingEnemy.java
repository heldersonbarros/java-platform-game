package model.Enemies;

import java.awt.*;
import java.util.ArrayList;

public class WalkingEnemy extends Enemy {

    public WalkingEnemy(int posX, int posY, String pathRun, String pathDie, int columnsRun, int columnsDie, ArrayList<Rectangle> tilesColliders) {
        super(posX, posY, pathRun, pathDie, columnsRun, columnsDie, tilesColliders, true);
        AnimationThread animationThread = new AnimationThread();
        animationThread.start();
    }

    private class AnimationThread extends Thread {
        public void run() {
            synchronized (this)
            {
                while(true) {
                    if (!isDead()) {
                        if (getFrame() == getColumnsRun()-1) setFrame(0);
                        else setFrame(getFrame() + 1);
                        try {
                            sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    getSprite().setAppereance(getFrame());
                }
            }
        }
    }
}
