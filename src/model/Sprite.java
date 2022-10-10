package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite{
	private BufferedImage spriteSheet;   
	private int width, height;
	private int columns;
	private BufferedImage[] sprites;
	private int appereance;
	
	public Sprite(File file, int appereance, int columns) throws IOException {
		spriteSheet = ImageIO.read(file);
		this.appereance=appereance;
		
		this.width = spriteSheet.getWidth()/columns;
		this.height = spriteSheet.getHeight();

		this.columns = columns;
		
		sprites = new BufferedImage[columns];
			for(int i = 0; i < columns; i++) {
				sprites[i] = spriteSheet.getSubimage(width*i, 0, width, height);
		}
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public int getAppereance() {
		return appereance;
	}

	public void setAppereance(int appereance) {
		this.appereance = appereance;
	}
	
}