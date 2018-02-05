package model;

import java.util.Observable;

public abstract class Creature extends Observable {

	boolean alive = true;
	Direction direction = Direction.UP;
	String identy = null;
	Square position = null;
	Direction potentialDirection = Direction.LEFT;

	public void eatDot() {
		System.out.println("Comió un dot");
	}

	public Position getBoardPosition() {
		return position.getBoardPosition();
	}

	public Direction getDirection() {
		return direction;
	}

	public Square getPosition() {
		return position;
	}

	public Direction getPotentialDirection() {
		return potentialDirection;
	}

	public boolean isDead() {
		return !alive;
	}

	public void kill() {
		alive = false;
	}

	public void move() {

		Square nextPotentialPosition = position.get(potentialDirection);
		Square nextPosition = position.get(direction);
		System.out.println(position + " , " + nextPosition);
		if (nextPotentialPosition.isNavegable(this)) {
			direction = potentialDirection;
			setPosition(nextPotentialPosition);

		} else if ((potentialDirection != direction) && (nextPosition.isNavegable(this))) {
			setPosition(nextPosition);

		}

	}

	public void setDirection(Direction direction) {

		this.direction = direction;
	}

	public void setPosition(Square position) {
		this.position = position;
		setChanged();
		notifyObservers();
	}

	public void setPotentialDirection(Direction potentialDirection) {
		this.potentialDirection = potentialDirection;
	}
}
