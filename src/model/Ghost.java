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
	private GhostState ghostState = GhostState.COURAGEOUS;
	private Random random;
	private int aux;
	private IA iAGhost;
	private int hellTime;

	public Ghost(String name, Square position, int intelligence) {
		super(name);
		this.position = position;
		this.intelligence = intelligence;
		this.target = position.getBoardPosition();
		setKeyOfHell(true);
	}

	public void run(Pacman pacman) throws InterruptedException {

		// CALCULA EL PATHFINDER SEGUN EL ESTADO
		switch (ghostState) {

		case COURAGEOUS:
			setTarget(pacman.getBoardPosition());
			break;
		case DEATH:
			setTarget(Board.getHellGatePosition());
			keyOfHell = true;
			break;
		case PUSSY:
			setTarget(pacman.getBoardPosition());
			break;
		case EATED:
			Thread.sleep(150);
			setGhostState(GhostState.DEATH);
			break;
		case INHELL:
			setTarget(pacman.getBoardPosition());
			if (hellTime == 100) {
				setKeyOfHell(true);
				ghostState = GhostState.COURAGEOUS;
				hellTime = 0;
			} else
				hellTime++;
			break;
		case HURRY:
			break;
		}
		IA.runIa(this);

		if (ghostState.equals(GhostState.DEATH))
			setPotentialDirection(IA.getSmartChoise());
		else
			setPotentialDirection(IA.pathFinder());
		move();
		goingThroughHellGate();
	}

	private void goingThroughHellGate() {

		if (position.boardPosition.equals(Board.getHellGatePosition())) {
			keyOfHell = !keyOfHell;

			if (ghostState.equals(GhostState.DEATH)) {
				ghostState = GhostState.INHELL;
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

}