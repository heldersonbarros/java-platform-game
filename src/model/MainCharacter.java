package model;

import view.Game;

import java.awt.Rectangle;
import java.util.ArrayList;

public class MainCharacter extends Entity2D {
	boolean falling, jumping, doubleJumping, canDoubleJump, wallJump, dead;
	ArrayList<Rectangle> tilesColliders;

	public MainCharacter(int posX, int posY, String pathRun, String pathDie, int columnsRun,
						 int columnsDie, ArrayList<Rectangle> tilesColliders) {
		super(posX, posY, pathRun, pathDie, columnsRun, columnsDie);
		this.tilesColliders = tilesColliders;

		MoveThread moveThread= new MoveThread();
		moveThread.start();
		AnimationThread animationThread = new AnimationThread();
		animationThread.start();
	}

	public class MoveThread extends Thread {
		public void run() {
			while(!dead) {
				try {
					sleep(8);
					move(tilesColliders);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public class AnimationThread extends Thread {
		public void run() {
			synchronized (this)
			{
				while(true) {
					if(!falling && !dead) {
						if (getFrame() == 11) setFrame(0);
						else setFrame(getFrame()+1);
					} else if(doubleJumping) {
						setFrame(getFrame()+1);
						if (getFrame() >= 19) {
							doubleJumping = false;
							falling=true;
							setFrame(12);
						}
					}
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					getSprite().setAppereance(getFrame());
				}
			}
		}
	}

	@Override
	public void move(ArrayList<Rectangle> rectanglesColliders) {
		boolean intersectX = intersectX(rectanglesColliders);
		boolean intersectY = intersectY(rectanglesColliders);

		if (intersectX && !intersectY) {
			setPosY(getPosY()+1);
			getCollider().y = getPosY();
			getSprite().setAppereance(4);
			wallJump = true;
		}
		else if (intersectX && intersectY) {
			if(isDirection()) {
				setDirection(false);
				setPosX(getPosX()+1);
				getCollider().x = getPosX();
			} else {
				setDirection(true);
				setPosX(getPosX()-1);
				getCollider().x = getPosX();
			}
			if (jumping || doubleJumping) {
				setFrame(0);
			}
			jumping = false;
			doubleJumping =false;
			canDoubleJump=true;
			falling = false;
			wallJump = false;
		}


		else {
			if (!intersectY) {
				if (doubleJumping) {
					if (getFrame() >= 19) {
						doubleJumping = false;
						if(!dead) {
							getSprite().setAppereance(12);
						}
					}
				}else {
					if(!dead) {
						getSprite().setAppereance(12);
					}
				}
				setPosY(getPosY()+1);
				getCollider().y = getPosY();
				falling=true;

			} else {
				falling = false;
				if (jumping || doubleJumping) {
					setFrame(0);
				}
				jumping = false;
				doubleJumping = false;
				canDoubleJump = true;
				wallJump = false;
			}

			if(!intersectX) {
				if (isDirection()) {
					setPosX(getPosX() - 1);
				}

				else {
					setPosX(getPosX() + 1);
				}

				if (!falling) {
					getSprite().setAppereance(getFrame());
				}

				getCollider().x = getPosX();

				wallJump= false;
			}

		}

	}

	public void jump(ArrayList<Rectangle> rectanglesColliders) {
		if (!jumping) {
			setPosY(getPosY() -50);
			getCollider().y = getPosY();
			setFalling(true);
			jumping = true;
			setFrame(12);
		}
		else if(wallJump) {
			if(isDirection()) {
				setDirection(false);
				setPosX(getPosX()+8);
			} else {
				setDirection(true);
				setPosX(getPosX()-8);
			}
			setPosY(getPosY() -50);
			getCollider().y = getPosY();
			setPosX(getPosX());
			getCollider().x = getPosX();
			setFalling(true);
			jumping = true;

		} else {
			if (!doubleJumping && canDoubleJump) {
				setPosY(getPosY() - 50);
				getCollider().y = getPosY();
				setFalling(true);
				doubleJumping = true;
				setFrame(14);
				canDoubleJump=false;
			}
		}
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isDoubleJumping() {
		return doubleJumping;
	}

	public void setDoubleJumping(boolean doubleJumping) {
		this.doubleJumping = doubleJumping;
	}

	public boolean isCanDoubleJump() {
		return canDoubleJump;
	}

	public void setCanDoubleJump(boolean canDoubleJump) {
		this.canDoubleJump = canDoubleJump;
	}

	public boolean isWallJump() {
		return wallJump;
	}

	public void setWallJump(boolean wallJump) {
		this.wallJump = wallJump;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public ArrayList<Rectangle> getTilesColliders() {
		return tilesColliders;
	}

	public void setTilesColliders(ArrayList<Rectangle> tilesColliders) {
		this.tilesColliders = tilesColliders;
	}
}
