package model.board;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import model.Position;
import model.squares.Square;

public class Dot extends Element implements JSONStreamAware{

	private boolean superDot=false;
	private Square position;


	public void setSuper (boolean sd) {
		superDot=sd;
	}
	public Position getBoardPosition() {
		return position.getBoardPosition();
	}
	public boolean getSuper () {
		return superDot;
	}
	public boolean isSuperDot() {
		return superDot;
	}

	public void setSuperDot(boolean superDot) {
		this.superDot = superDot;
	}

	public void setPosition(Square position) {
		this.position = position;
	}
	
	@Override
	public void writeJSONString(Writer out) throws IOException {
		LinkedHashMap<Object, Object> obj = new LinkedHashMap<>();
		obj.put("xPosition", String.valueOf(getBoardPosition().getX()));
		obj.put("yPosition", String.valueOf(getBoardPosition().getY()));
		obj.put("superDot", String.valueOf(superDot));
		JSONValue.writeJSONString(obj, out);
	}

}
