package visual;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import model.Dot;
import model.FalsePath;
import model.Ghost;
import model.Hell;
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
		images.put(Pacman.class, new ImageIcon("resources/CRIATURA.png"));
		images.put(FalsePath.class, new ImageIcon("resources/1.png"));
		images.put(Ghost.class, new ImageIcon("resources/GHOST.png"));
		images.put(Dot.class, new ImageIcon("resources/dot.png"));
		images.put(SuperDot.class, new ImageIcon("resources/superdot.png"));
		
	}
	
	
	//HAY QUE VER COMO RESOLVER LOS RECURSOS PARA LAS ANIMACIONES Y LOS CAMBIOS DE SENTIDO
	
	static public ImageIcon getImageIcon(Object object) {
		return images.get(object.getClass());
	}
}
