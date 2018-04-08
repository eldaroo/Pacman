package model.board.factory.squares;

import model.FalsePath;
import model.Square;
import model.board.factory.SquareCreator;

public class FalsePathCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new FalsePath();
	}

}
