package model;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Creature extends Observable {

	boolean alive = true;
	public boolean eateable = true;
	
	Direction direction = Direction.LEFT;
	String identy = null;

	Square position = null;

	Direction potentialDirection = Direction.LEFT;

	Position previousPosition = null;


	boolean stoped = true;
	
	public Position getPreviousPosition() {
		if (previousPosition==null) {
			return position.getBoardPosition();
		}else
		return previousPosition;
	}

	public void setPreviousPosition(Position previousPosition) {
		this.previousPosition = previousPosition;
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
