package model;

public class FalsePath extends Square {
	@Override
	public boolean isNavegable(Creature creature) {
		return false;
	}
}
