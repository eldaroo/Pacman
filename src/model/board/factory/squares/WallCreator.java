package model.board.factory.squares;

import model.Square;
import model.Wall;
import model.board.factory.SquareCreator;

public class WallCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new Wall();
	}

}
