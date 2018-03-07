package model;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Creature {

	public Ghost(String name, Square position) {
		super(name);
		this.position = position;
	}

	//@Override
	public  void isDead(Square target, Square[] hellZone)
	{
		//target = hellZone.get(10);
	}
	
	public void eatPacman(Pacman pacman, Board board) {
		if (getBoardPosition() == pacman.getBoardPosition()) {
			board.lifes--;
			pacman.isDead();
		}
	}

	public void pathFinder(Square target, int inteligence) {
		Random random = new Random();
		ArrayList<Direction> directionsAvailables = new ArrayList<Direction>();
		int stupidity = 10 - inteligence;
		Direction randomChoise = randomPotentialDirection();
		Direction smartChoise = smartPotentialDirection(target);

		for (int i = 0; i < inteligence; i++) {
			directionsAvailables.add(smartChoise);
		}
		for (int i = 0; i < stupidity; i++) {
			directionsAvailables.add(randomChoise);
		}
		int aux = random.nextInt(directionsAvailables.size());
		setPotentialDirection(directionsAvailables.get(aux));

	}

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

	public Direction smartPotentialDirection(Square target) {

		Position targetPosition = target.getBoardPosition();
		Position ghostPosition = position.getBoardPosition();
		Random random = new Random();
		ArrayList<Direction> smartDirectionsAvailables = new ArrayList<Direction>();
		int aux = 0;

		if ((position.getDown().isNavegable(this)) && (direction != Direction.UP)
				&& (targetPosition.getY() >= ghostPosition.getY())) {
			smartDirectionsAvailables.add(Direction.DOWN);
		}
		if ((position.getLeft().isNavegable(this)) && (direction != Direction.RIGHT)
				&& (targetPosition.getX() <= ghostPosition.getX())) {
			smartDirectionsAvailables.add(Direction.LEFT);
		}
		if ((position.getRight().isNavegable(this)) && (direction != Direction.LEFT)
				&& (targetPosition.getX() >= ghostPosition.getX())) {
			smartDirectionsAvailables.add(Direction.RIGHT);
		}
		if ((position.getUp().isNavegable(this)) && (direction != Direction.DOWN)
				&& (targetPosition.getY() <= ghostPosition.getY())) {
			smartDirectionsAvailables.add(Direction.UP);
		}

		if (smartDirectionsAvailables.size() > 0) {
			aux = random.nextInt(smartDirectionsAvailables.size());
			// System.out.println(smartDirectionsAvailables.get(aux));
			return smartDirectionsAvailables.get(aux);
		}
		return Direction.LEFT;
	}

	
}
