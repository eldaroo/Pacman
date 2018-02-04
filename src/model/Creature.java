package model;

import java.util.Observable;

public abstract class Creature extends Observable {

	boolean alive = true;
	Direction direction = Direction.UP;
	Square position = null;
	Direction potentialDirection =Direction.LEFT;
	String identy = null;

	public Direction getDirection() {
		return direction;
	}

	public Square getPosition() {
		return position;
	}

	public boolean isDead() {
		return !alive;
	}

	public void kill() {
		alive = false;
	}
	
	public void eatDot (){
		System.out.println("Comió un dot");
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

	public Direction getPotentialDirection() {
		return potentialDirection;
	}
}
