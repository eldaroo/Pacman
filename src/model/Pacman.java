package model;



import java.util.ArrayList;

import controller.Game;
import model.Fruit.FruitType;
import model.Ghost.GhostState;
import sounds.Sounds;

public class Pacman extends Creature  {

	private boolean eatingGhost = false;
	public static enum PacmanState {MOVE,EATGHOST,DEATH};
	private static PacmanState pacmanState = PacmanState.MOVE;
	private ArrayList<Dot> dots;
	

	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
		setKeyOfHell(false);
	}
	

	public void death()
	{
		Board.subtractLife();
	}

	public boolean isEatingGhost() {
		return eatingGhost;
	}

	public void setEatingGhost(boolean eatingGhost) {
		this.eatingGhost = eatingGhost;
	}

	public static void setPacmanState(PacmanState pacmanState) {
		Pacman.pacmanState = pacmanState;
	}
	public static PacmanState getPacmanState () {
		return pacmanState;
	}
	
}