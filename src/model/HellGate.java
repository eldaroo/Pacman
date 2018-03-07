package model;


public class HellGate extends Square {

	@Override
	public boolean isNavegable(Creature creature) {
		if (creature.name!="pacman")
		return true;
		else return false;
	}

}
