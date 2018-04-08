package model.board.factory.elements;

import model.Dot;
import model.board.Element;
import model.board.factory.ElementCreator;

public class DotCreator extends ElementCreator {

	@Override
	public Element elementCreator() {
		// TODO Auto-generated method stub
		return new Dot();
	}

}
