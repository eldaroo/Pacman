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
	
	
	
	static {
		images.put(Path.class, new ImageIcon("resources/1.png"));
		images.put(Wall.class, new ImageIcon("resources/0.png"));
		images.put(Hell.class, new ImageIcon("resources/6.png"));
		images.put(HellGate.class, new ImageIcon("resources/6.png"));
		images.put(FalseHell.class, new ImageIcon("resources/6.png"));
		images.put(Pacman.class, new ImageIcon("resources/CRIATURA.png"));
		images.put(FalsePath.class, new ImageIcon("resources/1.png"));
		images.put(Ghost.class, new ImageIcon("resources/GHOST.png"));
		images.put(Dot.class, new ImageIcon("resources/dot.png"));
		images.put(SuperDot.class, new ImageIcon("resources/superdot.png"));
		images.put(BeginMenu.class, new ImageIcon("resources/inicio.gif"));
		
	}
	
	/*private static Map <Direction, ImageIcon> pacmanWithDirection = new HashMap <Direction, ImageIcon>( );
	
	static {
		pacmanWithDirection.put(Direction.DOWN, new ImageIcon("resources/CRIATURA.png"));
		pacmanWithDirection.put(Direction.LEFT, new ImageIcon("resources/CRIATURA.png"));
		pacmanWithDirection.put(Direction.UP, new ImageIcon("resources/CRIATURA.png"));
		pacmanWithDirection.put(Direction.RIGHT, new ImageIcon("resources/CRIATURA.png"));
	}*/
	//HAY QUE VER COMO RESOLVER LOS RECURSOS PARA LAS ANIMACIONES Y LOS CAMBIOS DE SENTIDO
	
	static public ImageIcon getImageIcon(Object object) {
		return images.get(object.getClass());
	}
}
