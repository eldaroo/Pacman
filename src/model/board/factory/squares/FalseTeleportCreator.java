package model.board.factory.squares;

import model.board.factory.SquareCreator;
import model.squares.FalseTeleport;
import model.squares.Square;

public class FalseTeleportCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new FalseTeleport();
	}

}
