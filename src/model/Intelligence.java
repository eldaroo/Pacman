package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Intelligence {
	private Ghost ghost;
	private ArrayList<Square> threeDirectionMatrix ;
	private Map <Square, Direction > DirectionMap;
	private ArrayList<Square> squaresAvailables;
	 ArrayList<Direction> smartDirectionAvailables;
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
		private Direction getSmartPotentialDirection(ArrayList<Direction> goAwayDirectionMatrix, Map<Square, Direction> directionMap, ArrayList<Direction> smartDirectionAvailables, ArrayList<Direction> targetMatrix, ArrayList<Square> squaresAvailable2, ArrayList<Square> threeDirectionMatrix, ArrayList<Direction> directionAvailables)
		{
			
			generateSmartDirectionArray(goAwayDirectionMatrix, DirectionMap, smartDirectionAvailables,targetMatrix,squaresAvailables,threeDirectionMatrix,directionAvailables);
			Random random = new Random();
			Direction bestChoise = null;
			
			int aux = random.nextInt(smartDirectionAvailables.size());
			bestChoise= smartDirectionAvailables.get(aux);

			return bestChoise;
		}
	
		//GENERAMOS UNA MATRIZ CON LAS MEJORES DECISIONES
		private void generateSmartDirectionArray(ArrayList<Direction> goAwayTargetMatrix, Map<Square, Direction> directionMap, ArrayList<Direction> smartDirectionAvailables, ArrayList<Direction> targetMatrix, ArrayList<Square> squaresAvailables, ArrayList<Square> threeDirectionMatrix, ArrayList<Direction> directionAvailables)
		{
			smartDirectionAvailables.clear();
			CreateDirectionAvailablesArray();
			localizateTarget();
			for (Direction availableDirection : directionAvailables) {
				for (Direction targetDirection : targetMatrix) {
					if (availableDirection.equals(targetDirection))
					{
						smartDirectionAvailables.add(availableDirection);

					}
				}
			}
			if (smartDirectionAvailables.size()==0)
			{
				
				int aux=0;
				Random random = new Random();
				aux = random.nextInt(directionAvailables.size());
				smartDirectionAvailables.add(directionAvailables.get(aux));

			}

		}
		//IDENTIFICAMOS DONDE ESTA EL TARGET
		private void localizateTarget() {
			goAwayTargetMatrix.clear();
			targetMatrix.clear();

			Position ghostPosition = ghost.getBoardPosition();
			Position targetPosition = ghost.getTarget();
			if(ghostPosition.getX()>targetPosition.getX())
			{	
				targetMatrix.add(Direction.LEFT);

			} else goAwayTargetMatrix.add(Direction.LEFT);  //Llenamos la matriz opuesta para cuando estan muertos
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

		//GENERAMOS UNA DECISION ALEATORIA ENTRE LAS DISPONIBLES
		private Direction getRandomChoise(Map<Square, Direction> directionMap, ArrayList<Direction> targetMatrix, ArrayList<Square> squaresAvailables, ArrayList<Square> threeDirectionMatrix, ArrayList<Direction> directionAvailables)
		{			

			CreateDirectionAvailablesArray();
			int aux=0;
			Random random = new Random();
			aux = random.nextInt(directionAvailables.size());
			return directionAvailables.get(aux);

		}
		//VEMOS CUALES DE LAS DIRECCIONES ESTAN DISPONIBLES
		private void CreateDirectionAvailablesArray() {
			squaresAvailables.clear();
			directionAvailables.clear();
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
			threeDirectionMatrix.clear();
			DirectionMap.clear();
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
		
}
