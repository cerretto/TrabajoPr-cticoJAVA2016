package logic;

import java.util.ArrayList;

import util.*;
import entities.*;
import data.*;

public class PersonajeLogic {
	
	private PersonajeAdapter PersonajeData;
	
	public PersonajeLogic() {
		PersonajeData = new PersonajeAdapter();
	}
	
	public void guardar(Personaje p) throws Exception
	{
		try {
			String error = "";
			
			int def = p.getDefensa();
			int ene = p.getEnergia();
			int vid = p.getVida();
			int eva = p.getEvasion();
			
			int ptosDisp = p.getPtsTotales();
			
			if (def + ene + vid + eva > ptosDisp) {
				error += "Los atributos elegidos superan a los disponible \n";
			}
			if (eva > 80) {
				error += "La evasi�n no puede superar los 80 puntos \n";
			}
			if (def > 20) {
				error += "La defensa no puede superar los 20 puntos \n";
			}
			
			if (error.length() != 0) {
				//throw new PersonajeInvalidoException(error);
			}
			try
			{
				PersonajeData.Guardar(p);
			}
			//catch (ErrorConexionException e)
			//{
			//	throw e;
			//}
			catch (Exception e)
			{
				throw e;
			}
			
		/*} catch (PersonajeInvalidoException piEx) {
			throw piEx;
		}*/
		} catch (Exception ex) {
			throw ex;
		}
	}

	public ArrayList<Personaje> GetAll() throws Exception
	{
		try
		{
			return PersonajeData.GetAll();
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public Personaje getByNombre(Personaje pj) throws Exception {
		return PersonajeData.getByNombre(pj);
	}
}
