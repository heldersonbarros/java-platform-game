package model;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Entity2D {

    private int posX, posY, frame, columnsDie;
    private boolean direction;

    private Sprite sprite, dieSprite;
    private Rectangle collider;

    public Entity2D(int posX, int posY, String mainSpritePath, String dieSpritePath, int columnsRun, int columnsDie) {
        this.posX = posX;
        this.posY = posY;
        this.columnsDie = columnsDie;

        try {
            this.sprite = new Sprite(new File(mainSpritePath), 0, columnsRun);
            this.dieSprite = new Sprite(new File(dieSpritePath), 0, columnsDie);
        } catch (Exception e) {
            System.out.println(e);
        }

        collider = new Rectangle(sprite.getWidth(), sprite.getHeight());
        collider.x = posX;
        collider.y = posY;
    }

    public void startDieAnimation() {
        DieAnimation dieAnimation = new DieAnimation();
        dieAnimation.start();
    }

    private class DieAnimation extends Thread {

        public void run() {
            synchronized (this) {
                boolean run = true;
                sprite = dieSprite;
                frame = 0;
                int count = posY;

                while (run) {
                    while (frame < columnsDie - 1) {
                        try {
                            sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        posY -= 15;
                        frame++;
                        sprite.setAppereance(frame);
                    }

                    while (count < 496) {
                        try {
                            sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        posY += 1;
                    }
                    run = false;
                }
            }
        }
    }


    public boolean intersectX(ArrayList<Rectangle> tilesColliders) {

        Iterator<Rectangle> iterator = tilesColliders.iterator();
        while (iterator.hasNext()) {
            Rectangle tile = iterator.next();
            if (tile != null) {
                if (tile.intersectsLine(
                        getCollider().getMinX() + 1,
                        getCollider().getMinY(),
                        getCollider().getMinX(),
                        getCollider().getMaxY()
                )
                        || tile.intersectsLine(
                        getCollider().getMaxX(),
                        getCollider().getMinY(),
                        getCollider().getMaxX(),
                        getCollider().getMaxY()
                )) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean intersectY(ArrayList<Rectangle> tilesColliders) {
        Iterator<Rectangle> iterator = tilesColliders.iterator();
        while (iterator.hasNext()) {
            Rectangle tile = iterator.next();
                if (tile != null) {
                    if (tile.intersectsLine(
                            getCollider().getMinX() + 1,
                            getCollider().getMaxY() + 1,
                            getCollider().getMaxX() - 1,
                            getCollider().getMaxY() + 1)) {
                        return (true);
                    }
                }
            }


        return (false);
    }

    public abstract void move(ArrayList<Rectangle> rectanglesColliders);

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }


}
