package model.board.factory.squares;

import model.board.factory.SquareCreator;
import model.squares.HellGate;
import model.squares.Square;

public class HellGateCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new HellGate();
	}

}
