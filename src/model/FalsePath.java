package model;

import model.Square.Corner;
import model.creatures.Creature;

public class FalsePath extends Square {

	public FalsePath(Corner corner)
	{
		super(corner);
	}
	public FalsePath()
	{}
	@Override
	public boolean isNavegable(Creature creature) {
		return false;
	}
}
