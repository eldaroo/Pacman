package model.squares;

import model.creatures.Creature;

public class FalseHell extends Square {

	public FalseHell(Corner corner)
	{
		super(corner);
	}
	public FalseHell()
	{}
	@Override
	public boolean isNavegable(Creature creature) {
		return false;
	}

}
