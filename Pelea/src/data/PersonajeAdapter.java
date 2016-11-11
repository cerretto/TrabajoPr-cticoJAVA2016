package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Personaje;


public class PersonajeAdapter {
	
	public PersonajeAdapter() 
	{
	}
	
	public ArrayList<Personaje> GetAll() throws Exception
	{
	
		ResultSet rs=null;
		PreparedStatement stmt=null;
		ArrayList<Personaje> personajes = new ArrayList<Personaje>();
		try{
			stmt = FactoryConection.getInstancia().getConn().prepareStatement("SELECT * FROM personajes");
			rs = stmt.executeQuery();
			
			while (rs.next()) 
			{
				Personaje per = new Personaje();
				per.setId(rs.getInt(1));
				per.setNombre(rs.getString(2));
				per.setPtsTotales(rs.getInt(3));
				per.setVida(rs.getInt(4));
				per.setEnergia(rs.getInt(5));
				per.setDefensa(rs.getInt(6));
				per.setEvasion(rs.getInt(7));
				personajes.add(per);
			}
		}
		catch(SQLException ex)
		{
			throw ex;
		}
		
		return personajes;
		
	}

	public Personaje getByNombre(Personaje pj, String nombre) throws Exception
	{
		String nom = "";
		
		if(pj == null && nombre != ""){
			nom = nombre;
		} else if (pj != null && nombre ==""){
			nom = pj.getNombre();
		} else{
			throw new Exception();
		}
		
		Personaje per = new Personaje();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try 
		{
			stmt = FactoryConection.getInstancia().getConn().prepareStatement("SELECT * FROM personajes"
					+ " where personajes.nombre = ? ");
			stmt.setString(1, nom);
			rs = stmt.executeQuery();
			
			if (rs.next()) 
			{
				per.setId(rs.getInt(1));
				per.setNombre(rs.getString(2));
				per.setPtsTotales(rs.getInt(3));
				per.setVida(rs.getInt(4));
				per.setEnergia(rs.getInt(5));
				per.setDefensa(rs.getInt(6));
				per.setEvasion(rs.getInt(7));
				
			}
		
		
		} catch (SQLException ex) {
			throw ex;
		}
		finally 
		{
			try 
			{
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			
			} catch (Exception ex) {
				throw ex;
			}
		}
		
		
		return per;
	}
	
	public void Guardar(Personaje per) throws Exception
	{
		switch(per.getEstData()) {
		case New:
			Insert(per);
			break;
		case Modified:
			Edit(per);
			break;
		case Deleted:
			Delete(per);
			break;
		default:
			break;
		}
	}

	private void Insert(Personaje per) throws Exception
	{
	
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"insert into personajes(nombre, puntos_disp,vida, energia, defensa, evasion)"+
					" values(?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			// PreparedStatement.RETURN_GENERATED_KEYS to be able to retrieve id generated on the db
			// by the autoincrement column. Otherwise don't use it
						
			stmt.setString(1, per.getNombre());
			stmt.setInt(2, per.getPtsTotales());
			stmt.setInt(3, per.getVida());
			stmt.setInt(4, per.getEnergia());
			stmt.setInt(5, per.getDefensa());
			stmt.setInt(6, per.getEvasion());
			stmt.execute();
			
			//after executing the insert use the following lines to retrieve the id
			rs=stmt.getGeneratedKeys();
			if(rs!=null && rs.next()){
				per.setId(rs.getInt(1));
			}
			
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
	
	private void Edit(Personaje per) throws Exception//, ErrorConexionException {
	{
	
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"Update personajes Set "
					+ "nombre = ?, "
					+ "puntos_disp = ?, "
					+ "vida = ?, "
					+ "energia = ?, "
					+ "defensa = ?, "
					+ "evasion = ? "
					+ "Where id_personaje = ?");
						
			stmt.setString(1, per.getNombre());
			stmt.setInt(2, per.getPtsTotales());
			stmt.setInt(3, per.getVida());
			stmt.setInt(4, per.getEnergia());
			stmt.setInt(5, per.getDefensa());
			stmt.setInt(6, per.getEvasion());
			stmt.setInt(7, per.getId());
			stmt.execute();
			
			
		} catch (SQLException ex) {
			
			throw ex;
		
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			
			} catch (SQLException ex) {
				throw ex;
			}
		}
	}
	
	private void Delete(Personaje pj) throws Exception
	{
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"Delete From personajes "
					+ "Where id_personaje = ?");
						
			stmt.setInt(1, pj.getId());
			stmt.execute();
			
			
		} catch (SQLException ex) {
			throw ex;
		
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			/*} catch (ErrorConexionException e) {
				throw e; */
			} catch (SQLException ex) {
				throw ex;
			}
		}
		
	}
	
	
}
