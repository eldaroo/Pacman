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
import model.Ghost;
import model.Hell;
import model.HellGate;
import model.Pacman;
import model.Path;
import model.SuperDot;
import model.Wall;

public abstract class ResourceBinding {

	private static Map<Class<? extends Object>, ImageIcon> images = new HashMap<Class<? extends Object>, ImageIcon>();
	
	private static Map<Direction, ImageIcon> pacmanWithDirection = new HashMap<Direction, ImageIcon>();
	private static Map<Direction, ImageIcon> pacmanEatingDot = new HashMap<Direction, ImageIcon>();
	private static Map<Direction, ImageIcon> pacmanEatingSuper = new HashMap<Direction, ImageIcon>();
	private static Map<Direction, ImageIcon> pacmanEatingGhost = new HashMap<Direction, ImageIcon>();
	
	private static Map<Enum<?>, Map<Integer, ImageIcon>> ghostState = new HashMap<Enum<?>, Map<Integer, ImageIcon>>();
	private static Map<Integer, ImageIcon> ghostAlive = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostEated = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostDeath = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostPussy = new HashMap<Integer, ImageIcon>();

	static {
		images.put(Path.class, new ImageIcon("resources/1.png"));
		images.put(Wall.class, new ImageIcon("resources/0.png"));
		images.put(Hell.class, new ImageIcon("resources/6.png"));
		images.put(HellGate.class, new ImageIcon("resources/6.png"));
		images.put(FalseHell.class, new ImageIcon("resources/6.png"));
		images.put(Pacman.class, new ImageIcon("resources/pacoman"));
		images.put(FalsePath.class, new ImageIcon("resources/1.png"));
		images.put(Dot.class, new ImageIcon("resources/chala.png"));
		images.put(SuperDot.class, new ImageIcon("resources/superchala.png"));
		images.put(BeginMenu.class, new ImageIcon("resources/inicio.gif"));

	}

	static {
		pacmanWithDirection.put(Direction.DOWN, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.LEFT, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.UP, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.RIGHT, new ImageIcon("resources/pacoman_back.png"));
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
		pacmanEatingSuper.put(Direction.DOWN, new ImageIcon("resources/pacoman_eating_ghost.gif"));
		pacmanEatingSuper.put(Direction.LEFT, new ImageIcon("resources/pacoman_eating_ghost.gif"));
		pacmanEatingSuper.put(Direction.UP, new ImageIcon("resources/pacoman_eating_ghost.gif"));
		pacmanEatingSuper.put(Direction.RIGHT, new ImageIcon("resources/pacoman_eating_ghost_back.gif"));
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
		ghostState.put(Ghost.GhostState.ALIVE, ghostAlive);
		ghostState.put(Ghost.GhostState.DEATH, ghostDeath);
		ghostState.put(Ghost.GhostState.PUSSY, ghostPussy);
		ghostState.put(Ghost.GhostState.PUSSY, ghostPussy);
	}

	static public ImageIcon getImageIcon(Object object) {
		return images.get(object.getClass());
	}

	static public ImageIcon getPacmanIcon(Creature pacman) {
		return pacmanWithDirection.get(pacman.getDirection());
	}
	
	/*CON CONDICIONALES POR SI COME DOTS O GHOST
	static public ImageIcon getPacmanIcon(Creature pacman, Board board) {
		if (board.isPacmanEatNewDot()&&!board.dotRemoved.getSuper()&&!pacman.isEatingGhost())
		{
			return pacmanEatingDot.get(pacman.getDirection());
		}else if(board.isPacmanEatNewDot()&&board.dotRemoved.getSuper()&&!pacman.isEatingGhost()){
			return pacmanEatingSuper.get(pacman.getDirection());			
		}else if(&&pacman.isEatingGhost()){
			return pacmanEatingGhost.get(pacman.getDirection());
		}else
		return pacmanWithDirection.get(pacman.getDirection());
	}
	*/
	

	static public ImageIcon getGhostIcon(Ghost ghost) {
		return ghostAlive.get(ghost.getIntelligence());
	}
}
