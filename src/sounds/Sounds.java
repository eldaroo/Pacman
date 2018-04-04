package sounds;

import java.applet.AudioClip;

public class Sounds {

	public static void reproduceBeginning() throws InterruptedException
	{
		Beginning sound = new Beginning();
	    sound.play();
		Thread.sleep(3795);
	}
	
	public static void reproduceEatDot()
	{
		EatDot sound = new EatDot();
	    sound.play();
	}
	public static void reproduceLifeUp()
	{
		LifeUp sound = new LifeUp();
	    sound.play();
	}
	public void reproducePostGame()
	{
		AudioClip sound ;
		sound = java.applet.Applet.newAudioClip(getClass().getResource("/sounds/postGame.wav"));
		sound.play();
		
	}
	public void reproduceEatGhost(int ghostEated) throws InterruptedException
	{
		AudioClip sound ;
			
		switch (ghostEated) {
		case 1:
			sound = java.applet.Applet.newAudioClip(getClass().getResource("/sounds/firstblood.wav"));
			break;
		case 2:
			sound = java.applet.Applet.newAudioClip(getClass().getResource("/sounds/godlike.wav"));
			break;
		case 3:
			sound = java.applet.Applet.newAudioClip(getClass().getResource("/sounds/monsterkill.wav"));
			break;
		case 4:
			sound = java.applet.Applet.newAudioClip(getClass().getResource("/sounds/wickedsick.wav"));

			break;
		case 5:
			sound = java.applet.Applet.newAudioClip(getClass().getResource("/sounds/rampage.wav"));
			break;
		default:
			sound = java.applet.Applet.newAudioClip(getClass().getResource("/sounds/rampage.wav"));
			break;
		}
		//EatGhost sound = new EatGhost();
	    sound.play();
	   // Thread.sleep(500);
		

	}
	
	public static void reproduceDeath()
	{
		Death sound = new Death();
	    sound.play();
	}

}
