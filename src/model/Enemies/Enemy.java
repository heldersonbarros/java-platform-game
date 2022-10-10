package model.Enemies;

import model.Entity2D;

import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Enemy extends Entity2D {
    private ArrayList<Rectangle> tilesColliders;
    private int columnsRun;
    private boolean dead, move;
    private boolean attacking;

    public Enemy(int posX, int posY, String pathRun, String pathDie, int columnsRun, int columnsDie, ArrayList<Rectangle> tilesColliders, boolean move) {
        super(posX, posY, pathRun, pathDie, columnsRun, columnsDie);
        this.tilesColliders = tilesColliders;
        this.move = move;
        this.columnsRun = columnsRun;

        setCollider(new Rectangle(getSprite().getWidth(), getSprite().getHeight()));
        getCollider().x = posX;
        getCollider().y = posY;

        if (move) {
            MoveThread moveThread = new MoveThread();
            moveThread.start();
        }
    }

    private class MoveThread extends Thread {
        public void run() {
            synchronized (this){
                while (!dead) {
                    move(tilesColliders);
                    try {
                        sleep(8);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public void move(ArrayList<Rectangle> rectanglesColliders) {
        boolean intersectX = intersectX(rectanglesColliders);
        boolean intersectY = intersectY(rectanglesColliders);

        if (intersectX && intersectY) {
            if (isDirection()) {
                setDirection(false);
                setPosX(getPosX()+1);
            } else {
                setDirection(true);
                setPosX(getPosX()-1);
            }
            getCollider().x = getPosX();
        } else {
            if (!intersectY) {
                setPosY(getPosY()+1);
                getCollider().y = getPosY();

            }
            if (!intersectX) {
                if (isDirection()) {
                    setPosX(getPosX() - 1);
                } else {
                    setPosX(getPosX() + 1);
                }

                getCollider().x = getPosX();

            }
        }
    }

    public ArrayList<Rectangle> getTilesColliders() {
        return tilesColliders;
    }

    public void setTilesColliders(ArrayList<Rectangle> tilesColliders) {
        this.tilesColliders = tilesColliders;
    }

    public int getColumnsRun() {
        return columnsRun;
    }

    public void setColumnsRun(int columnsRun) {
        this.columnsRun = columnsRun;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
