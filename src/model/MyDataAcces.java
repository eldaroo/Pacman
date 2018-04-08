package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MyDataAcces {
	private String usuario = "u861596183_dario";
	private String pwd = "darioegea";
	private static String db ="u861596183_pacma";

private Connection conn= null;

static String url = "jdbc:mysql://auth-db141.hostinger.com.ar:3306/"+db;

public MyDataAcces() throws SQLException, ClassNotFoundException
{}

public Connection getConection() throws ClassNotFoundException
{
	try {
		Class.forName("com.mysql.jdbc.Connection");

	conn = (Connection) DriverManager.getConnection(url, usuario, pwd);
	if (conn!=null) {
		System.out.println("Conexion con la base de datos "+ url + " exitosa");
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
