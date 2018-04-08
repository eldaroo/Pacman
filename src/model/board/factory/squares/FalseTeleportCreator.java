package model.board.factory.squares;

import model.FalseTeleport;
import model.Square;
import model.board.factory.SquareCreator;

public class FalseTeleportCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new FalseTeleport();
	}

}
