package model.board.factory.squares;

import model.HellGate;
import model.Square;
import model.board.factory.SquareCreator;

public class HellGateCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new HellGate();
	}

}
