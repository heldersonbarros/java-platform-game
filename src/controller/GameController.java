package controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import model.Collectable;
import model.Enemies.Enemy;
import view.Game;
import view.Map;

public class GameController {
    Game game;
    CatchEvent catchEvent;

    public GameController(Game game) {
        this.game = game;
        catchEvent = new CatchEvent();
    }

    public void init() {
        game.addKeyListener(new KeyListener());
        game.getMap().getMoveCharacterTimer().addActionListener(new ActionHandler());
        game.getMap().getMoveCharacterTimer().start();
        catchEvent.addObserver(game.getMap().getScore());
    }

    private class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (!game.getMap().getMainCharacter().isDead()) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    game.getMap().getMainCharacter().jump(game.getMap().getTerrain().getRectangles());
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_R){
                game.getMap().getMainCharacter().setDead(false);
                game.setMap(new Map());
                game.getMap().getMoveCharacterTimer().addActionListener(new ActionHandler());
                game.getMap().getMoveCharacterTimer().start();
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == game.getMap().getMoveCharacterTimer()) {
                catchEvent.check(game.getMap().getCollectable().getCollectablesColliders().iterator(),
                        game.getMap().getMainCharacter().getCollider());
                Iterator<Enemy> iteratorEnemies = game.getMap().getEnemies().iterator();
                while (iteratorEnemies.hasNext()) {
                    Enemy enemy = iteratorEnemies.next();
                    ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
                    try {
                        rectangles.add(enemy.getCollider());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                    if (game.getMap().getMainCharacter().intersectY(rectangles)) {
                        if (!game.getMap().getMainCharacter().isDead()) {
                            //if (enemy instanceof Turtle && !enemy.isAttacking()) {
                            enemy.setDead(true);
                            enemy.startDieAnimation();
                            enemy.setCollider(null);
                        }
                    } else if (game.getMap().getMainCharacter().intersectX(rectangles)) {
                        if (!game.getMap().getMainCharacter().isDead()) {
                            if (!game.getMap().getMainCharacter().isDead()) {
                                game.getMap().getMainCharacter().setDead(true);
                                game.getMap().getMainCharacter().startDieAnimation();
                            }
                        }
                    }
                }
            }

            if (!game.getMap().getMainCharacter().isDead()) {
                if (game.getMap().getMainCharacter().intersectX(game.getMap().getTraps().getRectangles())) {
                    game.getMap().getMainCharacter().setDead(true);
                    game.getMap().getMainCharacter().startDieAnimation();
                }
            }
        }
    }
}