package model;

public class Player {
	int score = 0;
	String name = new String();
	
	public Player(int score, String name) {
		super();
		this.score = score;
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
