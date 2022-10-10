package model.Enemies;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Mushroom extends WalkingEnemy {
	private static final String pathRun="src/img/Enemies/Mushroom/Run.png";
	private static final String pathDie="src/img/Enemies/Mushroom/Die.png";
	private static final int columnsRun = 16;
	private static final int columnsDie = 7;
	
	public Mushroom(int posX, int posY, ArrayList<Rectangle> tilesColliders) {
		super(posX, posY, pathRun, pathDie, columnsRun, columnsDie, tilesColliders);
	}

}
