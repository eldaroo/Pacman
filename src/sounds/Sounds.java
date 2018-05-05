package sounds;

import java.applet.AudioClip;
import java.io.FileNotFoundException;

import controller.Game;
import model.creatures.IA;

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

	public void reproduceBeginning()
	{
		music.stop();
		String url = SoundsBinding.getBeginningSounds(IA.random(2));

		AudioClip  sound = java.applet.Applet.newAudioClip(getClass().getResource(url));
		sound.play();
		sleep(5500);

	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			System.err.println("Problem with Sleep");
			e.printStackTrace(System.err);
		}
	}

	public void reproduceLifeUp()
	{
		String url = SoundsBinding.getGameSounds("lifeUp");

		AudioClip  sound = java.applet.Applet.newAudioClip(getClass().getResource(url));
		sound.play();
		sleep(1000);

	}
	public void changeToPostGame()
	{
		String url = SoundsBinding.getGameSounds("postGame");

		music = java.applet.Applet.newAudioClip(getClass().getResource(url));

	}
	public void reproduceEatGhost(int ghostEated)
	{
		String url = SoundsBinding.getDeathSounds(ghostEated);
		 AudioClip sound = java.applet.Applet.newAudioClip(getClass().getResource(url));
		sound.play();
	    sleep(100);
		

	}
	public void chageToLoad()
	{
		String url = SoundsBinding.getGameSounds("load");
		music = java.applet.Applet.newAudioClip(getClass().getResource(url));
	    
	}
	
	public void changeToNormal()
	{
		String url = SoundsBinding.getNormalMusic(Game.getBoard().getLevel());

		music = java.applet.Applet.newAudioClip(getClass().getResource(url));

	    
	}
	public void changeToSuper() 
	{
		String url = SoundsBinding.getSuperMusic(IA.random(2));

		music= java.applet.Applet.newAudioClip(getClass().getResource(url));

	    
	}
	public void reproduceDeath()
	{

		 AudioClip sound = java.applet.Applet.newAudioClip(getClass().getResource("/sounds/death.wav"));
		sound.play();
		music.stop();
	    sleep(2000);
	}

	public void reproduceLevelUp(){
		String url = SoundsBinding.getGameSounds("levelUp");

		AudioClip  sound = java.applet.Applet.newAudioClip(getClass().getResource(url));
		sound.play();	
		sleep(3000);

	}


}
