package model.squares;


import model.creatures.Creature;

public class Hell extends Square {

	public Hell(Corner corner)
	{
		super(corner);
	}
	public Hell()
	{}

	@Override
	public boolean isNavegable(Creature creature) {
		return creature.canWalkInHell();
	}




}
