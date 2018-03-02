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
	
	static JSONArray jCreatures = new JSONArray();
	static JSONArray jDots = new JSONArray();

	static JSONObject jObj = new JSONObject();
	static JSONParser parser = new JSONParser();

	
	public void recover(Board board, Pacman pacman, Ghost ghost1, Ghost ghost2, Ghost ghost3, Ghost ghost4, Ghost ghost5) throws FileNotFoundException, IOException, ParseException,NullPointerException {
		jCreatures.clear();
		jObj.clear();
		jDots.clear();
	
		try (FileReader file = new FileReader("pacman.Json");){
			jObj = (JSONObject) parser.parse(file);//Agarra el archivo y LO GUARDA EN UN objeto lleno de arrays que dentro tienen objetos JSON
			//Iterator<String> iterator = game.Data.iterator(); // (otra forma de recorrer el JSON Array) 
		} catch (ParseException e) {
			System.out.println("error "+e);
		} 
		
		jCreatures = (JSONArray) jObj.get("Creatures") ;
		jDots=(JSONArray) jObj.get("Dots");
		
		/*for (int i = 0; i < jDots.size(); i++) {
			jObj = (JSONObject)jDots.get(i);
			String name = jObj.get("name").toString();
		} // Agarra cada objeto JSON y le extrae sus variables
		*/
		
		board.score = (int) jObj.get("score");
		board.lifes = (int) jObj.get("lifes");
		System.out.println("el score guardado es: "+ board.score + " y las vidas son: "+ board.lifes);
	}
	
	
	
	public void toPersist(Board board,Creature pacman, Creature ghost1, Creature ghost2, Creature ghost3, Creature ghost4, Creature ghost5) throws IOException {
		Dot[][] dots = board.dots;
		
		//Guarda los Objetos en un JSON Array y los escribe en un archivo
		jCreatures.add(pacman);
		jCreatures.add(ghost1);
		jCreatures.add(ghost2);
		jCreatures.add(ghost3);
		jCreatures.add(ghost4);
		jCreatures.add(ghost5);
		jObj.put("Creatures", jCreatures);
		
		for (Dot[] dots2 : dots) {
			for (Dot dot : dots2) {
				jDots.add(dot);
			}
		}
		//jObj.put("Dots", jDots);
		jObj.put("score", board.score);
		jObj.put("lifes", board.lifes);
		
		StringWriter out = new StringWriter();
		jObj.writeJSONString(out);
		try(FileWriter file = new FileWriter("pacman.Json");){
			file.write(out.toString());
			file.flush();
			JOptionPane.showMessageDialog(null, "la partida ha sido guardada con exito");

		} catch (Exception e){
			System.out.println("exception "+ e);
		}
	}

}
