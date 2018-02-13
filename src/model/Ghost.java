package model;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Creature {

	
	public Ghost(Square position) {
		this.position = position;
		identy = "Ghost";
		super.eateable = false;
	}

	public void eatPacman(Pacman pacman, Ghost ghost) {
		if (pacman.getBoardPosition().equals(ghost.getBoardPosition())&&(pacman.eateable=true)) {
			pacman.alive=false;
		}
	}

	public void pathFinder(boolean superMode) {
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
		if (superMode) {
			
			setPotentialDirection(directionsAvailables.get(aux));
			escape();
		} else {
			setPotentialDirection(directionsAvailables.get(aux));
			move();
		}
		
	}

	public void escape() {

		Square nextPotentialPosition = position.get(potentialDirection);
		Square nextPosition = position.get(direction);

		if (!stoped) {
			if (nextPotentialPosition.isNavegable(this)) {
				direction = potentialDirection;
				setPosition(nextPotentialPosition);
				stoped = true;

			} else if ((potentialDirection != direction) && (nextPosition.isNavegable(this))) {
				setPosition(nextPosition);
				stoped = true;
			}
		}else {
			stoped=false;
		}

	}

}
