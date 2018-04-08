package model;




import controller.Game;
import controller.states.Super;
import model.Ghost.GhostState;
import sounds.Sounds;

public class Pacman extends Creature  {

	private boolean eatingGhost = false;
	public static enum PacmanState {MOVE,EATGHOST,DEATH};
	private static PacmanState pacmanState = PacmanState.MOVE;
	//private ArrayList<Dot> dots;
	private int ghostsEated = 0;
	Sounds sounds = new Sounds();
	

	

	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
		setKeyOfHell(false);
	}
	
	public void eatDot(Dot dot)
	{
		//Sounds.reproduceEatDot();
		Board.upScore(10,0);
		Board.setDotRemoved((Dot) dot);
		
		if (Board.getDotRemoved().getSuper() == true) {
			Game.setState (new Super());
			Board.upScore(20, 0);
		}
		Board.setPacmanEatNewDot(true);
	}
	
	public void eatFruit()
	{
		Board.upScore(200,0);
	}
	
	public void eatGhost(Ghost ghost) throws InterruptedException
	{
		ghostsEated++;

		sounds.reproduceEatGhost(ghostsEated);

		setPacmanState(PacmanState.EATGHOST);
		ghost.setGhostState(GhostState.EATED);
		Board.upScore(50, ghostsEated);
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