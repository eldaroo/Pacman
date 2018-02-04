package model;

public class Hell extends Square {

	public Hell() {
	
	}

	@Override
	public boolean isNavegable(Creature creature) {
		return creature.isDead();
	}

}
