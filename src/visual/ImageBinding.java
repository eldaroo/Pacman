package visual;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import model.Direction;
import model.board.Dot;
import model.board.Fruit;
import model.board.SuperDot;
import model.creatures.Ghost;
import model.creatures.Pacman;

public abstract class ImageBinding {
	// MAPA DE BOARD
	private static Map<Class<? extends Object>, ImageIcon> images = new HashMap<Class<? extends Object>, ImageIcon>();
	private static Map<Enum<?>, ImageIcon> fruitIcon = new HashMap<Enum<?>, ImageIcon>();
	// MAPAS DE ESTADO DE PACMAN
	private static Map<Enum<?>, Map<Direction, ImageIcon>> pacmanState = new HashMap<Enum<?>, Map<Direction, ImageIcon>>();
	private static Map<Direction, ImageIcon> pacmanWithDirection = new HashMap<Direction, ImageIcon>();
	private static Map<Integer, ImageIcon> pacmanEatingGhost = new HashMap<Integer, ImageIcon>();
	private static Map<Direction, ImageIcon> pacmanDying = new HashMap<Direction, ImageIcon>();
	// MAPAS DE ESTADO DEL GHOST
	private static Map<String, Map<Integer, ImageIcon>> ghostState = new HashMap<String, Map<Integer, ImageIcon>>();
	private static Map<Integer, ImageIcon> ghostAlive = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostEated = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostDeath = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostPussy = new HashMap<Integer, ImageIcon>();
	private static Map<Integer, ImageIcon> ghostHurry = new HashMap<Integer, ImageIcon>();
	// PATH
	private static String i = "resources/images/";

	public static ImageIcon getFruitIcon(Fruit fruit) {
		return fruitIcon.get(Fruit.getFruitType());
	}
	
	static {
		fruitIcon.put(Fruit.FruitType.BANANNA, new ImageIcon(i + "fruit_lsd.gif"));
		fruitIcon.put(Fruit.FruitType.CHERRY, new ImageIcon(i + "fruit_joint.gif"));
		fruitIcon.put(Fruit.FruitType.ORANGE, new ImageIcon(i + "fruit_cogollo.gif"));
		fruitIcon.put(Fruit.FruitType.APPLE, new ImageIcon(i + "fruit_pipe.gif"));

		images.put(Dot.class, new ImageIcon(i+"chala.gif"));
		images.put(SuperDot.class, new ImageIcon(i+"superchala_b.gif"));
		images.put(BeginMenu.class, new ImageIcon(i+"paco_inicio.gif"));

		pacmanWithDirection.put(Direction.DOWN, new ImageIcon(i+"pacoman.png"));
		pacmanWithDirection.put(Direction.LEFT, new ImageIcon(i+"pacoman.png"));
		pacmanWithDirection.put(Direction.UP, new ImageIcon(i+"pacoman.png"));
		pacmanWithDirection.put(Direction.RIGHT, new ImageIcon(i+"pacoman_back.png"));

		pacmanEatingGhost.put(1, new ImageIcon(i+"eat_score_1.gif"));
		pacmanEatingGhost.put(2, new ImageIcon(i+"eat_score_2.gif"));
		pacmanEatingGhost.put(3, new ImageIcon(i+"eat_score_3.gif"));
		pacmanEatingGhost.put(4, new ImageIcon(i+"eat_score_4.gif"));
		pacmanEatingGhost.put(5, new ImageIcon(i+"eat_score_5.gif"));

		ghostAlive.put(5, new ImageIcon(i+"police1.gif"));
		ghostAlive.put(6, new ImageIcon(i+"police3.gif"));
		ghostAlive.put(7, new ImageIcon(i+"police5.gif"));
		ghostAlive.put(8, new ImageIcon(i+"police7.gif"));
		ghostAlive.put(9, new ImageIcon(i+"police9.gif"));

		ghostDeath.put(5, new ImageIcon(i+"police_death.gif"));
		ghostDeath.put(6, new ImageIcon(i+"police_death.gif"));
		ghostDeath.put(7, new ImageIcon(i+"police_death.gif"));
		ghostDeath.put(8, new ImageIcon(i+"police_death.gif"));
		ghostDeath.put(9, new ImageIcon(i+"police_death.gif"));

		ghostEated.put(5, new ImageIcon(i+""));
		ghostEated.put(6, new ImageIcon(i+""));
		ghostEated.put(7, new ImageIcon(i+""));
		ghostEated.put(8, new ImageIcon(i+""));
		ghostEated.put(9, new ImageIcon(i+""));

		ghostPussy.put(5, new ImageIcon(i+"police_pussy.gif"));
		ghostPussy.put(6, new ImageIcon(i+"police_pussy.gif"));
		ghostPussy.put(7, new ImageIcon(i+"police_pussy.gif"));
		ghostPussy.put(8, new ImageIcon(i+"police_pussy.gif"));
		ghostPussy.put(9, new ImageIcon(i+"police_pussy.gif"));

		ghostHurry.put(5, new ImageIcon(i+"police_hurry.gif"));
		ghostHurry.put(6, new ImageIcon(i+"police_hurry.gif"));
		ghostHurry.put(7, new ImageIcon(i+"police_hurry.gif"));
		ghostHurry.put(8, new ImageIcon(i+"police_hurry.gif"));
		ghostHurry.put(9, new ImageIcon(i+"police_hurry.gif"));
		
		ghostState.put("Courageous", ghostAlive);
		ghostState.put("Death", ghostDeath);
		ghostState.put("Pussy", ghostPussy);
		ghostState.put("Eated", ghostEated);
		ghostState.put("InHell", ghostAlive);
		ghostState.put("Hurry", ghostHurry);
		
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

	static public ImageIcon getEatIcon(Pacman pacman) {
		return pacmanEatingGhost.get(pacman.getGhostsEated());
	}

	static public ImageIcon getGhostIcon(Ghost ghost) {
		Map<Integer, ImageIcon> stateIcon = new HashMap<Integer, ImageIcon>();
		stateIcon = ghostState.get(ghost.getState().toString());
		return stateIcon.get(ghost.getIntelligence());
	}

	public static Icon getBoard(long l) {
		// return new ImageIcon("resources/board600x600.png");
		return new ImageIcon(i+"animatedBoard_"+l +".gif");
		//return new ImageIcon( imageBinding.getClass().getResource("/img/imagen.gif"));
	}

}
