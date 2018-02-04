package model;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Creature {

	public Ghost(Square position) {
		this.position = position;
		this.direction = Direction.UP;
		identy = "Ghost";

	}

	public void pathFinder() {
		if (this.position.getClass().getName() != "model.Teleport") {
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

			setPotentialDirection(directionsAvailables.get(aux));
		}
	}

}
