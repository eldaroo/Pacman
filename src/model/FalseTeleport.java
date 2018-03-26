package model;

import model.Square.Corner;

public class FalseTeleport extends Square {

	public FalseTeleport(Corner corner)
	{
		super(corner);
	}
	public FalseTeleport()
	{

	}
	
	@Override
	public boolean isNavegable(Creature creature) {
		// TODO Auto-generated method stub
		return false;
	}

}
