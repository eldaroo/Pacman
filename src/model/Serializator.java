package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Serializator {
	
	static JSONArray Data = new JSONArray();
	static JSONObject jObj = new JSONObject();
	static JSONParser parser = new JSONParser();

	
	public JSONArray recover() throws FileNotFoundException, IOException, ParseException {
		Data.clear();
		jObj.clear();
		try (FileReader file = new FileReader("pacman.Json");){
			Data = (JSONArray) parser.parse(file);//Agarra el archivo y LO GUARDA EN UN Array lleno de objetos JSON

			//Iterator<String> iterator = game.Data.iterator(); // (otra forma de recorrer el JSON Array) 
			

		} catch (ParseException e) {
			System.out.println("error"+e);
		} 
		return Data;
	}
	
	public void toPersist(Creature pacman, Creature ghost1, Creature ghost2, Creature ghost3, Creature ghost4, Creature ghost5) throws IOException {
		//Guarda los Objetos en un JSON Array y los escribe en un archivo
		Data.add(pacman);
		Data.add(ghost1);
		Data.add(ghost2);
		Data.add(ghost3);
		Data.add(ghost4);
		Data.add(ghost5);
		StringWriter out = new StringWriter();
		Data.writeJSONString(out);
		try(FileWriter file = new FileWriter("pacman.Json");){
			file.write(out.toString());
			file.flush();
			JOptionPane.showMessageDialog(null, "la partida ha sido guardada con exito");

		} catch (Exception e){
			System.out.println("exception "+ e);
		}
	}

}
