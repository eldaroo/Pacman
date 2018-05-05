package model.creatures;

import java.util.ArrayList;

import controller.Game;
import controller.states.RespawnState;
import model.Direction;
import model.Position;
import model.creatures.ghostStates.GhostState;
import model.creatures.ghostStates.InHell;
import model.squares.Square;

public class Ghost extends Creature {

	@SuppressWarnings("unused")
	private static ArrayList<Direction> potentialDirectionsList;

	private Position target;
	private int intelligence;
	private int hellTime = 0;
	private int hellRequiredTime;
	private int auxForRetarded = 0;
	public GhostState state;

	public Ghost(String name, Square position, int intelligence) {
		super(name);
		setState(new InHell());
		this.position = position;
		this.intelligence = intelligence;
		this.hellRequiredTime = intelligence * 10;
		this.target = position.getBoardPosition();
		setKeyOfHell(true);
	}

	public void setState(GhostState state) {
		this.state = state;
	}

	public GhostState getState() {
		return state;
	}

	public boolean timeForOutOfHell() {
		if (hellTime == hellRequiredTime)
			return true;
		return false;
	}

	public void run(Pacman pacman) throws InterruptedException {

		determinateTarget(pacman);
		IA.runIa(this);
		determinatePotentialDirection();
		state.singularityAction(this);
		state.checkGoingThroughHellGate(this);
		state.move(this);
	}

	public void eatPacman(Pacman pacman) throws InterruptedException {

		Game.getSound().reproduceDeath();
		pacman.death();
		Game.setState(new RespawnState());

	}

	private void determinatePotentialDirection() {
		state.determinatePotentialDirection(this);
	}

	private void determinateTarget(Pacman pacman) {
		state.determinateTarget(this, pacman);
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

	public boolean goTo(Direction direction)
	{
		if (direction.equals(this.getDirection()))
		{
			return true;
		}else return false;
	}

	public void setHellTime(int i) {
		hellTime = i;
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
	
	public boolean canWalkInHell() {
		return true;
	}

}