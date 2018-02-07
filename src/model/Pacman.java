package model;

public class Pacman extends Creature {

	public Pacman(Square position) {
		super();
		this.position = position;
		identy = "Pacman";
		super.eateable = true;
	}

	public void eatingGhosts(Ghost ghost, Pacman pacman) {
		//creatures.remove(indexPacman); <<<CUANDO SEAN VARIAS CRIATURAS>>>
		
		
	}


}
