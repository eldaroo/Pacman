package model;

import java.util.ArrayList;
import java.util.Random;

import sounds.Sounds;

public class Ghost extends Creature {

	private Square target ;
	private int intelligence;
	public static enum GhostState {ALIVE,DEATH,PUSSY,EATED};
	
	public Ghost(String name, Square position, int intelligence) {
		super(name);
		this.position = position;
		this.intelligence=intelligence;
	}

	//@Override
	public  Square isDead(ArrayList<Square> hellZone)
	{
		target = hellZone.get(10);
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

	//DECIDE LA DIRECCION
	public void pathFinder(Creature pacman, GameState gameState) {
		//el objetivo cambia en funcion al estado del juego
	
		Random random = new Random();
		ArrayList<Direction> potentialDirectionsList = new ArrayList<Direction>();
		int stupidity = 2;
		Direction randomChoise = randomPotentialDirection(); //seteamos la estupides
		Direction smartChoise = smartPotentialDirection(target, gameState); // seteamos la inteligencia

		//y las agregamos a un array, en proporciones dadas por la inteligencia de cada ghost
		for (int i = 0; i < intelligence; i++) {
			potentialDirectionsList.add(smartChoise);
		}
		/*for (int i = 0; i < stupidity; i++) {
			directionsAvailables.add(randomChoise);
		}*/
		int aux = random.nextInt(potentialDirectionsList.size());
		System.out.println(potentialDirectionsList.get(aux));
		setPotentialDirection(potentialDirectionsList.get(aux));
		if (gameState.equals(GameState.SUPERMODE))
		{
			setPotentialDirection(randomChoise);
		}

	}

	public Square getTarget() {
		return target;
	}

	public void setTarget(Square target) {
		this.target = target;
	}

	//CREA UNA POSIBILIDAD COMPLETAMENTE ALEATORIA
	public Direction randomPotentialDirection() {
		Random random = new Random();
		ArrayList<Direction> directionsAvailables = new ArrayList<Direction>();
		int aux = 0;

		if ((position.getDown().isNavegable(this)) && (direction != Direction.UP)) {
			directionsAvailables.add(Direction.DOWN);
		}
		if ((position.getLeft().isNavegable(this)) && (direction != Direction.RIGHT)) {
			directionsAvailables.add(Direction.LEFT);
		}
		if ((position.getRight().isNavegable(this)) && (direction != Direction.LEFT)) {
			directionsAvailables.add(Direction.RIGHT);
		}
		if ((position.getUp().isNavegable(this)) && (direction != Direction.DOWN)) {
			directionsAvailables.add(Direction.UP);
		}

		aux = random.nextInt(directionsAvailables.size());
		return directionsAvailables.get(aux);
	}

	//CREA UNA POSIBILIDAD DE DIRECCION EN FUNCION AL OBJETIVO
	public Direction smartPotentialDirection(Square target, GameState gameState) {

		Position targetPosition = target.getBoardPosition();
		Position ghostPosition = position.getBoardPosition();
		Random random = new Random();
		ArrayList<Direction> smartDirectionsAvailables = new ArrayList<Direction>();
		int aux = 0;

		if ((position.getDown().isNavegable(this))/* && (direction != Direction.UP)*/
				&& (targetPosition.getY() >= ghostPosition.getY())) {
			
			//Si esta en supermode que huya del pacman!
			/*if((gameState.equals(GameState.SUPERMODE))&&(position.getUp().isNavegable(this))&&(this.alive)) {
				smartDirectionsAvailables.add(Direction.UP);
			}else*/ 
				smartDirectionsAvailables.add(Direction.DOWN); //sino le agregamos la opcion inteligente
		}
		if ((position.getLeft().isNavegable(this))/* && (direction != Direction.RIGHT)*/
				&& (targetPosition.getX() <= ghostPosition.getX())) {
			
			//Si esta en supermode que huya del pacman!
			/*if((gameState.equals(GameState.SUPERMODE))&&(position.getRight().isNavegable(this))&&(this.alive)) {
				smartDirectionsAvailables.add(Direction.RIGHT);
			}else*/ smartDirectionsAvailables.add(Direction.LEFT); //sino le agregamos la opcion inteligente
		}
	
		if ((position.getRight().isNavegable(this)) /*&& (direction != Direction.LEFT)*/
				&& (targetPosition.getX() >= ghostPosition.getX())) {

			//Si esta en supermode que huya del pacman!
			/*if((gameState.equals(GameState.SUPERMODE))&&(position.getLeft().isNavegable(this))&&(this.alive)) {
				smartDirectionsAvailables.add(Direction.LEFT);
			}else*/ smartDirectionsAvailables.add(Direction.RIGHT); //sino le agregamos la opcion inteligente
		}
		if ((position.getUp().isNavegable(this)) /*&& (direction != Direction.DOWN)*/
				&& (targetPosition.getY() <= ghostPosition.getY())) {

			//Si esta en supermode que huya del pacman!
			/*if((gameState.equals(GameState.SUPERMODE))&&(position.getUp().isNavegable(this))&&(this.alive)) {
				smartDirectionsAvailables.add(Direction.DOWN);
			}else*/ smartDirectionsAvailables.add(Direction.UP); //sino le agregamos la opcion inteligente
		}

		if (smartDirectionsAvailables.size() > 0) {
			aux = random.nextInt(smartDirectionsAvailables.size());
			// System.out.println(smartDirectionsAvailables.get(aux));
			return smartDirectionsAvailables.get(aux);
		}
		return Direction.LEFT;
	}

	//EN PROCESO
	public Direction newSmartPotentialDirection()
	{
		ArrayList<Direction> SmartDirectionAvailables = new ArrayList<Direction>(); 
		Direction BestChoise = null;
		return BestChoise;
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
	
}
