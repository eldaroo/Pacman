package model.board;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import controller.Game;
import controller.states.GameState;
import model.Position;
import model.creatures.Pacman;
import model.squares.Square;

public class Dot extends Observable  implements JSONStreamAware{

	private Square position;

	public Position getBoardPosition() {
		return position.getBoardPosition();
	}

	public void setPosition(Square position) {
		this.position = position;
	}
	
	public void eatenInState(GameState gameState) {
		gameState.dotEaten();
	}
	
	public void eaten(Pacman pacman){
		Game.getBoard().upScore(10,0);
	}
	
	@Override
	public void writeJSONString(Writer out) throws IOException {
		LinkedHashMap<Object, Object> obj = new LinkedHashMap<>();
		obj.put("xPosition", String.valueOf(getBoardPosition().getX()));
		obj.put("yPosition", String.valueOf(getBoardPosition().getY()));
		obj.put("superDot", String.valueOf(this.getClass().getName()));
		JSONValue.writeJSONString(obj, out);
	}

}
