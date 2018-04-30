package model.creatures;




import controller.Game;
import controller.states.SuperState;

import model.Board;
import model.creatures.ghostStates.Eated;
import model.squares.Hell;
import model.squares.Square;
import model.board.Dot;
import model.board.Fruit;
import model.board.Fruit.FruitType;
import sounds.Sounds;

public class Pacman extends Creature  {

	public static enum PacmanState {MOVE,EATGHOST,DEATH};
	private static PacmanState pacmanState = PacmanState.MOVE;
	private int ghostsEated = 0;	

	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
		setKeyOfHell(false);
	}
	
	public void eatDot(Dot dot)
	{
		Game.getBoard().setDotRemoved((Dot) dot);		
		Game.getBoard().getDotRemoved().eaten(this);		
		Game.getBoard().setPacmanEatNewDot(true);
	}
	
	public void eatFruit() throws InterruptedException
	{
		Game.getBoard().upScore(200,0);
	}
	
	public void eatGhost(Ghost ghost) throws InterruptedException
	{
		ghostsEated++;
		Game.getSound().reproduceEatGhost(ghostsEated);
		ghost.setState(new Eated());
		pacmanState = PacmanState.EATGHOST;
		pacmanState= PacmanState.MOVE;
		Game.getBoard().upScore(50, ghostsEated);
	}
	
	public void move() {
		super.move();
		Game.getBoard().setPacmanEatNewDot(false);		
		
		for (Dot dot : Game.getBoard().getDots()) {
			if (dot.getBoardPosition().equals(getBoardPosition())) {
				eatDot(dot);
			}
		}

		Game.getBoard().getDots().remove(Game.getBoard().getDotRemoved());

		// CHEQUEA SI TERMINO EL LEVEL
		Game.checkIfCompleteLevel();
	}

	public void lookingForFruit() throws InterruptedException {
		if (Fruit.isEnableToEat()) {
			if (Fruit.getBoardPosition().equals(Game.getBoard().getPacman().getBoardPosition())) {
				Game.getBoard().getPacman().eatFruit();
			}		
	}
	}

	public void death()
	{
		Game.getBoard().subtractLife();
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