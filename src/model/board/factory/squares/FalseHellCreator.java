package model.board.factory.squares;

import model.board.factory.SquareCreator;
import model.squares.FalseHell;
import model.squares.Square;

public class FalseHellCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new FalseHell();
	}

}
