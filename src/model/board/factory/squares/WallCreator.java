package model.board.factory.squares;

import model.board.factory.SquareCreator;
import model.squares.Square;
import model.squares.Wall;

public class WallCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new Wall();
	}

}
