package model.squares;


import model.creatures.Creature;

public class Path extends Square {
	public Path(Corner corner)
	{
		super(corner);
	}
	public Path()
	{}
	@Override
	public boolean isNavegable(Creature creature) {
		return true;
	}


}
