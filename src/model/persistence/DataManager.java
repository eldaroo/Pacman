package model.persistence;

import java.sql.ResultSet;

import controller.Game;
import visual.ScoreView;

public abstract class DataManager {

	private static MyDataAcces connection;
	private static ResultSet result;

	// SALVAMOS EL SCORE
	public static void saveScore(String name) {
		try {
			connection = new MyDataAcces();
			connection.setQuery(name, Game.getBoard().getScore());
		} catch (Exception e) {
		}
	}

	// CARGAMOS EL SCORE Y LO VOLCAMOS AL JTEXTAREA DEL SCOREVIEW
	public static void getScore(ScoreView scoreView) {

		int aux = 0;
		try {
			connection = new MyDataAcces();
			result = connection.getQuery();
			while (result.next()) {
				aux++;
				scoreView.getScoreTextArea()
						.append(aux + ". " + result.getString("name") + " " + result.getInt("score") + "\n");
			}

		} catch (Exception e) {
		}

		//gameView.repaint();
	}

}
