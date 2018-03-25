package model;

import model.Square.Corner;

public class Wall extends Square {
	public Wall(Corner corner)
	{
		super(corner);
	}
	public Wall()
	{}

	@Override
	public boolean isNavegable(Creature creature) {
		return false;
	}
}
