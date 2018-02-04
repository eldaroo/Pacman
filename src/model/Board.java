package model;

import java.util.ArrayList;

public class Board {

	Square[][] board;
	Dot[][] dots;
	ArrayList <Square> teleportList = new ArrayList <Square>();

	public Board(int[][] levelBoard,int[][] levelDots ) {
		makeBoard(levelBoard);
		makeDots(levelDots);
	}



	public Square[][] getBoard() {
		return board;
	}

	public Dot[][] getDots() {
		return dots;
	}

	private void lookingForDot(){
		
	}
	
	private void makeBoard(int[][] levelBoard) {
		board = new Square[levelBoard.length][levelBoard.length];
		for (int i = 0; i < levelBoard.length; i++) {
			for (int j = 0; j < levelBoard.length; j++) {
				switch (levelBoard[i][j]) {
				case 1:
					
					board[i][j] = new Path ();
					break;
				case 0:
					board[i][j] = new Wall();
					break;
				case 6:
					board[i][j] = new Hell();
					break;
				case 9:
					board[i][j] = new Teleport();
					teleportList.add(board[i][j]);
					break;
				}
				board[i][j].setBoardPosition(new Position(i,j));
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (j + 1 < board.length)  {
					board[i][j].setDown(board[i][j + 1]);

				}
				if (j - 1 >= 0){
					board[i][j].setUp(board[i][j - 1]);
				}
				if (i - 1 >= 0) {
					board[i][j].setLeft(board[i - 1][j]);
				}
				if (i + 1 < board.length) {
					board[i][j].setRight(board[i + 1][j]);
				}
			}
		}
		
	}

	private void makeDots(int[][] levelDots) {
		dots = new Dot[levelDots.length][levelDots.length];
		for (int i = 0; i < levelDots.length; i++) {
			for (int j = 0; j < levelDots.length; j++) {
				switch (levelDots[i][j]) {
				case 1:
			
					dots[i][j] = new Dot ();
					break;
				case 2:
					dots[i][j] = new SuperDot();
					break;

					}
			}
		}
		
	}

	public void move(Creature creature) {
		
		if(creature.position.getClass().getName()== "model.Teleport")
		{
			teleportCreature(creature);
		}
		Direction potentialDirection = creature.getPotentialDirection();
		Direction direction = creature.getDirection();
		Square position = creature.getPosition();
		Square nextPotentialPosition = position.get(potentialDirection);
		Square nextPosition = position.get(direction);

		if (nextPotentialPosition.isNavegable(creature)) {
			creature.direction=potentialDirection;
			creature.setPosition(nextPotentialPosition);
			
		} else if((potentialDirection != direction)&&(nextPosition.isNavegable(creature)))
		{
			
			creature.setPosition(nextPosition);
			
		} 
		
		if(creature.identy=="Pacman"){
			if (creature.position.getClass().getName()=="model.Dot"){
				creature.eatDot();
			}else if (creature.position.getClass().getName()=="model.SuperDot"){
				
			}
		}
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}


	public void setDots(Dot[][] dots) {
		this.dots = dots;
	}
		
	

	private void teleportCreature(Creature creature) {
			if(teleportList.get(0)==creature.position)
			{
				creature.setPosition(teleportList.get(5).getLeft());
			}
			if(teleportList.get(1).equals(creature.position))
			{
				creature.setPosition(teleportList.get(2).getUp());				
			}
			if(teleportList.get(2).equals(creature.position))
			{				
				creature.setPosition(teleportList.get(1).getDown());
			}
			if(teleportList.get(3).equals(creature.position))
			{
				creature.setPosition(teleportList.get(4).getUp());
			}
			if(teleportList.get(4).equals(creature.position))
			{
				creature.setPosition(teleportList.get(3).getDown());
			}
			if(teleportList.get(5).equals(creature.position))
			{
				creature.setPosition(teleportList.get(0).getRight());
			}
		}
}
