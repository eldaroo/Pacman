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
	private int ghostsEated = 0;
	

	

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
		ghostsEated++;
		Sounds.reproduceEatGhost();
		setPacmanState(PacmanState.EATGHOST);
		ghost.setGhostState(GhostState.EATED);
		Board.upScore(50, ghostsEated);
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
	
	public void	accumulateGhostsEated () {
		ghostsEated++;
	}
	
	public void resetGhostEated () {
		ghostsEated = 0;
	}

	public void setPacmanState(PacmanState pacmanState) {
		Pacman.pacmanState = pacmanState;
	}
	public PacmanState getPacmanState () {
		return pacmanState;
	}

	public int getGhostsEated() {
		// TODO Auto-generated method stub
		return ghostsEated;
	}
	
}