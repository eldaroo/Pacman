package model;

import java.util.ArrayList;
import java.util.Random;

import sounds.Sounds;

public class Ghost extends Creature {

	private static ArrayList<Direction> potentialDirectionsList;

	private Position target ;
	private int intelligence;
	public static enum GhostState {COURAGEOUS,DEATH,PUSSY,EATED};
	private int stupidity;
	private GhostState ghostState = GhostState.COURAGEOUS;
	private Position initialPosition ;
	private Random random ;

	private int aux;



	Intelligence iA_Ghost;
	
	
	public Ghost(String name, Square position, int intelligence) {
		super(name);
		this.position = position;
		this.initialPosition = position.getBoardPosition();
		this.intelligence=intelligence;
		this.target = position.getBoardPosition();
		setKeyOfHell(true);
	}

	protected boolean isInHell()
	{
		if (position.equals(HellGate.boardPosition))
		{
			System.out.println("esta en el infierno");

			return true;
		} else return false;
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

	public void run(Creature pacman, GameState gameState) {
		
		//GENERA LAS BESTCHOISE PARA CADA ESTADO
		
		
		switch (ghostState) {
		
		//CALCULA EL PATHFINDER PARA CUANDO ESTA VIVO
		case COURAGEOUS:
			setTarget(pacman.position.getBoardPosition());
			
			break;
			//CALCULA EL PATHFINDER PARA CUANDO ESTA MUERTO
		case DEATH:
			setGhostState(GhostState.EATED);
			break;
		case PUSSY:
			setTarget(pacman.getBoardPosition());
			
			break;
		case EATED:
			setTarget(initialPosition);
			keyOfHell = true;
			break;
		}
		iA_Ghost = new Intelligence (this);
		pathFinder();
		move();
		
	}

	public void pathFinder()
	{
		random = new Random();
		potentialDirectionsList = new ArrayList<Direction>();
	    stupidity = 10 - getIntelligence();
		
		//y las agregamos a un array, en proporciones dadas por la inteligencia de cada ghost
		for (int i = 0; i < getIntelligence(); i++) {
			if(getGhostState().equals(GhostState.PUSSY))
			{
			potentialDirectionsList.add(iA_Ghost.getGoAwayDirection());
			}else {
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
	public GhostState getGhostState() {
		return ghostState;
	}

	public void setGhostState(GhostState ghostState) {
		this.ghostState = ghostState;
	}
	
}
