package model.persistence;

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

import controller.Game;
import model.board.Dot;
import model.board.SuperDot;


public abstract class Serializator {
	
	static JSONArray jCreatures;
	static JSONArray jDots ;
	static ArrayList<Dot> dotsArraySaved;
	static JSONObject jObj ;
	static JSONObject jAux;
	static JSONParser parser = new JSONParser();
	static Dot dot;
	
	
	public static ArrayList<Dot> recover() throws FileNotFoundException, IOException, ParseException,NullPointerException {
		 jCreatures = new JSONArray();
		jObj = new JSONObject();
		jDots = new JSONArray();
		jAux= new JSONObject();
		dotsArraySaved = new ArrayList<Dot>();
	
		//Agarra el archivo y LO GUARDA EN UN objeto lleno de arrays que dentro tienen objetos JSON
		try (FileReader file = new FileReader("pacman.Json");){
			jObj = (JSONObject) parser.parse(file);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Usted no tiene partidas guardadas");

		} 
		try {

			Game.getBoard().setScore((long) jObj.get("score"));
			Game.getBoard().setLifes((long) jObj.get("lifes"));
			Game.getBoard().setLevel((long) jObj.get("level"));
				
		
			jCreatures = (JSONArray) jObj.get("Creatures") ;
			jDots=(JSONArray) jObj.get("Dots");
/*
		for (Object obj : jCreatures) {
			jAux = (JSONObject) obj;

			String name = (String) jAux.get("name");
			int x = Integer.parseInt((String) jObj.get("xPosition")) ;
			int y =Integer.parseInt((String)  jObj.get("yPosition"));
			Direction direction = (Direction) jObj.get("direction");

			if (name.equals( "pacman")) {
				Board.pacman.setDirection(direction);
				Board.pacman.setPosition(position);
			}
		}*/
		
		// Agarra cada objeto JSON y le extrae sus variables	
		for (Object obj : jDots) {
				
			jAux = (JSONObject) obj;
				int x = Integer.parseInt((String) jAux.get("xPosition")) ;
				int y =Integer.parseInt((String)  jAux.get("yPosition"));

				if (Boolean.parseBoolean((String) jAux.get("superDot"))) {
					dot=new SuperDot();
					dot.setPosition(Game.getBoard().getBoard()[(int) x][(int) y]);
					dotsArraySaved.add(dot);

				} else if(!Boolean.parseBoolean((String) jAux.get("superDot"))){
					dot=new Dot();
					dot.setPosition(Game.getBoard().getBoard()[(int) x][(int) y]);
					dotsArraySaved.add(dot);
				}


		}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ e);
			System.exit(0);
		} 
		return dotsArraySaved;

	}
	

	@SuppressWarnings("unchecked")
	public static void toPersist() throws IOException {
		jDots = new JSONArray();
		jObj = new JSONObject();
		 jCreatures = new JSONArray();
		ArrayList<Dot> dots = Game.getBoard().getDots();
		
		//Guarda los Objetos en un JSON Array y los escribe en un archivo
		jCreatures.add(Game.getBoard().pacman);
		//jCreatures.add(Board.getGhostsArray());

		jObj.put("Creatures", jCreatures);
		
			for (Dot dot : dots) {

				jDots.add(dot);
			}
		jObj.put("Dots", jDots);

		jObj.put("score", Game.getBoard().getScore());
		jObj.put("lifes", Game.getBoard().getLifes());
		jObj.put("level", Game.getBoard().getLevel());

		
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
