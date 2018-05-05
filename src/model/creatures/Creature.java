package model.creatures;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import model.Direction;
import model.Position;
import model.squares.Square;

public abstract class Creature extends Observable implements JSONStreamAware {

	private boolean alive = true;
	protected boolean haveKeyOfHell;
	private Direction direction = Direction.RIGHT;
	private String name = null;
	protected Square position = null;
	//private Square positionType = null;
	private Direction potentialDirection = Direction.LEFT;
	
	public Creature(String name) {
		super();
		this.setName(name);
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

	public boolean haveKeyOfHell() {
		return haveKeyOfHell;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setKeyOfHell(boolean key) {
		haveKeyOfHell = key;

	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public void writeJSONString(Writer out) throws IOException {

		LinkedHashMap<Object, Object> obj = new LinkedHashMap<>();
		obj.put("name", String.valueOf(getName()));
		obj.put("direction", String.valueOf(direction));
		obj.put("position", String.valueOf(position));
		obj.put("status", alive);
		JSONValue.writeJSONString(obj, out);
	}

	public boolean canWalkInHell() {
		return false;
	}
}
