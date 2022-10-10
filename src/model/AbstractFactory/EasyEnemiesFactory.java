package model.AbstractFactory;

import model.Enemies.IdleEnemies.IdleEnemy;
import model.Enemies.Mushroom;
import model.Enemies.IdleEnemies.Turtle;
import model.Enemies.WalkingEnemy;

import java.awt.*;
import java.util.ArrayList;

public class EasyEnemiesFactory extends EnemiesFactory{
    @Override
    public IdleEnemy getIdleEnemy(int posX, int posY, ArrayList<Rectangle> rectangles) {
        return new Turtle(posX, posY, rectangles);
    }

    @Override
    public WalkingEnemy getWalkingEnemy(int posX, int posY, ArrayList<Rectangle> rectangles) {
        return new Mushroom(posX, posY, rectangles);
    }
}
