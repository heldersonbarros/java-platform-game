package model.AbstractFactory;

import model.Enemies.IdleEnemies.IdleEnemy;
import model.Enemies.WalkingEnemy;

import java.awt.*;
import java.util.ArrayList;

public abstract class EnemiesFactory {
    public EnemiesFactory(){

    }

    private static EnemiesFactory instance;

    public static synchronized EnemiesFactory getEnemiesFactory(){
        if (instance == null){
            if(true) {
                instance = new EasyEnemiesFactory();
            }
            else{
                instance = new HardEnemiesFactory();
            }
        }

        return instance;
    }
    public abstract IdleEnemy getIdleEnemy(int posX, int posY, ArrayList<Rectangle> rectangles);
    public abstract WalkingEnemy getWalkingEnemy(int posX, int posY, ArrayList<Rectangle> rectangles);
}
