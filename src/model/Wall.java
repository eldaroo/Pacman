package model;

import model.Square.Corner;
import model.creatures.Creature;

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
