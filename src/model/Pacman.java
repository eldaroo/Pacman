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
	
	public void eatDot(Dot dot)
	{
		Sounds.reproduceEatDot();
		Board.upScore(10,0);
		Board.setDotRemoved((Dot) dot);
		
		if (Board.getDotRemoved().getSuper() == true) {
			Game.setGameState (GameState.SUPERMODE);
			Board.upScore(20, 0);
		}
		Board.setPacmanEatNewDot(true);
	}
	
	public void eatFruit()
	{
		Board.upScore(200,0);
	}
	
	public void eatGhost(Ghost ghost, int ghostEated)
	{
		ghostEated++;
		Sounds.reproduceEatGhost();
		ghost.setGhostState(GhostState.EATED);
		Board.upScore(50, ghostEated);
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