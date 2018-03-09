package sounds;

public class Sounds {

	public void reproduceBeginning() throws InterruptedException
	{
		Beginning sound = new Beginning();
	    sound.play();
		Thread.sleep(3795);
	}
	
	public void reproduceEatDot()
	{
		EatDot sound = new EatDot();
	    sound.play();
	}
	
	public void reproduceEatGhost()
	{
		EatGhost sound = new EatGhost();
	    sound.play();
	}
	
	public void reproduceDeath()
	{
		Death sound = new Death();
	    sound.play();
	}

}
