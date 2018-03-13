package model;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import sounds.Sounds;

public abstract class Creature  extends Observable implements JSONStreamAware {

	boolean alive = true;
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		System.out.println("muere "+ this.name);
		this.alive = alive;
	}

	Direction direction = Direction.RIGHT;
	String name = null;
	Sounds sounds = new Sounds();


	public Creature(String name) {
		super();
		this.name = name;
	}

	Square position = null;
	Square positionType = null;

	Direction potentialDirection = Direction.LEFT;

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
		obj.put("name", String.valueOf(name));
		obj.put("direction", String.valueOf(direction));
		obj.put("getX", String.valueOf(position.getBoardPosition().getX()));
		obj.put("getY", String.valueOf(position.getBoardPosition().getY()));
		obj.put("status", alive);
		JSONValue.writeJSONString(obj, out);
	}
}
