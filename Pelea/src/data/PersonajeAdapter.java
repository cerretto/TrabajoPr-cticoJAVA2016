package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Personaje;
//import util.ErrorConexionException;

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
		catch(Exception e)
		{
			throw e;
		}
		
		return personajes;
		
	}

	public Personaje getByNombre(Personaje pj) throws Exception
	{
		Personaje per = new Personaje();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try 
		{
			stmt = FactoryConection.getInstancia().getConn().prepareStatement("SELECT * FROM personajes"
					+ " where personajes.nombre = ?");
			stmt.setString(1, pj.getNombre());
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
		
		
		} catch (Exception e) {
			throw new Exception( e);
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			/*} catch (ErrorConexionException e) {
				throw e;*/
			} catch (SQLException e) {
				throw new Exception("Error al cerrar conexion",e);
			}
		}
		
		
		return per;
	}
	
	public void Guardar(Personaje per) throws Exception//, ErrorConexionException
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

	private void Insert(Personaje per) throws Exception//, ErrorConexionException {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Error al crear personaje", e);
		/*} catch (ErrorConexionException e) {
			throw e; */
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			/*} catch (ErrorConexionException e) {
				throw e;*/
			} catch (SQLException e) {
				throw new Exception("Error al cerrar conexion",e);
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
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Error al editar personaje", e);
		/*} catch (ErrorConexionException e) {
			throw e;*/
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			/*} catch (ErrorConexionException e) {
				throw e; */
			} catch (SQLException e) {
				throw new Exception("Error al cerrar conexion",e);
			}
		}
	}
	
	private void Delete(Personaje pj) throws Exception//, ErrorConexionException {
	{
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"Delete From personajes "
					+ "Where id_personaje = ?");
						
			stmt.setInt(1, pj.getId());
			stmt.execute();
			
			
		} catch (SQLException e) {
			throw new Exception("Error al eliminar personaje", e);
		/*} catch (ErrorConexionException e) {
			throw e;*/
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			/*} catch (ErrorConexionException e) {
				throw e; */
			} catch (SQLException e) {
				throw new Exception("Error al cerrar conexion",e);
			}
		}
		
	}
	
	
}
