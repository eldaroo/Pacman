package model;

public class Pacman extends Creature {

	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
		super.eateable = true;
	}

	public void eatingGhosts(Ghost ghost, Pacman pacman) {
		//creatures.remove(indexPacman); <<<CUANDO SEAN VARIAS CRIATURAS>>>
		
		
	}


}
