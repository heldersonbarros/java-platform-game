package model.Enemies.IdleEnemies;

import model.Enemies.IdleEnemies.IdleEnemy;
import model.Sprite;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Chameleon extends IdleEnemy {
    private static final String pathRun="src/img/Enemies/Turtle/Idle.png";
    private static final String pathDie="src/img/Enemies/Turtle/Die.png";
    private static final int columnsRun = 14;
    private static final int columnsDie = 5;

    public Chameleon(int posX, int posY, ArrayList<Rectangle> tilesColliders, boolean move) {
        super(posX, posY, pathRun, pathDie, columnsRun, columnsDie, tilesColliders);

        try {
            this.attackSprite =  new Sprite(new File("src/img/Enemies/Turtle/Spikes in.png"), 0, 8);
            this.idleSprite = new Sprite(new File("src/img/Enemies/Turtle/Idle.png"),0,14);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attack() {
        getCollider().x -= 4;
        getCollider().width += 4;
    }

    @Override
    public void disarm() {
        getCollider().x = getPosX();
        getCollider().width = getSprite().getWidth();
    }
}