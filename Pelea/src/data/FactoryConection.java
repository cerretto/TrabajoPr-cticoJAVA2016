package data;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class FactoryConection {
	private String dbDriver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user ="root";
	private String pass="algy";
	private String db="lucha";
	private String dbType="mysql";
	
	
	private Connection conn;
	private int cantConn=0;
	
	private FactoryConection()throws Exception { 
		try {
			Class.forName(dbDriver);
			/*
			Properties propiedades = new Properties();
		    InputStream entrada = null;
		    
		    entrada = new FileInputStream("config.properties");
		    propiedades.load(entrada);
			
		    host = propiedades.getProperty("host");
		    user = propiedades.getProperty("user");
		    pass = propiedades.getProperty("pass"); 
		    */
			
	
		} catch (Exception ex) {
			throw ex;
			//throw new Exception("error en el driver", ex);
		}
	}
	
	private static FactoryConection instancia;
	
	public static FactoryConection getInstancia() throws Exception{ 
		if (instancia==null){
			instancia = new FactoryConection();
		}
		return instancia;
	}
	
	public Connection getConn() throws Exception { 
		try {
			if(conn==null || conn.isClosed()){
				conn = DriverManager.getConnection(
						"jdbc:"+dbType+"://"+host+":"+port+"/"+
						db+"?user="+user+"&password="+pass+"&useSSL=false");
				cantConn++;
			}
		} catch (Exception ex) {
			
			throw ex;
		}
		return conn;
	}
	
	public void releaseConn() throws Exception{ 
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (Exception ex) {
		
			throw ex;
		}
		
	}
}
