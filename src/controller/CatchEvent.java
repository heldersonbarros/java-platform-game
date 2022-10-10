package controller;

import model.Observer.Subject;
import model.Collectable;

import java.awt.*;
import java.util.Iterator;

public class CatchEvent extends Subject {
    public void check(Iterator<Collectable> iterator, Rectangle playerCollider){
        while (iterator.hasNext()) {
            Collectable collectable = iterator.next();
            if (collectable.getCollider().intersects(playerCollider)) {
                if (!collectable.isCollectedAnimationControl()) {
                    collectable.startCollectedAnimation(iterator);
                    notifyObservers();
                }
                break;
            }
        }
    }
}
