package model;

import java.util.ArrayList;
import java.util.Random;

import controller.Game;
import model.Pacman.PacmanState;

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
	private Board board;
	private int aux;
	private Intelligence iA_Ghost;
	private int hellTime;

	public Ghost(String name, Square position, int intelligence, Board board) {
		super(name);
		this.position = position;
		this.intelligence = intelligence;
		this.target = position.getBoardPosition();
		this.board = board;
		setKeyOfHell(true);
	}

	public void run(Pacman pacman) throws InterruptedException {

		// CALCULA EL PATHFINDER SEGUN EL ESTADO
		switch (ghostState) {

		case COURAGEOUS:
			setTarget(pacman.position.getBoardPosition());
			break;
		case DEATH:
			setTarget(board.getBoard()[board.getHellGate().boardPosition.getX()][board.getHellGate().boardPosition
					.getY()].boardPosition);
			keyOfHell = true;
			break;
		case PUSSY:
			setTarget(pacman.getBoardPosition());
			break;
		case EATED:
			Thread.sleep(150);
			setGhostState(GhostState.DEATH);
			//eatGhostTime = 0;
			break;
		case INHELL:
			setTarget(pacman.position.getBoardPosition());
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
	}
		
	private void goingThroughHellGate() {

		if (position.equals(
				board.getBoard()[board.getHellGate().boardPosition.getX()][board.getHellGate().boardPosition.getY()])) {
			keyOfHell = !keyOfHell;

			if (ghostState.equals(GhostState.DEATH)) {
				ghostState = GhostState.INHELL;
			}
		}
	}

	public void eatingPacman(Pacman pacman, Board board) {
		if (pacman.getBoardPosition().equals(getBoardPosition())) 
		{
			sounds.reproduceDeath();
			pacman.death();
			pacman.setPacmanState(PacmanState.DEATH);
			Game.setGameState(GameState.RESPAWN);
		}
	
		iA_Ghost = new Intelligence(this);
		
		if (ghostState.equals(GhostState.DEATH))
			setPotentialDirection(iA_Ghost.getSmartChoise());
		else
			pathFinder();
		move();
		goingThroughHellGate();

	}

	public void pathFinder() {
		random = new Random();
		potentialDirectionsList = new ArrayList<Direction>();
		stupidity = 10 - getIntelligence();

		// y las agregamos a un array, en proporciones dadas por la inteligencia de cada
		// ghost
		for (int i = 0; i < getIntelligence(); i++) {
			if (getGhostState().equals(GhostState.PUSSY)) {
				potentialDirectionsList.add(iA_Ghost.getGoAwayDirection());
			} else {
				potentialDirectionsList.add(iA_Ghost.getSmartChoise());
			}
		}
		for (int i = 0; i < stupidity; i++) {
			potentialDirectionsList.add(iA_Ghost.getRandomChoise());
		}

		aux = random.nextInt(potentialDirectionsList.size());

		setPotentialDirection(potentialDirectionsList.get(aux));
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