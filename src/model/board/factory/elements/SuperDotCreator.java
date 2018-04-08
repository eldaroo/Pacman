package model.board.factory.elements;

import model.SuperDot;
import model.board.Element;
import model.board.factory.ElementCreator;

public class SuperDotCreator extends ElementCreator {

	@Override
	public Element elementCreator() {
		// TODO Auto-generated method stub
		return new SuperDot();
	}

}
