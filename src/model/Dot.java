package model;

import java.util.Observable;

public class Dot extends Observable {

	public boolean superDot = false;
	Square position;

	public Position getBoardPosition() {
		return position.getBoardPosition();
	}

}
