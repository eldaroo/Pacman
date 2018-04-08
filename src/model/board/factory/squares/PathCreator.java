package model.board.factory.squares;

import model.Path;
import model.Square;
import model.board.factory.SquareCreator;

public class PathCreator extends SquareCreator {

	@Override
	public Square createSquare() {
		// TODO Auto-generated method stub
		return new Path();
	}

}
