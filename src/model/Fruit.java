package model;

import java.util.Observable;
import java.util.Random;

import controller.Game;

public class Fruit extends Observable {
	public static enum FruitType {
		CHERRY, BANANNA, APPLE, ORANGE
	}

	private static FruitType fruitType;
	private static Position fruitPosition;
	public static boolean enableToEat;
	private static int fruitTime = 0;

	public Fruit(Position Position) {
		enableToEat = false;
		fruitPosition = Position;
	}

	public void lookingForFruit() {
		fruitTime++;
		if (Game.getTime() % 50 == 0) {
			determinateType();
			enableToEat = true;
			fruitTime = 0;
			setChanged();
			notifyObservers();
		}
		if (fruitTime == 20) {
			enableToEat = false;
		}
	}

	public static void determinateType() {

		Random random = new Random();
		int aux = random.nextInt(4);

		switch (aux) {
		case 0:
			fruitType = FruitType.APPLE;
			break;
		case 1:
			fruitType = FruitType.BANANNA;
			break;
		case 2:
			fruitType = FruitType.ORANGE;
			break;
		case 3:
			fruitType = FruitType.CHERRY;
			break;
		}

	}

	public Position getFruitPosition() {
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
