package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Serializator {
	
	static JSONArray jCreatures = new JSONArray();
	static JSONArray jDots = new JSONArray();
	static ArrayList<Dot> dotsArraySaved;
	static JSONObject jObj = new JSONObject();
	static JSONParser parser = new JSONParser();
	static Dot dot;
	
	
	public ArrayList<Dot> recover(Board board, Pacman pacman) throws FileNotFoundException, IOException, ParseException,NullPointerException {
		jCreatures.clear();
		jObj.clear();
		jDots.clear();
		dotsArraySaved = new ArrayList<Dot>();
	
		//Agarra el archivo y LO GUARDA EN UN objeto lleno de arrays que dentro tienen objetos JSON
		try (FileReader file = new FileReader("pacman.Json");){
			jObj = (JSONObject) parser.parse(file);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Usted no tiene partidas guardadas");
		} 
		
		board.setScore((long) jObj.get("score"));
		board.setLifes((long) jObj.get("lifes"));
		
		jCreatures = (JSONArray) jObj.get("Creatures") ;
		jDots=(JSONArray) jObj.get("Dots");
		// Agarra cada objeto JSON y le extrae sus variables	
		for (Object obj : jDots) {
				
			jObj = (JSONObject) obj;
				int x = Integer.parseInt((String) jObj.get("xPosition")) ;
				int y =Integer.parseInt((String)  jObj.get("yPosition"));

				if (Boolean.parseBoolean((String) jObj.get("superDot"))) {
					dot=new SuperDot();
					dot.setPosition(board.getBoard()[(int) x][(int) y]);
					dotsArraySaved.add(dot);

				} else if(!Boolean.parseBoolean((String) jObj.get("superDot"))){
					dot=new Dot();
					dot.setPosition(board.getBoard()[(int) x][(int) y]);
					dotsArraySaved.add(dot);
				}


		} 
		return dotsArraySaved;

	}
	

	@SuppressWarnings("unchecked")
	public void toPersist(Board board,Creature pacman) throws IOException {

		ArrayList<Dot> dots = board.getDots();
		
		//Guarda los Objetos en un JSON Array y los escribe en un archivo
		jCreatures.add(pacman);

		jObj.put("Creatures", jCreatures);
		
			for (Dot dot : dots) {

				jDots.add(dot);
			}

		jObj.put("Dots", jDots);
		jObj.put("score", board.getScore());
		jObj.put("lifes", board.getLifes());
		
		
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
