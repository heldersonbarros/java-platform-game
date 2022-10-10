package model.AbstractFactory;

import model.Enemies.IdleEnemies.IdleEnemy;
import model.Enemies.WalkingEnemy;

import java.awt.*;
import java.util.ArrayList;

public class HardEnemiesFactory extends EnemiesFactory{
    @Override
    public IdleEnemy getIdleEnemy(int posX, int posY, ArrayList<Rectangle> rectangles) {
        return null;
    }

    @Override
    public WalkingEnemy getWalkingEnemy(int posX, int posY, ArrayList<Rectangle> rectangles) {
        return null;
    }
}
