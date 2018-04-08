package model.board.factory.squares;

import model.Square;
import model.Teleport;
import model.board.factory.SquareCreator;

public class TeleportCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new Teleport();
	}

}
