package model.creatures;




import controller.Game;
import controller.states.Super;

import model.Board;
import model.creatures.ghostStates.Eated;
import model.squares.Square;
import model.board.Dot;
import model.board.Fruit;
import model.board.Fruit.FruitType;
import sounds.Sounds;

public class Pacman extends Creature  {

	private boolean eatingGhost = false;
	public static enum PacmanState {MOVE,EATGHOST,DEATH};
	private static PacmanState pacmanState = PacmanState.MOVE;
	private int ghostsEated = 0;
	Sounds sounds = new Sounds();
	

	

	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
		setKeyOfHell(false);
	}
	
	public void eatDot(Dot dot) throws InterruptedException
	{
		Board.upScore(10,0);
		Board.setDotRemoved((Dot) dot);
		
		if (Board.getDotRemoved().getSuper() == true) {
			Game.setState (new Super());
			ghostsEated=0;
			Game.setFirstTime(true);
			Board.upScore(20, 0);
		}

		Board.setPacmanEatNewDot(true);
	}
	
	public void eatFruit() throws InterruptedException
	{
		Board.upScore(200,0);
	}
	
	public void eatGhost(Ghost ghost) throws InterruptedException
	{
		ghostsEated++;

		sounds.reproduceEatGhost(ghostsEated);
		ghost.setState(new Eated());
		pacmanState = PacmanState.EATGHOST;
		pacmanState= PacmanState.MOVE;
		Board.upScore(50, ghostsEated);
	}
	public void lookingForDots() throws InterruptedException {
		//dotRemoved=false;
		Board.setPacmanEatNewDot(false);

		for (Dot dot : Board.getDots()) {

			if (dot.getBoardPosition().equals(Board.getPacman().getBoardPosition())) {
				Board.getPacman().eatDot(dot);
			}
		}

		Board.getDots().remove(Board.getDotRemoved());

		// CHEQUEA SI TERMINO EL LEVEL
		Game.checkIfCompleteLevel();
	}

	public void lookingForFruit() throws InterruptedException {
		if (Fruit.isEnableToEat()) {
			if (Fruit.getBoardPosition().equals(Board.getPacman().getBoardPosition())) {
				Board.getPacman().eatFruit();
			}		
	}
	}

	public void death()
	{
		Board.subtractLife();
	}

	public boolean isEatingGhost() {
		return eatingGhost;
	}

	public void setEatingGhost(boolean eatingGhost) {
		this.eatingGhost = eatingGhost;
	}
	
	public void	accumulateGhostsEated () {
		ghostsEated++;
	}
	
	public void resetGhostEated () {
		ghostsEated = 0;
	}

	public void setPacmanState(PacmanState pacmanState) {
		Pacman.pacmanState = pacmanState;
	}
	public PacmanState getPacmanState () {
		return pacmanState;
	}

	public int getGhostsEated() {
		// TODO Auto-generated method stub
		return ghostsEated;
	}


}