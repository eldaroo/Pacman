package model;

import model.Square.Corner;
import model.creatures.Creature;

public class Hell extends Square {

	public Hell(Corner corner)
	{
		super(corner);
	}
	public Hell()
	{}

@Override
public boolean isNavegable(Creature creature) {
	if(creature.getName().equals("pacman")) {
	return false;}
	else return true;
}




}
