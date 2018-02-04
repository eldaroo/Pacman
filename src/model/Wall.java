package model;

public class Wall extends Square {
	@Override
	public boolean isNavegable(Creature creature) {
		return false;
	}
}
