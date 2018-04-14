package model.board.factory.squares;

import model.board.factory.SquareCreator;
import model.squares.Square;
import model.squares.Teleport;

public class TeleportCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new Teleport();
	}

}
