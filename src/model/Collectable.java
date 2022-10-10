package model;

import model.Observer.Subject;

import java.awt.Rectangle;
import java.io.File;
import java.util.Iterator;

public class Collectable extends Subject {
    int posX, posY, frame;
    Sprite sprite, collectedSprite;
    Rectangle collider;
    CollectedAnimation collectedAnimation;
    boolean collectedAnimationControl;

    public Collectable(int posX, int posY, String path, int columns) {
        this.posX = posX;
        this.posY = posY;

        try {
            this.sprite = new Sprite(new File(path), 0, columns);
            this.collectedSprite = new Sprite(new File("src/img/Collectable/Collected.png"), 0, 6);
        } catch (Exception e) {
            System.out.println(e);
        }

        this.collectedAnimationControl = false;

        collider = new Rectangle(sprite.getWidth() - 1, sprite.getHeight() - 1);
        collider.x = posX;
        collider.y = posY;

        IdleAnimation idleAnimation = new IdleAnimation();
        idleAnimation.start();
    }

    public void startCollectedAnimation(Iterator<Collectable> iterator) {
        collectedAnimation = new CollectedAnimation(iterator);
        collectedAnimation.start();
    }

    private class IdleAnimation extends Thread {
        public void run() {
            synchronized (this) {
                while (true) {
                    if (frame == 16) frame = 0;
                    else frame++;
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private class CollectedAnimation extends Thread {
        private Iterator<Collectable> iterator;

        public CollectedAnimation(Iterator<Collectable> iterator) {
            this.iterator = iterator;
        }

        public void run() {

            sprite = collectedSprite;
            collectedAnimationControl = true;
            frame = 0;

            while (frame < 5) {
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame++;
            }
            iterator.remove();
        }
    }

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

    public Sprite getIdleSprite() {
        return sprite;
    }

    public void setIdleSprite(Sprite idleSprite) {
        this.sprite = idleSprite;
    }

    public Sprite getCollectedSprite() {
        return collectedSprite;
    }

    public void setCollectedSprite(Sprite collectedSprite) {
        this.collectedSprite = collectedSprite;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public CollectedAnimation getCollectedAnimation() {
        return collectedAnimation;
    }

    public void setCollectedAnimation(CollectedAnimation collectedAnimation) {
        this.collectedAnimation = collectedAnimation;
    }

    public boolean isCollectedAnimationControl() {
        return collectedAnimationControl;
    }

    public void setCollectedAnimationControl(boolean collectedAnimationControl) {
        this.collectedAnimationControl = collectedAnimationControl;
    }

}
