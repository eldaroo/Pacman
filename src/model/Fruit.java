package model;

import java.util.Observable;
import java.util.Random;

import controller.Game;

public class Fruit extends Observable{
public static enum FruitType {CHERRY, BANANNA, APPLE, ORANGE}
private FruitType fruitType;
Position fruitPosition;

public Fruit(Position Position)
{
	fruitPosition = Position;
	/*
	if (Game.getTime()%50==0)
	{
		Random random = new Random();		
		int aux = random.nextInt(3);
		
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
	}*/
}

public Position getFruitPosition() {
	return fruitPosition;
}

public void setFruitPosition(Position fruitPosition) {
	this.fruitPosition = fruitPosition;
}

public void lookingForFruit () {
	
	if (Game.getTime()%50==0)
	{
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
		
		System.out.println(fruitType);
		setChanged();
		notifyObservers();
	}
}

public FruitType getFruitType() {
	
	return fruitType;
}
}