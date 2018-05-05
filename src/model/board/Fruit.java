package model.board;

import java.util.Observable;
import java.util.Random;

import controller.Game;
import model.Position;

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

	// ********* FRUIT

		private static int fruitTime = 0;

		public static void update() {
			fruitTime++;
			if (Game.getTime() % 50 == 0) {
				determinateType();
				Fruit.setEnableToEat(true);
				fruitTime = 0;
			}
			if (fruitTime == 20) {
				Fruit.setEnableToEat(false);
			}
		}

		public static void determinateType() {

			Random random = new Random();
			int aux = random.nextInt(4);

			switch (aux) {
			case 0:
				Fruit.setFruitType(FruitType.APPLE);
				break;
			case 1:
				Fruit.setFruitType(FruitType.BANANNA);
				break;
			case 2:
				Fruit.setFruitType(FruitType.ORANGE);
				break;
			case 3:
				Fruit.setFruitType(FruitType.CHERRY);
				break;
			}

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
