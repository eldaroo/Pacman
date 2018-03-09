package visual;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

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
	private static Map <Direction, ImageIcon> pacmanWithDirection = new HashMap <Direction, ImageIcon>( );
	private static Map <Integer, ImageIcon > ghostWithIntelligence = new HashMap <Integer, ImageIcon>( );

	
	
	static {
		images.put(Path.class, new ImageIcon("resources/1.png"));
		images.put(Wall.class, new ImageIcon("resources/0.png"));
		images.put(Hell.class, new ImageIcon("resources/6.png"));
		images.put(HellGate.class, new ImageIcon("resources/6.png"));
		images.put(FalseHell.class, new ImageIcon("resources/6.png"));
		images.put(Pacman.class, new ImageIcon("resources/CRIATURA.png"));
		images.put(FalsePath.class, new ImageIcon("resources/1.png"));
		images.put(Dot.class, new ImageIcon("resources/dot.png"));
		images.put(SuperDot.class, new ImageIcon("resources/superdot.png"));
		images.put(BeginMenu.class, new ImageIcon("resources/inicio.gif"));
		
	}
	
	static {
		pacmanWithDirection.put(Direction.DOWN, new ImageIcon("resources/CRIATURA.png"));
		pacmanWithDirection.put(Direction.LEFT, new ImageIcon("resources/CRIATURA.png"));
		pacmanWithDirection.put(Direction.UP, new ImageIcon("resources/CRIATURA.png"));
		pacmanWithDirection.put(Direction.RIGHT, new ImageIcon("resources/CRIATURA.png"));
	}
	
	static {
		
		ghostWithIntelligence.put (1, new ImageIcon("resources/GHOST.png"));
		ghostWithIntelligence.put (3, new ImageIcon("resources/GHOST.png"));
		ghostWithIntelligence.put (5, new ImageIcon("resources/GHOST.png"));
		ghostWithIntelligence.put (7, new ImageIcon("resources/GHOST.png"));
		ghostWithIntelligence.put (9, new ImageIcon("resources/GHOST.png"));

	}
	
	static public ImageIcon getImageIcon(Object object) {
		return images.get(object.getClass());
	}
	static public ImageIcon getPacmanIcon(Pacman pacman) {
		return pacmanWithDirection.get(pacman.getDirection());
	}
	static public ImageIcon getGhostIcon(Ghost ghost) {
		return ghostWithIntelligence.get(ghost.getIntelligence());
	}
}
