package model;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

public class Dot extends Observable implements JSONStreamAware{

	boolean superDot=false;
	Square position;

	public Position getBoardPosition() {
		return position.getBoardPosition();
	}
	
	public void setSuper (boolean sd) {
		superDot=sd;
	}
	
	public boolean getSuper () {
		return superDot;
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
