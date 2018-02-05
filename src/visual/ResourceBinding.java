package visual;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import model.Ghost;
import model.Hell;
import model.Pacman;
import model.Path;
import model.Wall;

public abstract class ResourceBinding {

	private static Map<Class<? extends Object>, ImageIcon> images = new HashMap<Class<? extends Object>, ImageIcon>();

	static {
		images.put(Path.class, new ImageIcon("resources/1.png"));
		images.put(Wall.class, new ImageIcon("resources/0.png"));
		images.put(Hell.class, new ImageIcon("resources/6.png"));
		images.put(Pacman.class, new ImageIcon("resources/CRIATURA.png"));
<<<<<<< HEAD
=======
		images.put(Ghost.class, new ImageIcon("resources/GHOST.png"));
>>>>>>> 23109994925dd78db28c484068f24dbf2c728aa5

	}

	static public ImageIcon getImageIcon(Object object) {
		return images.get(object.getClass());
	}
}
