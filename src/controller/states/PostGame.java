package controller.states;

import javax.swing.JOptionPane;

import controller.Game;
import model.Board;

public class PostGame extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		
		if (Game.isFirstTime()) {
			JOptionPane.showMessageDialog(null, "la partida termino. Puntos: " + Board.getScore());
			//GameView.remove(layers);
			//postGameView = new PostGameView(gameView, postGameView, scoreView);
			//GameView.setContentPane(PostGameView);
			Game.setFirstTime(false);
			Game.getSound().reproducePostGame();

		}

	}

}
