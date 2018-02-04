package model;

public class Teleport extends Square {

	@Override
	public boolean isNavegable(Creature creature) {
		return true;
	}

}
