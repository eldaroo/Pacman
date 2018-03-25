package model;



import java.util.ArrayList;

import controller.Game;
import model.Fruit.FruitType;
import model.Ghost.GhostState;
import sounds.Sounds;

public class Pacman extends Creature {

	private boolean eatingGhost = false;
	public static enum PacmanState {MOVE,EATGHOST,DEATH};
	private static PacmanState pacmanState = PacmanState.MOVE;
	

	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
		setKeyOfHell(false);
	}
	
	public void run(Board board)
	{
		move();
		Eating.eatingDot(board);
		Eating.eatingFruit();
	}

	
	
	public void death()
	{
		Board.lostLife();
	}

	public boolean isEatingGhost() {
		return eatingGhost;
	}

	public void setEatingGhost(boolean eatingGhost) {
		this.eatingGhost = eatingGhost;
	}

	public void setPacmanState(PacmanState pacmanState) {
		Pacman.pacmanState = pacmanState;
	}
	public PacmanState getPacmanState () {
		return pacmanState;
	}
	
}