package visual;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import model.Board;
import model.Creature;
import model.Direction;
import model.Dot;
import model.FalseHell;
import model.FalsePath;
import model.FalseTeleport;
import model.Ghost;
import model.Hell;
import model.HellGate;
import model.Pacman;
import model.Path;
import model.Square;
import model.Square.Corner;
import model.SuperDot;
import model.Teleport;
import model.Wall;

public abstract class ResourceBinding {
	//MAPA DE BOARD
	private static Map<Corner, ImageIcon> wallIcon = new HashMap<Corner, ImageIcon>();
	private static Map<Corner, ImageIcon> falsePathIcon = new HashMap<Corner, ImageIcon>();
	private static Map<Class<?>, Map<Square.Corner, ImageIcon>> squareBoard = new HashMap<Class<?>, Map<Square.Corner, ImageIcon>>();
	private static Map<Class<? extends Object>, ImageIcon> images = new HashMap<Class<? extends Object>, ImageIcon>();
	//MAPAS DE ESTADO DE PACMAN
	private static Map<Enum<?>, Map<Direction, ImageIcon>> pacmanState = new HashMap<Enum<?>, Map<Direction, ImageIcon>>();
	private static Map<Direction, ImageIcon> pacmanWithDirection = new HashMap<Direction, ImageIcon>();
	private static Map<Direction, ImageIcon> pacmanEatingDot = new HashMap<Direction, ImageIcon>();
	private static Map<Direction, ImageIcon> pacmanEatingSuper = new HashMap<Direction, ImageIcon>();
	private static Map<Direction, ImageIcon> pacmanEatingGhost = new HashMap<Direction, ImageIcon>();
	private static Map<Direction, ImageIcon> pacmanDying = new HashMap<Direction, ImageIcon>();
	//MAPAS DE ESTADO DEL GHOST
	private static Map<Enum<?>, Map<Integer, ImageIcon>> ghostState = new HashMap<Enum<?>, Map<Integer, ImageIcon>>();
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
		images.put(HellGate.class, new ImageIcon("resources/1.png"));
		images.put(FalseHell.class, new ImageIcon("resources/6.png"));
		images.put(FalseTeleport.class, new ImageIcon("resources/6.png"));
		images.put(Teleport.class, new ImageIcon("resources/6.png"));
		images.put(FalsePath.class, new ImageIcon("resources/1.png"));
		images.put(Dot.class, new ImageIcon("resources/chala.png"));
		images.put(SuperDot.class, new ImageIcon("resources/superchala.gif"));
		images.put(BeginMenu.class, new ImageIcon("resources/paco_inicio.gif"));

	}

	static {
		pacmanWithDirection.put(Direction.DOWN, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.LEFT, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.UP, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.RIGHT, new ImageIcon("resources/pacoman_back.png"));
	}
	static {
		pacmanEatingGhost.put(Direction.DOWN, new ImageIcon("resources/.gif"));
		pacmanEatingGhost.put(Direction.LEFT, new ImageIcon("resources/.gif"));
		pacmanEatingGhost.put(Direction.UP, new ImageIcon("resources/.gif"));
		pacmanEatingGhost.put(Direction.RIGHT, new ImageIcon("resources/.gif"));
	}

	static {

		ghostAlive.put(1, new ImageIcon("resources/police1.png"));
		ghostAlive.put(3, new ImageIcon("resources/police3.png"));
		ghostAlive.put(5, new ImageIcon("resources/police5.png"));
		ghostAlive.put(7, new ImageIcon("resources/police7.png"));
		ghostAlive.put(9, new ImageIcon("resources/police9.png"));

	}
	static {

		ghostDeath.put(1, new ImageIcon("resources/police_death.gif"));
		ghostDeath.put(3, new ImageIcon("resources/police_death.gif"));
		ghostDeath.put(5, new ImageIcon("resources/police_death.gif"));
		ghostDeath.put(7, new ImageIcon("resources/police_death.gif"));
		ghostDeath.put(9, new ImageIcon("resources/police_death.gif"));

	}
	static {

		ghostEated.put(1, new ImageIcon("resources/police_death.gif"));
		ghostEated.put(3, new ImageIcon("resources/police_death.gif"));
		ghostEated.put(5, new ImageIcon("resources/police_death.gif"));
		ghostEated.put(7, new ImageIcon("resources/police_death.gif"));
		ghostEated.put(9, new ImageIcon("resources/police_death.gif"));

	}
	static {

		ghostPussy.put(1, new ImageIcon("resources/police_pussy.gif"));
		ghostPussy.put(3, new ImageIcon("resources/police_pussy.gif"));
		ghostPussy.put(5, new ImageIcon("resources/police_pussy.gif"));
		ghostPussy.put(7, new ImageIcon("resources/police_pussy.gif"));
		ghostPussy.put(9, new ImageIcon("resources/police_pussy.gif"));

	}
	static {

		ghostHurry.put(1, new ImageIcon("resources/police_hurry.gif"));
		ghostHurry.put(3, new ImageIcon("resources/police_hurry.gif"));
		ghostHurry.put(5, new ImageIcon("resources/police_hurry.gif"));
		ghostHurry.put(7, new ImageIcon("resources/police_hurry.gif"));
		ghostHurry.put(9, new ImageIcon("resources/police_hurry.gif"));

	}

	static {
		ghostState.put(Ghost.GhostState.COURAGEOUS, ghostAlive);
		ghostState.put(Ghost.GhostState.DEATH, ghostDeath);
		ghostState.put(Ghost.GhostState.PUSSY, ghostPussy);
		ghostState.put(Ghost.GhostState.EATED, ghostEated);
		ghostState.put(Ghost.GhostState.INHELL, ghostAlive);
		ghostState.put(Ghost.GhostState.HURRY, ghostHurry);
	}

	static {
		pacmanState.put(Pacman.PacmanState.MOVE, pacmanWithDirection);
		pacmanState.put(Pacman.PacmanState.EATGHOST, pacmanEatingGhost);
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

	static public ImageIcon getCornerIcon(Square object) {
		Map<Corner, ImageIcon> cornerIcon = new HashMap<Corner, ImageIcon>();
		cornerIcon = squareBoard.get(object.getClass());
		return cornerIcon.get(object.getCorner());
	}
	
	static public ImageIcon getGhostIcon(Ghost ghost) {
		Map<Integer, ImageIcon> stateIcon = new HashMap<Integer, ImageIcon>();
		stateIcon = ghostState.get(ghost.getGhostState());
		return stateIcon.get(ghost.getIntelligence());
	}
}
