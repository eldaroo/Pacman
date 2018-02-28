package model;

public class Pacman extends Creature {

	public int lifes = 3;
	public int score = 0;

	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
	}

	public void eatingGhosts(Ghost ghost, Pacman pacman) {
		if (pacman.getBoardPosition() == ghost.getBoardPosition()) {
			pacman.score += 50;
			ghost.isDead();
		}
		// creatures.remove(indexPacman); <<<CUANDO SEAN VARIAS CRIATURAS>>>

	}

}
