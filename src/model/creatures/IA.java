package model.creatures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.Direction;
import model.Position;
import model.squares.Square;

public abstract class IA {
	private static Ghost ghost;
	private static ArrayList<Square> threeDirectionMatrix;
	private static Map<Square, Direction> DirectionMap;
	private static ArrayList<Square> squaresAvailables;
	public static ArrayList<Direction> smartDirectionAvailables;
	private static ArrayList<Direction> targetMatrix;
	private static ArrayList<Direction> directionAvailables;
	private static ArrayList<Direction> goAwayTargetMatrix;

	private static Direction smartChoise;
	private static Direction randomChoise;
	private static Direction goAwayDirection;

	public IA(Ghost ghost) {
	}

	public static void runIa(Ghost ghost) {
		IA.ghost = ghost;

		DirectionMap = new HashMap<Square, Direction>();
		threeDirectionMatrix = new ArrayList<Square>();
		squaresAvailables = new ArrayList<Square>();
		smartDirectionAvailables = new ArrayList<Direction>();
		targetMatrix = new ArrayList<Direction>();
		directionAvailables = new ArrayList<Direction>();
		goAwayTargetMatrix = new ArrayList<Direction>();

		randomChoise = getRandomChoice(DirectionMap, targetMatrix, squaresAvailables, threeDirectionMatrix,
				directionAvailables);
		smartChoise = getSmartPotentialDirection();
		generateGoAwayDirection();

	}

	static Direction pussyDirection() {
		return goAwayDirection;
	}

	static Direction smartDirection() {
		return smartChoise;
	}

	public static Direction randomDirection() {
		return randomChoise;
	}

	public static int random(int value) {
		Random random = new Random();
		int aux = random.nextInt(value);
		return aux;
	}

	public static Direction pathFinder(int intelligence) {
		ArrayList<Direction> potentialDirectionsList = new ArrayList<Direction>();
		int stupidity = 10 - intelligence;

		// y las agregamos a un array, en proporciones dadas por la inteligencia de cada

		for (int i = 0; i < ghost.getIntelligence(); i++) {

			potentialDirectionsList.add(smartChoise);

		}
		for (int i = 0; i < stupidity; i++) {
			potentialDirectionsList.add(randomChoise);
		}

		return potentialDirectionsList.get(random(potentialDirectionsList.size()));
	}

	private static void generateGoAwayDirection() {

		goAwayDirection = goAwayTargetMatrix.get(random(goAwayTargetMatrix.size()));
	}

	public static Direction getGoAwayDirection() {
		return goAwayDirection;
	}

	private static Direction getSmartPotentialDirection() {
		Direction bestChoise = null;


			generateSmartDirectionArray();
		
		int aux = random(smartDirectionAvailables.size());
		bestChoise = smartDirectionAvailables.get(aux);

		return bestChoise;
	}

	private static void generateSmartDirectionArray() {
		
		smartDirectionAvailables.clear();
		createDirectionAvailablesArray();
		localizateTarget();
		
		if ((ghost.getState().toString().equals("Pussy")) || (ghost.getState().toString().equals("Hurry"))) {

			for (Direction availableDirection : directionAvailables) {
				for (Direction targetDirection : goAwayTargetMatrix) {
					if (availableDirection.equals(targetDirection)) {
						smartDirectionAvailables.add(availableDirection);

					}
				}
			}
		} else {
		for (Direction availableDirection : directionAvailables) {
			for (Direction targetDirection : targetMatrix) {
				if (availableDirection.equals(targetDirection)) {
					smartDirectionAvailables.add(availableDirection);

				}
			}
		}
		}
		
		  if (smartDirectionAvailables.size()==0) {
		  
		  int aux=0; Random random = new Random(); aux =
		  random.nextInt(directionAvailables.size());
		  smartDirectionAvailables.add(directionAvailables.get(aux));
		  
		  }
		 

	}

	private static void localizateTarget() {
		goAwayTargetMatrix.clear();
		targetMatrix.clear();

		Position ghostPosition = ghost.getBoardPosition();
		Position targetPosition = ghost.getTarget();

		if (ghostPosition.getY() > targetPosition.getY()) {
			targetMatrix.add(Direction.UP);

		} else
			goAwayTargetMatrix.add(Direction.UP);

		if (ghostPosition.getX() >= targetPosition.getX()) {
			targetMatrix.add(Direction.LEFT);
		} else
			goAwayTargetMatrix.add(Direction.LEFT); // Llenamos la matriz opuesta para cuando estan muertos

		if (ghostPosition.getX() < targetPosition.getX()) {
			targetMatrix.add(Direction.RIGHT);

		} else
			goAwayTargetMatrix.add(Direction.RIGHT);

		if (ghostPosition.getY() <= targetPosition.getY()) {

			targetMatrix.add(Direction.DOWN);
		} else
			goAwayTargetMatrix.add(Direction.DOWN);
	}

	private static Direction getRandomChoice(Map<Square, Direction> directionMap, ArrayList<Direction> targetMatrix,
			ArrayList<Square> squaresAvailables, ArrayList<Square> threeDirectionMatrix,
			ArrayList<Direction> directionAvailables) {
		createDirectionAvailablesArray();
		int aux = 0;
		Random random = new Random();
		aux = random.nextInt(directionAvailables.size());
		return directionAvailables.get(aux);

	}

	// VEMOS CUALES DE LAS DIRECCIONES ESTAN DISPONIBLES
	private static void createDirectionAvailablesArray() {
		squaresAvailables.clear();
		directionAvailables.clear();
		createThreeDirectionMatrix(threeDirectionMatrix);
		for (Square square : threeDirectionMatrix) {
			if (square.isNavegable(ghost)) {
				squaresAvailables.add(square);
			}
		}
		for (Square square : squaresAvailables) {
			directionAvailables.add(DirectionMap.get(square));

		}
	}

	// CREAMOS UN ARRAY CON LOS SQUARE POSIBLES, EXCLUYENDO A LA DIRECCION DE
	// LA QUE VIENE.
	// LO MAPEAMOS CON SUS RESPECTIVAS DIRECCIONES
	private static void createThreeDirectionMatrix(ArrayList<Square> threeDirectionMatrix) {
		threeDirectionMatrix.clear();
		DirectionMap.clear();
		if (!ghost.goTo(Direction.UP)) {
			threeDirectionMatrix.add(ghost.position.getDown());
			DirectionMap.put(ghost.position.getDown(), Direction.DOWN);
		}
		if (!ghost.goTo(Direction.LEFT)) {
			threeDirectionMatrix.add(ghost.position.getRight());
			DirectionMap.put(ghost.position.getRight(), Direction.RIGHT);
		}
		if (!ghost.goTo(Direction.DOWN)) {
			threeDirectionMatrix.add(ghost.position.getUp());
			DirectionMap.put(ghost.position.getUp(), Direction.UP);
		}
		if (!ghost.goTo(Direction.RIGHT)) {
			threeDirectionMatrix.add(ghost.position.getLeft());
			DirectionMap.put(ghost.position.getLeft(), Direction.LEFT);
		}
	}

}
