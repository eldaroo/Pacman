package model.board.factory.squares;

import model.FalseHell;
import model.Square;
import model.board.factory.SquareCreator;

public class FalseHellCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new FalseHell();
	}

}
