package model;

import model.Square.Corner;

public class Hell extends Square {

	public Hell(Corner corner)
	{
		super(corner);
	}
	public Hell()
	{}

@Override
public boolean isNavegable(Creature creature) {
	if(creature.name.equals("pacman")) {
	return false;}
	else return true;
}




}
