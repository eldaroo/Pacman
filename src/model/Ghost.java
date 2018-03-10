package model;

import java.util.ArrayList;
import java.util.Random;

import sounds.Sounds;

public class Ghost extends Creature {

	Position target ;
	int intelligence;
	Intelligence ArtificialIntelligence;
	public Ghost(String name, Square position, int intelligence) {
		super(name);
		this.position = position;
		this.intelligence=intelligence;
	}

	//@Override
	public  Position isDead(ArrayList<Square> hellZone)
	{
		target = hellZone.get(10).getBoardPosition();
		return target;
	}
	
	public GameState eatingPacman(Pacman pacman, Board board, GameState gameState) {
		if (getPosition().equals(pacman.getPosition()) ) {
			
			sounds.reproduceDeath();
			
			board.lifes--;
			pacman.setAlive(false);
			gameState = gameState.RESPAWN;
		}
		return gameState;

	}

	public void pathFinder(Creature pacman, GameState gameState) {
		ArtificialIntelligence = new Intelligence (this);
		setPotentialDirection( ArtificialIntelligence.getDecisionNormalAlive());
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void inHell () {
		//boolean
	}
	//for (Creatures ghost : Creatures)
	public Position getTarget() {
		return target;
	}

	public void setTarget(Position position) {
		this.target = position;
	}

	
}
