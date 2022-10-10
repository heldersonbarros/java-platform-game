package view;

import model.*;
import model.AbstractFactory.EnemiesFactory;
import model.Enemies.*;
import model.Enemies.IdleEnemies.IdleEnemy;
import model.Score;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    private Layer backgroundBlue, terrain, traps, collectable;
    private MainCharacter mainCharacter;
    private Sprite cherries;
    private BufferedImage frame, bg;
    private Timer moveCharacterTimer;
    private final Font font;

    private ArrayList<Enemy> enemies;
    private Score score;

    public Map(){
        frame = new BufferedImage(1776, 800, BufferedImage.TYPE_4BYTE_ABGR);
        score = new Score();
        enemies = new ArrayList<Enemy>();

        moveCharacterTimer = new Timer(50, null);
        terrain = new Layer(50, 111, 16, 16, "src/img/Terrain/terrain.png", "/img/Fase/Terrain.txt", true, false, false);

        traps = new Layer(50, 111, 16, 16, "src/img/Spikes/Spikes.png", "/img/Fase/Traps.txt", true, false, false);
        collectable = new Layer(50, 111, 16, 16, "src/img/Collectable/Cherry.png", "/img/Fase/Fruits.txt", false, true, false);

        try {
            bg = ImageIO.read(new File("src/img/Background/bg.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        font = new Font("Arial", Font.BOLD, 8);

        try {
            cherries = new Sprite(new File("src/img/Collectable/Cherries.png"), 0, 17);
        } catch (IOException e) {
            e.printStackTrace();
        }

        terrain.build(1776, 800);
        traps.build(1776, 800);
        collectable.build(1776, 800);
        mainCharacter = new MainCharacter(380, 300, "src/img/Main Characters/Mask Dude/Sprite.png", "src/img/Main Characters/Mask Dude/Die.png",
                20, 7, terrain.getRectangles());

        WalkingEnemy enemy1 = EnemiesFactory.getEnemiesFactory().getWalkingEnemy(1020, 200, terrain.getRectangles());
        IdleEnemy enemy2 = EnemiesFactory.getEnemiesFactory().getIdleEnemy(1360,280,terrain.getRectangles());
        enemies.add(enemy1);
        enemies.add(enemy2);
    }

    public void paint(Graphics g){
        frame.getGraphics().drawImage(bg, 0, 0, null);

        frame.getGraphics().drawImage(terrain.camada, 0, 0, null);
        frame.getGraphics().drawImage(traps.camada, 0, 0, null);

        int posX = mainCharacter.getPosX();
        int posY = mainCharacter.getPosY();

        for (Collectable collectable : collectable.getCollectablesColliders()) {
            frame.getGraphics().drawImage(collectable.getIdleSprite().getSprites()[collectable.getFrame()], collectable.getPosX(), collectable.getPosY(), null);
        }
        if (mainCharacter.isDirection()) {
            frame.getGraphics().drawImage(mainCharacter.getSprite().getSprites()[mainCharacter.getSprite().getAppereance()], posX + mainCharacter.getSprite().getWidth(),
                    posY, -mainCharacter.getSprite().getWidth(), mainCharacter.getSprite().getHeight(), null);
        } else {
            frame.getGraphics().drawImage(mainCharacter.getSprite().getSprites()[mainCharacter.getSprite().getAppereance()], posX, posY, null);
        }

        for (Enemy enemy : enemies) {
            if (enemy != null) {
                if (!enemy.isDirection()) {
                    frame.getGraphics().drawImage(enemy.getSprite().getSprites()[enemy.getSprite().getAppereance()], enemy.getPosX() + mainCharacter.getSprite().getWidth(),
                            enemy.getPosY(), -enemy.getSprite().getWidth(), enemy.getSprite().getHeight(), null);
                } else {
                    frame.getGraphics().drawImage(enemy.getSprite().getSprites()[enemy.getSprite().getAppereance()], enemy.getPosX(), enemy.getPosY(), null);
                }
            }
        }

        int translateX = posX - Game.WINDOW_WIDTH / 2;

        int translateY = 0;
        if (!mainCharacter.isDead()) {
            translateY = posY - Game.WINDOW_HEIGHT / 2;
        }

        frame.getGraphics().drawImage(cherries.getSprites()[0],
                translateX + Game.WINDOW_WIDTH - 80 - cherries.getWidth(),
                translateY + Game.WINDOW_HEIGHT - 20 - cherries.getHeight(), null);

        frame.getGraphics().setFont(font);
        frame.getGraphics().drawString("x"+score.getPoints(),
                translateX + Game.WINDOW_WIDTH -80,
                translateY + (Game.WINDOW_HEIGHT - 30));

        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(-translateX, -translateY);
        g2d.drawImage(frame, 0, 0, null);
    }

    public Layer getBackgroundBlue() {
        return backgroundBlue;
    }

    public void setBackgroundBlue(Layer backgroundBlue) {
        this.backgroundBlue = backgroundBlue;
    }

    public Layer getTerrain() {
        return terrain;
    }

    public void setTerrain(Layer terrain) {
        this.terrain = terrain;
    }

    public Layer getTraps() {
        return traps;
    }

    public void setTraps(Layer traps) {
        this.traps = traps;
    }

    public Layer getCollectable() {
        return collectable;
    }

    public void setCollectable(Layer collectable) {
        this.collectable = collectable;
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public Sprite getCherries() {
        return cherries;
    }

    public void setCherries(Sprite cherries) {
        this.cherries = cherries;
    }

    public BufferedImage getFrame() {
        return frame;
    }

    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    public BufferedImage getBg() {
        return bg;
    }

    public void setBg(BufferedImage bg) {
        this.bg = bg;
    }

    public Timer getMoveCharacterTimer() {
        return moveCharacterTimer;
    }

    public void setMoveCharacterTimer(Timer moveCharacterTimer) {
        this.moveCharacterTimer = moveCharacterTimer;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
