package model;

public class Path extends Square {
	
	public Path()
	{

	}
	@Override
	public boolean isNavegable(Creature creature) {
		return true;
	}
	

}
