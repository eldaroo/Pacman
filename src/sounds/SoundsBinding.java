package sounds;

import java.util.HashMap;
import java.util.Map;

public abstract class SoundsBinding {

	private static Map <String, String> gameSounds = new HashMap<String, String>();
	private static Map<Integer, String> deathSounds = new HashMap<Integer, String>();
	private static Map<Long, String> normalMusic = new HashMap<Long, String>();
	private static Map<Integer, String> superMusic = new HashMap<Integer, String>();
	private static Map<Integer, String> beginningSounds = new HashMap<Integer, String>();


	static {
		normalMusic.put((long) 1,"/sounds/states/normal0.wav");
		normalMusic.put((long) 2,"/sounds/states/normal1.wav");
		normalMusic.put((long) 3,"/sounds/states/normal2.wav");
	}
	static {
		superMusic.put(0,"/sounds/states/super0.wav");
		superMusic.put(1,"/sounds/states/super1.wav");
		superMusic.put(2,"/sounds/states/super3.wav");
	}
	
	static {
		beginningSounds.put(0,"/sounds/beginning.wav");
		beginningSounds.put(1,"/sounds/beginning2.wav");
	}
	
	static {
		gameSounds.put("load","/sounds/states/load.wav");
		gameSounds.put("lifeUp","/sounds/lifeUp.wav");
		gameSounds.put("postGame","/sounds/states/postGame.wav");
		gameSounds.put("levelUp","/sounds/levelUp.wav");
	}

	static {
		deathSounds.put(1,"/sounds/firstblood.wav");
		deathSounds.put(2,"/sounds/godlike.wav");
		deathSounds.put(3,"/sounds/monsterkill.wav");
		deathSounds.put(4,"/sounds/wickedsick.wav");
		deathSounds.put(5,"/sounds/rampage.wav");
	}
	
	public static String getGameSounds(String gameSound) {
		return gameSounds.get(gameSound);
	}
	public static String getDeathSounds(int ghostEated) {
		return deathSounds.get(ghostEated);
	}
	public static String getBeginningSounds(int random) {
		return beginningSounds.get(random);
	}



	public static String getNormalMusic(long l) {
		return normalMusic.get(l);
	}
	public static String getSuperMusic(Integer aux) {
		return superMusic.get(aux);
	}


}
