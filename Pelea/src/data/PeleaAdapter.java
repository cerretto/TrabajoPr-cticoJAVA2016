package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Personaje;


public class PeleaAdapter {

	public void guardar(Personaje personajeGanador, Personaje personajePerdedor) throws Exception {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"insert into partidas(id_personaje_ganador, id_personaje_perdedor)"+
					" values(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						
			stmt.setInt(1, personajeGanador.getId());
			stmt.setInt(2, personajePerdedor.getId());
			stmt.execute();
			
		} catch (SQLException ex) {
			throw ex;
			
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
				
			} catch (Exception ex) {
				throw ex;
			}
		}
		
		
	}
	
	
	
	
	
}
