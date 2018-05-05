package controller.states;

import javax.swing.JOptionPane;

import controller.Game;
import visual.BoardView;
import visual.ViewManager;

public class NormalState extends GameState {


	@Override
	public void reorganize() throws InterruptedException {
		ViewManager.startGameView();
		Game.getBoard().addObserver(ViewManager.getBoardView());
		Game.getBoard().observePacman(BoardView.getPacmanView());
		Game.getBoard().update();
		if(Game.getBoard().getLevel()==1)
		{				
			JOptionPane.showMessageDialog(null, "Move a Paco con las flechitas.\nCon la tecla 'P', hace una pausa.");
			Game.setWin(false);
		}
		Game.getSound().reproduceBeginning();

		Game.setFirstTime (false);
	}


	@Override
	public void run() throws InterruptedException {

			Thread.sleep(Game.getRetard());
			Game.upTime();
			Game.runCreatures();
			Game.getBoard().update();

	}
	
	@Override
	public String toString() {
		return "Normal";
	}

}
