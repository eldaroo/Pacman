package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

public class Intelligence {
	private Ghost ghost;
	private ArrayList<Square> threeDirectionMatrix ;
	private Map <Square, Direction > DirectionMap;
	private ArrayList<Square> squaresAvailables;
	private ArrayList<Direction> smartDirectionAvailables;
	private ArrayList<Direction> targetMatrix; 
	private ArrayList<Direction> directionAvailables ; 
	private ArrayList<Direction> goAwayTargetMatrix; 
	private static Direction smartChoise;
	private Direction randomChoise;
	private Direction goAwayDirection;




	public Intelligence(Ghost ghost)
	{
		this.ghost = ghost;
		
			DirectionMap = new HashMap <Square, Direction>( );
			threeDirectionMatrix = new ArrayList<Square>();
			 squaresAvailables = new ArrayList<Square>();
			smartDirectionAvailables  = new ArrayList<Direction>();
			targetMatrix = new ArrayList<Direction>();
			directionAvailables = new ArrayList<Direction>();
			goAwayTargetMatrix = new ArrayList<Direction>();
			

			randomChoise =getRandomChoise(DirectionMap, targetMatrix,squaresAvailables,threeDirectionMatrix,directionAvailables);
			smartChoise = getSmartPotentialDirection(goAwayTargetMatrix,DirectionMap, smartDirectionAvailables,targetMatrix,squaresAvailables,threeDirectionMatrix,directionAvailables);
			generateGoAwayDirection();
		}
		



		private void generateGoAwayDirection() {
			int aux=0;
			Random random = new Random();
			aux = random.nextInt(goAwayTargetMatrix.size());
			setGoAwayDirection(goAwayTargetMatrix.get(aux));
	}




		public void setGoAwayDirection(Direction goAwayDirection) {
		this.goAwayDirection = goAwayDirection;
	}




		//GENERAMOS LA MEJOR DECISION
		private Direction getSmartPotentialDirection(ArrayList<Direction> goAwayDirectionMatrix, Map<Square, Direction> directionMap, ArrayList<Direction> smartDirectionAvailables2, ArrayList<Direction> targetMatrix, ArrayList<Square> squaresAvailable2, ArrayList<Square> threeDirectionMatrix, ArrayList<Direction> directionAvailables)
		{
			generateSmartDirectionArray(goAwayDirectionMatrix, DirectionMap, smartDirectionAvailables,targetMatrix,squaresAvailables,threeDirectionMatrix,directionAvailables);
			Random random = new Random();
			Direction bestChoise = null;
			
			if (smartDirectionAvailables.size()>0) {
			int aux = random.nextInt(smartDirectionAvailables.size());
			bestChoise= smartDirectionAvailables.get(aux);
			}else bestChoise =Direction.LEFT;

			return bestChoise;
		}
	
		//GENERAMOS UNA MATRIZ CON LAS MEJORES DECISIONES
		private void generateSmartDirectionArray(ArrayList<Direction> goAwayTargetMatrix, Map<Square, Direction> directionMap, ArrayList<Direction> smartDirectionAvailables2, ArrayList<Direction> targetMatrix, ArrayList<Square> squaresAvailables, ArrayList<Square> threeDirectionMatrix, ArrayList<Direction> directionAvailables)
		{
			CreateDirectionAvailablesArray(DirectionMap,squaresAvailables,threeDirectionMatrix,directionAvailables);
			localizateTarget(targetMatrix, goAwayTargetMatrix );
			for (Direction availableDirection : directionAvailables) {
				for (Direction targetDirection : targetMatrix) {
					if (availableDirection.equals(targetDirection))
					{
						smartDirectionAvailables.add(availableDirection);

					}
				}
			}
			if (smartDirectionAvailables.equals(null))
			{
				smartDirectionAvailables.add(directionAvailables.get(0));
				System.out.println("Holis "+directionAvailables.get(0));

			}

		}
		//IDENTIFICAMOS DONDE ESTA EL TARGET
		private void localizateTarget(ArrayList<Direction> targetMatrix, ArrayList<Direction> goAwayTargetMatrix) {
			Position ghostPosition = ghost.getBoardPosition();
			Position targetPosition = ghost.getTarget();
			if(ghostPosition.getX()>targetPosition.getX())
			{	
				targetMatrix.add(Direction.LEFT);

			} else goAwayTargetMatrix.add(Direction.LEFT);
			if(ghostPosition.getX()<targetPosition.getX())
			{	
				targetMatrix.add(Direction.RIGHT);

			}else goAwayTargetMatrix.add(Direction.RIGHT);
			if(ghostPosition.getY()>=targetPosition.getY())
			{
				targetMatrix.add(Direction.UP);

			}else goAwayTargetMatrix.add(Direction.UP);
			if(ghostPosition.getY()<=targetPosition.getY())
			{	
				
				targetMatrix.add(Direction.DOWN);
			}else goAwayTargetMatrix.add(Direction.DOWN);
		}


		private Direction getRandomChoise(Map<Square, Direction> directionMap, ArrayList<Direction> targetMatrix, ArrayList<Square> squaresAvailables, ArrayList<Square> threeDirectionMatrix, ArrayList<Direction> directionAvailables)
		{			

			CreateDirectionAvailablesArray(directionMap, squaresAvailables,threeDirectionMatrix,directionAvailables);
			int aux=0;
			Random random = new Random();
			aux = random.nextInt(directionAvailables.size());
			return directionAvailables.get(aux);

		}
		//VEMOS CUALES DE LAS DIRECCIONES ESTAN DISPONIBLES
		private void CreateDirectionAvailablesArray(Map<Square, Direction> directionMap, ArrayList<Square> squaresAvailables, ArrayList<Square> threeDirectionMatrix, ArrayList<Direction> directionAvailables) {

			CreateThreeDirectionMatrix(threeDirectionMatrix);
			for (Square square : threeDirectionMatrix) {
				if (square.isNavegable(ghost))
				{
					squaresAvailables.add(square);


				}
			}
			for (Square square : squaresAvailables) {
				directionAvailables.add(DirectionMap.get(square)); 

			}
		}

		//CREAMOS UN ARRAY CON LOS SQUARE POSIBLES, EXCLUYENDO A LA DIRECCION DE 
		//LA QUE VIENE. 
		//LO MAPEAMOS CON SUS RESPECTIVAS DIRECCIONES
		private void CreateThreeDirectionMatrix(ArrayList<Square> threeDirectionMatrix) {
			if(!ghost.direction.equals(Direction.UP))
			{		
				threeDirectionMatrix.add(ghost.position.getDown());
				DirectionMap.put(ghost.position.getDown(), Direction.DOWN);
			}
			if(!ghost.direction.equals(Direction.LEFT))
			{
				threeDirectionMatrix.add(ghost.position.getRight());
				DirectionMap.put(ghost.position.getRight(), Direction.RIGHT);
			}
			if(!ghost.direction.equals(Direction.DOWN))
			{
				threeDirectionMatrix.add(ghost.position.getUp());
				DirectionMap.put(ghost.position.getUp(), Direction.UP);
			}
			if(!ghost.direction.equals(Direction.RIGHT))
			{
				threeDirectionMatrix.add(ghost.position.getLeft());
				DirectionMap.put(ghost.position.getLeft(), Direction.LEFT);
			}
			//System.out.println(threeDirectionMatrix.size());
		}
		
		public Direction getGoAwayDirection() {
		return goAwayDirection;
	}

		public Direction getSmartChoise() {
			return smartChoise;
		}
	public Direction getRandomChoise() {
		return randomChoise;
	}
		
		/*
		//CREA UNA POSIBILIDAD COMPLETAMENTE ALEATORIA
		public Direction randomPotentialDirection() {
			Random random = new Random();
			ArrayList<Direction> directionsAvailables = new ArrayList<Direction>();
			int aux = 0;

			if ((position.getDown().isNavegable(this)) && (direction != Direction.UP)) {
				directionsAvailables.add(Direction.DOWN);
			}
			if ((position.getLeft().isNavegable(this)) && (direction != Direction.RIGHT)) {
				directionsAvailables.add(Direction.LEFT);
			}
			if ((position.getRight().isNavegable(this)) && (direction != Direction.LEFT)) {
				directionsAvailables.add(Direction.RIGHT);
			}
			if ((position.getUp().isNavegable(this)) && (direction != Direction.DOWN)) {
				directionsAvailables.add(Direction.UP);
			}

			aux = random.nextInt(directionsAvailables.size());
			return directionsAvailables.get(aux);
		}*/
}
