package model.creatures;

import java.util.ArrayList;
import java.util.Random;

import controller.Game;
import model.Board;
import model.Direction;
import model.GameState;
import model.Position;
import model.Square;
import model.creatures.ghostStates.Courageous;
import model.creatures.ghostStates.GhostState;
import model.creatures.ghostStates.InHell;
import sounds.Sounds;

public class Ghost extends Creature {

	private static ArrayList<Direction> potentialDirectionsList;


	private Position target;
	private int intelligence;
	private int hellTime=0;
	private int hellRequiredTime;
	 private int auxForRetarded=0;
	 public GhostState state ;

	public Ghost(String name, Square position, int intelligence) {
		super(name);
		setState(new InHell());
		this.position = position;
		this.intelligence = intelligence;
		this.hellRequiredTime= intelligence*10;
		this.target = position.getBoardPosition();
		setKeyOfHell(true);
	}



	public void setState(GhostState state) {
		this.state = state;
	}
	public GhostState getState ()
	{
		return state;
	}
	public boolean timeForOutOfHell()
	{
		if (hellTime == hellRequiredTime)
			return true;
		return false;
	}

	public void run(Pacman pacman) throws InterruptedException {

		IA.runIa(this);
		determinateTarget(pacman);
		determinatePotentialDirection();
		state.particularAction(this);
		state.checkGoingThroughHellGate(this);
		move();
	}


	public void eatPacman(Pacman pacman) {

		Sounds.reproduceDeath();
		pacman.death();
		Game.setGameState(GameState.RESPAWN);

	}

	private void determinatePotentialDirection() {
		state.determinatePotentialDirection(this);
	}

	private void determinateTarget(Pacman pacman) {
		state.determinateTarget(this,pacman);
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void inHell() {
		// boolean
	}

	public Position getTarget() {
		return target;
	}

	public void setTarget(Position position) {
		this.target = position;
	}

	

	public void setHellTime(int i) {
		hellTime=i;
	}

	public int getAuxForRetarded() {
		return auxForRetarded;
	}

	public void setAuxForRetarded(int auxForRetarded) {
		this.auxForRetarded = auxForRetarded;
	}



	public void upHellTime() {
		hellTime++;
	}

}