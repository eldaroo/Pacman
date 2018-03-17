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
		wallIcon.put(Square.Corner.NE, new ImageIcon("resources/w_sw.png"));
		wallIcon.put(Square.Corner.NW, new ImageIcon("resources/w_se.png"));
		wallIcon.put(Square.Corner.SW, new ImageIcon("resources/w_ne.png"));
		wallIcon.put(Square.Corner.SE, new ImageIcon("resources/w_nw.png"));
	}
	static {
		falsePathIcon.put(Square.Corner.SE, new ImageIcon("resources/"));
		falsePathIcon.put(Square.Corner.SW, new ImageIcon("resources/"));
		falsePathIcon.put(Square.Corner.NE, new ImageIcon("resources/"));
		falsePathIcon.put(Square.Corner.NW, new ImageIcon("resources/"));
		falsePathIcon.put(Square.Corner.CENTER, new ImageIcon("resources/"));
	}
	
	static {
		images.put(Path.class, new ImageIcon("resources/1.png"));
		images.put(Wall.class, new ImageIcon("resources/0.png"));
		images.put(Hell.class, new ImageIcon("resources/6.png"));
		images.put(HellGate.class, new ImageIcon("resources/1.png"));
		images.put(FalseHell.class, new ImageIcon("resources/6.png"));
		images.put(FalseTeleport.class, new ImageIcon("resources/6.png"));
		images.put(FalsePath.class, new ImageIcon("resources/1.png"));
		images.put(Dot.class, new ImageIcon("resources/chala.png"));
		images.put(SuperDot.class, new ImageIcon("resources/superchala.png"));
		images.put(BeginMenu.class, new ImageIcon("resources/inicio.gif"));

	}

	static {
		pacmanWithDirection.put(Direction.DOWN, new ImageIcon("resources/paco-man.gif"));
		pacmanWithDirection.put(Direction.LEFT, new ImageIcon("resources/paco-man.gif"));
		pacmanWithDirection.put(Direction.UP, new ImageIcon("resources/paco-man.gif"));
		pacmanWithDirection.put(Direction.RIGHT, new ImageIcon("resources/paco-man_back.gif"));
	}
	static {
		pacmanEatingDot.put(Direction.DOWN, new ImageIcon("resources/pacoman_eating_dot.gif"));
		pacmanEatingDot.put(Direction.LEFT, new ImageIcon("resources/pacoman_eating_dot.gif"));
		pacmanEatingDot.put(Direction.UP, new ImageIcon("resources/pacoman_eating_dot.gif"));
		pacmanEatingDot.put(Direction.RIGHT, new ImageIcon("resources/pacoman_eating_dot_back.gif"));
	}
	static {
		pacmanEatingSuper.put(Direction.DOWN, new ImageIcon("resources/pacoman_eating_super.gif"));
		pacmanEatingSuper.put(Direction.LEFT, new ImageIcon("resources/pacoman_eating_super.gif"));
		pacmanEatingSuper.put(Direction.UP, new ImageIcon("resources/pacoman_eating_super.gif"));
		pacmanEatingSuper.put(Direction.RIGHT, new ImageIcon("resources/pacoman_eating_super_back.gif"));
	}
	static {
		pacmanEatingGhost.put(Direction.DOWN, new ImageIcon("resources/pacoman_eating_ghost.gif"));
		pacmanEatingGhost.put(Direction.LEFT, new ImageIcon("resources/pacoman_eating_ghost.gif"));
		pacmanEatingGhost.put(Direction.UP, new ImageIcon("resources/pacoman_eating_ghost.gif"));
		pacmanEatingGhost.put(Direction.RIGHT, new ImageIcon("resources/pacoman_eating_ghost_back.gif"));
	}

	static {

		ghostAlive.put(1, new ImageIcon("resources/police1.png"));
		ghostAlive.put(3, new ImageIcon("resources/police3.png"));
		ghostAlive.put(5, new ImageIcon("resources/police5.png"));
		ghostAlive.put(7, new ImageIcon("resources/police7.png"));
		ghostAlive.put(9, new ImageIcon("resources/police9.png"));

	}
	static {

		ghostDeath.put(1, new ImageIcon("resources/police_death_1.png"));
		ghostDeath.put(3, new ImageIcon("resources/police_death_3.png"));
		ghostDeath.put(5, new ImageIcon("resources/police_death_5.png"));
		ghostDeath.put(7, new ImageIcon("resources/police_death_7.png"));
		ghostDeath.put(9, new ImageIcon("resources/police_death_9.png"));

	}
	static {

		ghostEated.put(1, new ImageIcon("resources/police_eated_1.png"));
		ghostEated.put(3, new ImageIcon("resources/police_eated_3.png"));
		ghostEated.put(5, new ImageIcon("resources/police_eated_5.png"));
		ghostEated.put(7, new ImageIcon("resources/police_eated_7.png"));
		ghostEated.put(9, new ImageIcon("resources/police_eated_9.png"));

	}
	static {

		ghostPussy.put(1, new ImageIcon("resources/police_pussy_1.png"));
		ghostPussy.put(3, new ImageIcon("resources/police_pussy_3.png"));
		ghostPussy.put(5, new ImageIcon("resources/police_pussy_5.png"));
		ghostPussy.put(7, new ImageIcon("resources/police_pussy_7.png"));
		ghostPussy.put(9, new ImageIcon("resources/police_pussy_9.png"));

	}
	static {

		ghostHurry.put(1, new ImageIcon("resources/police_pussy_1.png"));
		ghostHurry.put(3, new ImageIcon("resources/police_pussy_3.png"));
		ghostHurry.put(5, new ImageIcon("resources/police_pussy_5.png"));
		ghostHurry.put(7, new ImageIcon("resources/police_pussy_7.png"));
		ghostHurry.put(9, new ImageIcon("resources/police_pussy_9.png"));

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
		pacmanState.put(Pacman.PacmanState.EATDOT, pacmanEatingDot);
		pacmanState.put(Pacman.PacmanState.EATSUPER, pacmanEatingSuper);
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
