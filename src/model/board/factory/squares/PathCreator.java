package model.board.factory.squares;

import model.board.factory.SquareCreator;
import model.squares.Path;
import model.squares.Square;

public class PathCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new Path();
	}

}
