package sounds;

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
	
	public static void reproduceEatGhost()
	{
		EatGhost sound = new EatGhost();
	    sound.play();
	}
	
	public static void reproduceDeath()
	{
		Death sound = new Death();
	    sound.play();
	}

}
