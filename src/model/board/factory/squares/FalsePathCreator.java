package model.board.factory.squares;

import model.board.factory.SquareCreator;
import model.squares.FalsePath;
import model.squares.Square;

public class FalsePathCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new FalsePath();
	}

}
