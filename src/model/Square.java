package model;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

import org.json.simple.JSONValue;

public abstract class Square {
	private Square down = null;
	private Square left = null;
	private Square right = null;
	private Square up = null;
	private Position boardPosition = null;
	

	public Square() {
		super();
	}

	public Square get(Direction direction) {

		switch (direction) {
		case LEFT:
			return left;
		case RIGHT:
			return right;
		case UP:
			return up;
		case DOWN:
			return down;
		}
		return null;
	}

	public Position getBoardPosition() {
		return boardPosition;
	}


	public Square getDown() {
		return down;
	}

	public Square getLeft() {
		return left;
	}

	public Square getRight() {
		return right;
	}

	public Square getUp() {
		return up;
	}

	public abstract boolean isNavegable(Creature creature);

	public void setBoardPosition(Position position) {
		this.boardPosition = position;
	}

	public void setDown(Square down) {
		this.down = down;
	}

	public void setLeft(Square left) {
		this.left = left;
	}

	public void setRight(Square right) {
		this.right = right;
	}

	public void setUp(Square up) {
		this.up = up;
	}
	
}
