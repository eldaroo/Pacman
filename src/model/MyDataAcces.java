package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class MyDataAcces {
	private String _usuario = "root";
	private String _pwd = "";
	private static String _db ="pacman";
private String nombre;
private int puntaje;
private Connection conn= null;

static String _url = "jdbc:mysql://localhost/"+_db;

public MyDataAcces() throws SQLException, ClassNotFoundException
{}

public Connection getConection() throws ClassNotFoundException
{
	try {
		Class.forName("com.mysql.jdbc.Connection");

	conn = (Connection) DriverManager.getConnection(_url, _usuario, _pwd);
	if (conn!=null) {
		System.out.println("Conexion con la base de datos "+ _url + " exitosa");
	}
	
	}catch ( SQLException e) {
		System.out.println("error");
		e.printStackTrace();
	}
	return conn;
}
public ResultSet getQuery () throws SQLException, ClassNotFoundException
{
	Connection conn = getConection();
	java.sql.PreparedStatement ps=null;
	ResultSet resultado = null;
	
	ps= conn.prepareStatement("Select * From players ORDER BY score DESC");
	resultado = ps.executeQuery();
	return resultado;
	
}

public void setQuery(String name, long score) throws SQLException, ClassNotFoundException
{
	java.sql.PreparedStatement ps = null;
	Connection con = getConection();
	
	ps = con.prepareStatement("Insert into players (name, score) Values (?,?)");
	ps.setString(1, name);
	ps.setLong(2, score);
	int res = ps.executeUpdate();
	
	if (res > 0) {
		System.out.println("ok dude");
	
	}else if (res==0)
		{	System.out.println("no dude");
}
	con.close();
	}

}
