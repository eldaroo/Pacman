package model;

public abstract class Square {
	private Square down = null;
	private Square left = null;
	private Square right = null;
	private Square up = null;
	Position boardPosition = null;

	public static enum Corner {NE,NW,SE,SW,CENTER};

	public Corner corner;

	public Square() {
		corner = Corner.CENTER;
	}
	public Square(Corner corner) {
		this.corner=corner;
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

	public Corner getCorner() {
		return corner;
	}
	public void setCorner(Corner corner) {
		this.corner = corner;
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
