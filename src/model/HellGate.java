package model;


public class HellGate extends Square {
	static Position boardPosition;
	
	public HellGate()
	{
		boardPosition = this.getBoardPosition();
	}
	@Override
	public boolean isNavegable(Creature creature) {
		if (creature.haveKeyOfHell())
		return true;
		else return false;
	}
	

}
