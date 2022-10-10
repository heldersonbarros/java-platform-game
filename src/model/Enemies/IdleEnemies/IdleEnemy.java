package model.Enemies.IdleEnemies;

import model.Enemies.Enemy;
import model.Sprite;

import java.awt.*;
import java.util.ArrayList;

public abstract class IdleEnemy extends Enemy {
    Sprite attackSprite;
    Sprite idleSprite;

    public IdleEnemy(int posX, int posY, String pathRun, String pathDie, int columnsRun, int columnsDie, ArrayList<Rectangle> tilesColliders) {
        super(posX, posY, pathRun, pathDie, columnsRun, columnsDie, tilesColliders, false);


        AnimationThread animationThread = new AnimationThread();
        animationThread.start();
    }

    public abstract void attack();

    public abstract void disarm();

    private class AnimationThread extends Thread {
        public void run() {
            synchronized (this) {
                while (true) {
                    if (!isDead()) {
                        if (!isAttacking()) {
                            if (getFrame() == getColumnsRun() - 1) {
                                setFrame(0);
                                setSprite(attackSprite);
                                setAttacking(true);
                                attack();
                            } else {
                                setFrame(getFrame() + 1);
                            }

                        } else {
                            if (getFrame() == 7) {
                                setFrame(0);
                                setSprite(idleSprite);
                                setAttacking(false);
                            } else {
                                disarm();
                                setFrame(getFrame() + 1);
                            }
                        }

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
