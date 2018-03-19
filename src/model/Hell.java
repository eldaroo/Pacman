package model;

public class Hell extends Square {

	public Hell() {
		
	}

@Override
public boolean isNavegable(Creature creature) {
	if(creature.name.equals("pacman")) {
	return false;}
	else return true;
}




}
