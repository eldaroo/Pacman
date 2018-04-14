package visual;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import model.Direction;
import model.Dot;
import model.Fruit;
import model.creatures.Ghost;
import model.creatures.Pacman;
import model.creatures.ghostStates.Courageous;
import model.creatures.ghostStates.Death;
import model.creatures.ghostStates.Eated;
import model.creatures.ghostStates.Hurry;
import model.creatures.ghostStates.InHell;
import model.creatures.ghostStates.Pussy;
import model.squares.FalseHell;
import model.squares.FalsePath;
import model.squares.FalseTeleport;
import model.squares.Hell;
import model.squares.HellGate;
import model.squares.Path;
import model.squares.Square;
import model.squares.Teleport;
import model.squares.Wall;
import model.squares.Square.Corner;
import model.SuperDot;

public abstract class ResourceBinding {
	//MAPA DE BOARD
	private static Map<Corner, ImageIcon> wallIcon = new HashMap<Corner, ImageIcon>();
	private static Map<Corner, ImageIcon> falsePathIcon = new HashMap<Corner, ImageIcon>();
	private static Map<Class<?>, Map<Square.Corner, ImageIcon>> squareBoard = new HashMap<Class<?>, Map<Square.Corner, ImageIcon>>();
	private static Map<Class<? extends Object>, ImageIcon> images = new HashMap<Class<? extends Object>, ImageIcon>();
	private static Map<Enum<?>, ImageIcon> fruitIcon = new HashMap<Enum<?>, ImageIcon>();
	//MAPAS DE ESTADO DE PACMAN
	private static Map<Enum<?>, Map<Direction, ImageIcon>> pacmanState = new HashMap<Enum<?>, Map<Direction, ImageIcon>>();
	private static Map<Direction, ImageIcon> pacmanWithDirection = new HashMap<Direction, ImageIcon>();
	private static Map<Integer, ImageIcon> pacmanEatingGhost = new HashMap<Integer, ImageIcon>();
	private static Map<Direction, ImageIcon> pacmanDying = new HashMap<Direction, ImageIcon>();
	//MAPAS DE ESTADO DEL GHOST
	private static Map<String, Map<Integer, ImageIcon>> ghostState = new HashMap<String, Map<Integer, ImageIcon>>();
	private static Map<Integer, ImageIcon> ghostAlive = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostEated = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostDeath = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostPussy = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostHurry = new HashMap<Integer, ImageIcon>();

	

	static {
		squareBoard.put(FalsePath.class, falsePathIcon);
		squareBoard.put(Wall.class, wallIcon);
		squareBoard.put(Path.class, falsePathIcon);
	}	
	static {
		fruitIcon.put(Fruit.FruitType.BANANNA, new ImageIcon("resources/fruit_lsd.gif"));
		fruitIcon.put(Fruit.FruitType.CHERRY, new ImageIcon("resources/fruit_joint.gif"));
		fruitIcon.put(Fruit.FruitType.ORANGE, new ImageIcon("resources/fruit_cogollo.gif"));
		fruitIcon.put(Fruit.FruitType.APPLE, new ImageIcon("resources/fruit_pipe.gif"));
	}
	public static ImageIcon getFruitIcon(Fruit fruit) {
		return fruitIcon.get(Fruit.getFruitType());
	}

	static {
		wallIcon.put(Square.Corner.NE, new ImageIcon("resources/wall_NE.gif"));
		wallIcon.put(Square.Corner.NW, new ImageIcon("resources/wall_NW.gif"));
		wallIcon.put(Square.Corner.SW, new ImageIcon("resources/wall_SW.gif"));
		wallIcon.put(Square.Corner.SE, new ImageIcon("resources/wall_SE.gif"));
	}
	static {
		falsePathIcon.put(Square.Corner.SE, new ImageIcon("resources/path_SE.gif"));
		falsePathIcon.put(Square.Corner.SW, new ImageIcon("resources/path_SW.gif"));
		falsePathIcon.put(Square.Corner.NE, new ImageIcon("resources/path_NE.gif"));
		falsePathIcon.put(Square.Corner.NW, new ImageIcon("resources/path_NW.gif"));
	}
	
	static {
		images.put(Path.class, new ImageIcon("resources/1.png"));
		images.put(Wall.class, new ImageIcon("resources/0.png"));
		images.put(Hell.class, new ImageIcon("resources/6.png"));
		images.put(HellGate.class, new ImageIcon("resources/6.png"));
		images.put(FalseHell.class, new ImageIcon("resources/6.png"));
		images.put(FalseTeleport.class, new ImageIcon("resources/6.png"));
		images.put(Teleport.class, new ImageIcon("resources/6.png"));
		images.put(FalsePath.class, new ImageIcon("resources/1.png"));
		images.put(Dot.class, new ImageIcon("resources/chala.gif"));
		images.put(SuperDot.class, new ImageIcon("resources/superchala_b.gif"));
		images.put(BeginMenu.class, new ImageIcon("resources/paco_inicio.gif"));

	}

	static {
		pacmanWithDirection.put(Direction.DOWN, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.LEFT, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.UP, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.RIGHT, new ImageIcon("resources/pacoman_back.png"));
	}
	static {
		pacmanEatingGhost.put(1, new ImageIcon("resources/eat_score_1.gif"));
		pacmanEatingGhost.put(2, new ImageIcon("resources/eat_score_2.gif"));
		pacmanEatingGhost.put(3, new ImageIcon("resources/eat_score_3.gif"));
		pacmanEatingGhost.put(4, new ImageIcon("resources/eat_score_4.gif"));
		pacmanEatingGhost.put(5, new ImageIcon("resources/eat_score_5.gif"));
	}

	static {

		ghostAlive.put(5, new ImageIcon("resources/police1.gif"));
		ghostAlive.put(6, new ImageIcon("resources/police3.gif"));
		ghostAlive.put(7, new ImageIcon("resources/police5.gif"));
		ghostAlive.put(8, new ImageIcon("resources/police7.gif"));
		ghostAlive.put(9, new ImageIcon("resources/police9.gif"));

	}
	static {

		ghostDeath.put(5, new ImageIcon("resources/police_death.gif"));
		ghostDeath.put(6, new ImageIcon("resources/police_death.gif"));
		ghostDeath.put(7, new ImageIcon("resources/police_death.gif"));
		ghostDeath.put(8, new ImageIcon("resources/police_death.gif"));
		ghostDeath.put(9, new ImageIcon("resources/police_death.gif"));

	}
	static {

		ghostEated.put(5, new ImageIcon("resources/"));
		ghostEated.put(6, new ImageIcon("resources/"));
		ghostEated.put(7, new ImageIcon("resources/"));
		ghostEated.put(8, new ImageIcon("resources/"));
		ghostEated.put(9, new ImageIcon("resources/"));

	}
	static {

		ghostPussy.put(5, new ImageIcon("resources/police_pussy.gif"));
		ghostPussy.put(6, new ImageIcon("resources/police_pussy.gif"));
		ghostPussy.put(7, new ImageIcon("resources/police_pussy.gif"));
		ghostPussy.put(8, new ImageIcon("resources/police_pussy.gif"));
		ghostPussy.put(9, new ImageIcon("resources/police_pussy.gif"));

	}
	static {

		ghostHurry.put(5, new ImageIcon("resources/police_hurry.gif"));
		ghostHurry.put(6, new ImageIcon("resources/police_hurry.gif"));
		ghostHurry.put(7, new ImageIcon("resources/police_hurry.gif"));
		ghostHurry.put(8, new ImageIcon("resources/police_hurry.gif"));
		ghostHurry.put(9, new ImageIcon("resources/police_hurry.gif"));

	}

	static {
		ghostState.put("Courageous", ghostAlive);
		ghostState.put("Death", ghostDeath);
		ghostState.put("Pussy", ghostPussy);
		ghostState.put("Eated", ghostEated);
		ghostState.put("InHell", ghostAlive);
		ghostState.put("Hurry", ghostHurry);
	}

	static {
		pacmanState.put(Pacman.PacmanState.MOVE, pacmanWithDirection);
		pacmanState.put(Pacman.PacmanState.DEATH, pacmanDying);
	}
	

	static public ImageIcon getImageIcon(Object object) {
		return images.get(object.getClass());
	}

	static public ImageIcon getPacmanIcon(Pacman pacman) {
		Map<Direction, ImageIcon> stateIcon = new HashMap<Direction, ImageIcon>();
		stateIcon = pacmanState.get(pacman.getPacmanState());
		return stateIcon.get(pacman.getDirection());
	}
	
	static public ImageIcon getEatIcon (Pacman pacman) {
		System.out.println(pacman.getGhostsEated());
		return pacmanEatingGhost.get(pacman.getGhostsEated());
	}

	static public ImageIcon getCornerIcon(Square object) {
		Map<Corner, ImageIcon> cornerIcon = new HashMap<Corner, ImageIcon>();
		cornerIcon = squareBoard.get(object.getClass());
		return cornerIcon.get(object.getCorner());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	static public ImageIcon getGhostIcon(Ghost ghost) {
		Map<Integer, ImageIcon> stateIcon = new HashMap<Integer, ImageIcon>();
		stateIcon = ghostState.get(ghost.getState().toString());
		return stateIcon.get(ghost.getIntelligence());
	}

}
