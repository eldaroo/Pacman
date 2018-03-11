package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

public class Intelligence {
	Ghost ghost;
	ArrayList<Square> threeDirectionMatrix ;
	Map <Square, Direction > DirectionMap;
	ArrayList<Square> squaresAvailables;
	ArrayList<Direction> smartDirectionAvailables;
	ArrayList<Direction> targetMatrix; 
	ArrayList<Direction> directionAvailables ; 
	Direction smartChoise;
	Direction randomChoise;




	public Intelligence(Ghost ghost)
	{
		this.ghost = ghost;
	}
	
	//DECIDE LA DIRECCION PARA CUANDO ESTA EN NORMAL MODE Y VIVO
		public Direction getDecisionNormalAlive() {
		
			DirectionMap = new HashMap <Square, Direction>( );
			threeDirectionMatrix = new ArrayList<Square>();
			 squaresAvailables = new ArrayList<Square>();
			smartDirectionAvailables  = new ArrayList<Direction>();
			targetMatrix = new ArrayList<Direction>();
			directionAvailables = new ArrayList<Direction>();
			
			
			Random random = new Random();
			ArrayList<Direction> potentialDirectionsList = new ArrayList<Direction>();
			int stupidity = 10 - ghost.getIntelligence();
			smartChoise = getSmartPotentialDirection(DirectionMap, smartDirectionAvailables,targetMatrix,squaresAvailables,threeDirectionMatrix,directionAvailables);
			
			directionAvailables.clear();
			threeDirectionMatrix.clear();
			squaresAvailables.clear();
			randomChoise =getRandomChoise(DirectionMap, targetMatrix,squaresAvailables,threeDirectionMatrix,directionAvailables);

			//y las agregamos a un array, en proporciones dadas por la inteligencia de cada ghost
			for (int i = 0; i < ghost.getIntelligence(); i++) {
				potentialDirectionsList.add(smartChoise);
			}
			for (int i = 0; i < stupidity; i++) {
				potentialDirectionsList.add(randomChoise);
			}
			int aux = random.nextInt(potentialDirectionsList.size());
			return potentialDirectionsList.get(aux);
			

		}
		
		//GENERAMOS LA MEJOR DECISION
		public Direction getSmartPotentialDirection(Map<Square, Direction> directionMap, ArrayList<Direction> smartDirectionAvailables2, ArrayList<Direction> targetMatrix2, ArrayList<Square> squaresAvailables2, ArrayList<Square> threeDirectionMatrix2, ArrayList<Direction> directionAvailables2)
		{
			generateSmartDirectionArray(DirectionMap, smartDirectionAvailables,targetMatrix,squaresAvailables,threeDirectionMatrix,directionAvailables);
			Random random = new Random();
			Direction bestChoise = null;
			
			if (smartDirectionAvailables.size()>0) {
			int aux = random.nextInt(smartDirectionAvailables.size());
			bestChoise= smartDirectionAvailables.get(aux);
			}else bestChoise =Direction.LEFT;

			return bestChoise;
		}
	
		//GENERAMOS UNA MATRIZ CON LAS MEJORES DECISIONES
		public void generateSmartDirectionArray(Map<Square, Direction> directionMap, ArrayList<Direction> smartDirectionAvailables2, ArrayList<Direction> targetMatrix2, ArrayList<Square> squaresAvailables2, ArrayList<Square> threeDirectionMatrix2, ArrayList<Direction> directionAvailables2)
		{
			CreateDirectionAvailablesArray(DirectionMap,squaresAvailables,threeDirectionMatrix,directionAvailables);
			localizateTarget(targetMatrix);
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
		private void localizateTarget(ArrayList<Direction> targetMatrix) {
			Position ghostPosition = ghost.getBoardPosition();
			Position targetPosition = ghost.getTarget();
			if(ghostPosition.getX()>=targetPosition.getX())
			{	
				targetMatrix.add(Direction.LEFT);

			}
			if(ghostPosition.getX()<=targetPosition.getX())
			{	
				targetMatrix.add(Direction.RIGHT);

			}
			if(ghostPosition.getY()>=targetPosition.getY())
			{
				targetMatrix.add(Direction.UP);

			}
			if(ghostPosition.getY()<=targetPosition.getY())
			{	
				
				targetMatrix.add(Direction.DOWN);
			}

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
					//System.out.println(squaresAvailables.size());
					//System.out.println(threeDirectionMatrix.size());

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
