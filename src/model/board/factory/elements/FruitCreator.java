package model.board.factory.elements;

import model.Fruit;
import model.board.Element;
import model.board.factory.ElementCreator;

public class FruitCreator extends ElementCreator {

	@Override
	public Element elementCreator() {
		// TODO Auto-generated method stub
		return new Fruit();
	}

}
