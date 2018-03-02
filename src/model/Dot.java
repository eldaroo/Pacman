package model;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONValue;

public class Dot extends Observable {

	public boolean superDot = false;
	Square position;

	public Position getBoardPosition() {
		return position.getBoardPosition();
	}

	public void writeJSONString(Writer out) throws IOException {
		
		LinkedHashMap<Object, Object> obj = new LinkedHashMap<>();
		obj.put("xPosition", String.valueOf(position.getBoardPosition().getX()));
		obj.put("yPosition", String.valueOf(position.getBoardPosition().getY()));
		obj.put("superDot", String.valueOf(superDot));
		JSONValue.writeJSONString(obj, out);
	}
}
