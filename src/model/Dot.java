package model;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

public class Dot extends Observable implements JSONStreamAware{

	private boolean superDot=false;
	private Square position;


	public void setSuper (boolean sd) {
		superDot=sd;
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

	public Square getPosition() {
		return position;
	}

	public void setPosition(Square position) {
		this.position = position;
	}
	
	@Override
	public void writeJSONString(Writer out) throws IOException {
		LinkedHashMap<Object, Object> obj = new LinkedHashMap<>();
		obj.put("xPosition", String.valueOf(position.getBoardPosition().getX()));
		obj.put("yPosition", String.valueOf(position.getBoardPosition().getY()));
		obj.put("superDot", String.valueOf(superDot));
		JSONValue.writeJSONString(obj, out);
	}

}
