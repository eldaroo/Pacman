package model;

import model.Square.Corner;
import model.creatures.Creature;

public class HellGate extends Square {

	public HellGate(Corner corner)
	{
		super(corner);
	}
	public HellGate()
	{}
	@Override
	public boolean isNavegable(Creature creature) {
		if (creature.haveKeyOfHell())
		return true;
		else return false;
	}
	

}
