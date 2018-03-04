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

import controller.Game;

public class Serializator {
	
	static JSONArray jCreatures = new JSONArray();
	static JSONArray jDots = new JSONArray();
	static Dot[][] dotsArraySaved;
	static JSONObject jObj = new JSONObject();
	static JSONParser parser = new JSONParser();

	
	public Dot[][] recover(Board board, Pacman pacman, Ghost ghost1, Ghost ghost2, Ghost ghost3, Ghost ghost4, Ghost ghost5) throws FileNotFoundException, IOException, ParseException,NullPointerException {
		jCreatures.clear();
		jObj.clear();
		jDots.clear();
		dotsArraySaved = new Dot[board.dots.length][board.dots.length];
	
		try (FileReader file = new FileReader("pacman.Json");){
			jObj = (JSONObject) parser.parse(file);//Agarra el archivo y LO GUARDA EN UN objeto lleno de arrays que dentro tienen objetos JSON
		} catch (ParseException e) {
			System.out.println("error "+e);
		} 
		
		board.score = (long) jObj.get("score");
		board.lifes = (long) jObj.get("lifes");
		
		jCreatures = (JSONArray) jObj.get("Creatures") ;
		jDots=(JSONArray) jObj.get("Dots");
		System.out.println(jDots.isEmpty());
		for (int i = 0; i < jDots.size(); i++) {

				jObj = (JSONObject)jDots.get(i);
				int x = Integer.parseInt((String) jObj.get("xPosition")) ;
				int y =Integer.parseInt((String)  jObj.get("yPosition"));

				if (Boolean.parseBoolean((String) jObj.get("superDot"))) {
					dotsArraySaved[(int) x][(int) y]= new SuperDot();

				} else if(!Boolean.parseBoolean((String) jObj.get("superDot"))){
					dotsArraySaved[(int) x][(int) y]= new Dot();
				}
				dotsArraySaved[(int) x][(int) y].position = board.board[(int) x][(int) y];

		} // Agarra cada objeto JSON y le extrae sus variables
				
		return dotsArraySaved;

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
				if(dot!=null) {
				jDots.add(dot);}
			}
		}
		jObj.put("Dots", jDots);
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
