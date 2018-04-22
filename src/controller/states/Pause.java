package controller.states;

import javax.swing.JOptionPane;

import controller.Game;
import sounds.Sounds;

public class Pause extends GameState {

	@Override
	public void reorganize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		JOptionPane.showMessageDialog(null, "la partida esta en pausa");
		Game.setState(new Normal());
	}

}
