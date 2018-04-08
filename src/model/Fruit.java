package model;

import java.util.Observable;

public class Fruit extends Observable {
	public static enum FruitType {
		CHERRY, BANANNA, APPLE, ORANGE
	}

	private static FruitType fruitType;
	private static Position fruitPosition;
	public static boolean enableToEat;

	public Fruit(Position Position) {
		enableToEat = false;
		fruitPosition = Position;
	}



	public static void setFruitType(FruitType fruitType) {
		Fruit.fruitType = fruitType;
	}



	public static Position getBoardPosition() {

		return fruitPosition;
	}

	public static void setFruitPosition(Position fruitPosition) {
		Fruit.fruitPosition = fruitPosition;
	}

	public static boolean isEnableToEat() {
		return enableToEat;
	}

	public static void setEnableToEat(boolean enableToEat) {
		Fruit.enableToEat = enableToEat;
	}

	public static FruitType getFruitType() {
		return fruitType;
	}
}
