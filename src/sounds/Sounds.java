package sounds;

import java.applet.AudioClip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.states.GameState;
import model.Board;
import model.creatures.IA;
import visual.ImageBinding;

public class Sounds {
	AudioClip  music;
	
	public void reproduceMusic(String state) throws InterruptedException, FileNotFoundException {
		if (music != null) {
			music.stop();
		}
		switch (state) {
		case "Load":
			chageToLoad();
			break;
		case "Normal":
			changeToNormal();
			break;
		case "Super":
			changeToSuper();
			break;
		case "PostGame":
			changeToPostGame();
			break;
		default:
			music.stop();
			break;
		}
	
			music.loop();


	}

	public void reproduceBeginning() throws InterruptedException
	{
		music.stop();
		String url = SoundsBinding.getBeginningSounds(IA.random(2));

		AudioClip  sound = java.applet.Applet.newAudioClip(getClass().getResource(url));
		sound.play();
		Thread.sleep(5500);

	}

	public void reproduceLifeUp()
	{
		String url = SoundsBinding.getGameSounds("lifeUp");

		AudioClip  sound = java.applet.Applet.newAudioClip(getClass().getResource(url));
		sound.play();
	}
	public void changeToPostGame()
	{
		String url = SoundsBinding.getGameSounds("postGame");

		music = java.applet.Applet.newAudioClip(getClass().getResource(url));
		
	}
	public void reproduceEatGhost(int ghostEated) throws InterruptedException
	{
		String url = SoundsBinding.getDeathSounds(ghostEated);
		 AudioClip sound = java.applet.Applet.newAudioClip(getClass().getResource(url));
		sound.play();
	    Thread.sleep(100);
		

	}
	public void chageToLoad() throws InterruptedException, FileNotFoundException
	{
		String url = SoundsBinding.getGameSounds("load");
		music = java.applet.Applet.newAudioClip(getClass().getResource(url));
	    
	}
	
	public void changeToNormal() throws InterruptedException
	{
		String url = SoundsBinding.getNormalMusic(Board.getLevel());

		music = java.applet.Applet.newAudioClip(getClass().getResource(url));

	    
	}
	public void changeToSuper() throws InterruptedException
	{
		String url = SoundsBinding.getSuperMusic(IA.random(2));

		music= java.applet.Applet.newAudioClip(getClass().getResource(url));

	    
	}
	public static void reproduceDeath()
	{

	}

	public void reproduceLevelUp() {
		String url = SoundsBinding.getGameSounds("levelUp");

		AudioClip  sound = java.applet.Applet.newAudioClip(getClass().getResource(url));
		sound.play();		
	}


}
