package model;

import java.util.ArrayList;
import java.util.Random;

import controller.Game;
import sounds.Sounds;

public class Ghost extends Creature {

	private static ArrayList<Direction> potentialDirectionsList;

	public static enum GhostState {
		COURAGEOUS, DEATH, PUSSY, EATED, INHELL, HURRY
	};

	private Position target;
	private int intelligence;
	private int stupidity;
	private GhostState ghostState = GhostState.INHELL;
	private Random random;
	private int hellTime=0;
	private IA iAGhost;
	private int hellRequiredTime;
	 int auxForRetarded=0;

	public Ghost(String name, Square position, int intelligence) {
		super(name);
		this.position = position;
		this.intelligence = intelligence;
		this.hellRequiredTime= intelligence*10;
		this.target = position.getBoardPosition();
		setKeyOfHell(true);
	}

	public void run(Pacman pacman) throws InterruptedException {

		
		// CALCULA EL PATHFINDER SEGUN EL ESTADO
		IA.runIa(this);
		switch (ghostState) {

		case COURAGEOUS:
			setTarget(pacman.getBoardPosition());
			potentialDirection= IA.pathFinder(intelligence);
			break;
		case DEATH:
			setTarget(Board.getHellGatePosition());
			potentialDirection= IA.pathFinder(9);
			keyOfHell = true;
			break;
		case PUSSY:
			setTarget(pacman.getBoardPosition());
			potentialDirection= IA.pathFinder(intelligence);
			break;
		case EATED:
			setGhostState(GhostState.DEATH);
			break;
		case INHELL:
			potentialDirection=IA.randomDirection();
			determinatePermanenceInHell();
			break;
		case HURRY:
			break;
		}
		
		move();
		checkGoingThroughHellGate();
	}

	private void determinatePermanenceInHell() {
		if (hellTime == hellRequiredTime) {
			setTarget(Board.getHellGatePosition());
			potentialDirection=IA.pathFinder(intelligence);
			setKeyOfHell(true);
			
		} else
			hellTime++;		
	}

	private void checkGoingThroughHellGate() {

		if (position.boardPosition.equals(Board.getHellGatePosition())) {
			keyOfHell = !keyOfHell;

			if (ghostState.equals(GhostState.DEATH)) {
				ghostState = GhostState.INHELL;
			}else if (ghostState.equals(GhostState.INHELL))
			{
				ghostState = GhostState.COURAGEOUS;
			}
		}
	}

	public void eatPacman(Pacman pacman) {

		Sounds.reproduceDeath();
		pacman.death();
		Game.setGameState(GameState.RESPAWN);

	}



	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void inHell() {
		// boolean
	}

	public Position getTarget() {
		return target;
	}

	public void setTarget(Position position) {
		this.target = position;
	}

	public GhostState getGhostState() {
		return ghostState;
	}

	public void setGhostState(GhostState ghostState) {
		this.ghostState = ghostState;
	}

	public void setHellTime(int i) {
		hellTime=i;
	}

}