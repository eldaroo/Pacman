package model.creatures;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import model.Direction;
import model.Position;
import model.Square;
import sounds.Sounds;

public abstract class Creature extends Observable implements JSONStreamAware {

	private boolean alive = true;
	protected boolean keyOfHell;
	Direction direction = Direction.RIGHT;
	private String name = null;
	Sounds sounds = new Sounds();
	protected Square position = null;
	Square positionType = null;
	Direction potentialDirection = Direction.LEFT;
	
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

	public void writeJSONString(Writer out) throws IOException {

		LinkedHashMap<Object, Object> obj = new LinkedHashMap<>();
		obj.put("name", String.valueOf(getName()));
		obj.put("direction", String.valueOf(direction));
		obj.put("getX", String.valueOf(position.getBoardPosition().getX()));
		obj.put("getY", String.valueOf(position.getBoardPosition().getY()));
		obj.put("status", alive);
		JSONValue.writeJSONString(obj, out);
	}

	public boolean haveKeyOfHell() {
		return keyOfHell;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setKeyOfHell(boolean key) {
		keyOfHell = key;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
