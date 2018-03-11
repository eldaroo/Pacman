package visual;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

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
		images.put(Pacman.class, new ImageIcon("resources/pacoman.png"));
		images.put(FalsePath.class, new ImageIcon("resources/1.png"));
		images.put(Dot.class, new ImageIcon("resources/chala.png"));
		images.put(SuperDot.class, new ImageIcon("resources/superchala.png"));
		images.put(BeginMenu.class, new ImageIcon("resources/inicio.gif"));

	}

	static {
		pacmanWithDirection.put(Direction.DOWN, new ImageIcon("resources/pacoman_back.png"));
		pacmanWithDirection.put(Direction.LEFT, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.UP, new ImageIcon("resources/pacoman.png"));
		pacmanWithDirection.put(Direction.RIGHT, new ImageIcon("resources/pacoman_back.png"));
	}

	static {

		ghostAlive.put(1, new ImageIcon("resources/police1.png"));
		ghostAlive.put(3, new ImageIcon("resources/police3.png"));
		ghostAlive.put(5, new ImageIcon("resources/police5.png"));
		ghostAlive.put(7, new ImageIcon("resources/police7.png"));
		ghostAlive.put(9, new ImageIcon("resources/police9.png"));

	}
	static {

		ghostDeath.put(1, new ImageIcon("resources/police1.png"));
		ghostDeath.put(3, new ImageIcon("resources/police3.png"));
		ghostDeath.put(5, new ImageIcon("resources/police5.png"));
		ghostDeath.put(7, new ImageIcon("resources/police7.png"));
		ghostDeath.put(9, new ImageIcon("resources/police9.png"));

	}
	static {

		ghostEated.put(1, new ImageIcon("resources/police1.png"));
		ghostEated.put(3, new ImageIcon("resources/police3.png"));
		ghostEated.put(5, new ImageIcon("resources/police5.png"));
		ghostEated.put(7, new ImageIcon("resources/police7.png"));
		ghostEated.put(9, new ImageIcon("resources/police9.png"));

	}
	static {

		ghostAlive.put(1, new ImageIcon("resources/police1.png"));
		ghostAlive.put(3, new ImageIcon("resources/police3.png"));
		ghostAlive.put(5, new ImageIcon("resources/police5.png"));
		ghostAlive.put(7, new ImageIcon("resources/police7.png"));
		ghostAlive.put(9, new ImageIcon("resources/police9.png"));

	}

	static {
		ghostState.put(Ghost.GhostState.COURAGEOUS, ghostAlive);
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

	static public ImageIcon getGhostIcon(Ghost ghost) {
		return ghostAlive.get(ghost.getIntelligence());
	}
}
