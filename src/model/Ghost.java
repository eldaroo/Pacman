package model;

import java.util.ArrayList;
import java.util.Random;

import controller.Game;
import model.Pacman.PacmanState;

public class Ghost extends Creature {

	private static ArrayList<Direction> potentialDirectionsList;

	private Position target;
	private int intelligence;

	public static enum GhostState {
		COURAGEOUS, DEATH, PUSSY, EATED, INHELL, HURRY
	};

	private int stupidity;
	private GhostState ghostState = GhostState.COURAGEOUS;
	private Random random;
	private Board board;

	private int aux;

	Intelligence iA_Ghost;

	private int hellTime;

	public Ghost(String name, Square position, int intelligence, Board board) {
		super(name);
		this.position = position;
		this.intelligence = intelligence;
		this.target = position.getBoardPosition();
		this.board = board;
		setKeyOfHell(true);
	}

	protected void goingThroughHellGate() {

		if (position.equals(
				board.getBoard()[board.getHellGate().boardPosition.getX()][board.getHellGate().boardPosition.getY()])) {
			keyOfHell = !keyOfHell;

			if (ghostState.equals(GhostState.DEATH)) {
				ghostState = GhostState.INHELL;
			}
		}
	}

	public void eatingPacman(Pacman pacman, Board board) {
		if (getPosition().equals(pacman.getPosition())) {

			sounds.reproduceDeath();

			board.lifes--;
			pacman.setAlive(false);
			pacman.setPacmanState(PacmanState.DEATH);
			Game.setGameState(GameState.RESPAWN);
		}

	}

	public void run(Pacman pacman) throws InterruptedException {

		switch (ghostState) {

		// CALCULA EL PATHFINDER PARA CUANDO ESTA VIVO
		case COURAGEOUS:
			setTarget(pacman.position.getBoardPosition());

			break;
		// CALCULA EL PATHFINDER PARA CUANDO ESTA MUERTO, LO MANDA A LA HELLGATE
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
		default:
			break;
		}
		iA_Ghost = new Intelligence(this);
		// GENERA LAS BESTCHOISE PARA CADA ESTADO

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

	// for (Creatures ghost : Creatures)
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
