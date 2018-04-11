package controller.states;

import javax.swing.JOptionPane;

import controller.Game;

public class Pause extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		JOptionPane.showMessageDialog(null, "la partida esta en pausa");
		//Game.setState(new Normal());
	}

}
