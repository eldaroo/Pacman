package model;

import java.util.Observable;

public abstract class Creature extends Observable {

	boolean alive = true;

	Direction direction = Direction.LEFT;
	Direction potentialDirection = Direction.LEFT;

	Square position = null;

	String identy = null;

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

	public void setDirection(Direction direction) {

		this.direction = direction;
	}

	public void setPosition(Square position) {
		this.position = position;
		setChanged();
		notifyObservers();

	}

	public void setPotentialDirection(Direction potentialDirection) {
		this.potentialDirection=potentialDirection;
	}
}
