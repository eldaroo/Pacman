package model;

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
