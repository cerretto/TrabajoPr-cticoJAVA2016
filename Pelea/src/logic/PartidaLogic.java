package logic;

import entities.*;
import data.*;
import java.util.Random;


public class PartidaLogic {
	//variables
	private boolean partidaEnCurso;
	private PersonajeEnLucha jugador1;
	private PersonajeEnLucha jugador2;
	private PersonajeEnLucha enTurno;
	private PersonajeEnLucha enEspera;
	
	//getters y setters
	public PersonajeEnLucha getJugador1() {
		return jugador1;
	}
	public void setJugador1(PersonajeEnLucha p1) {
		this.jugador1 = p1;
	}
	public PersonajeEnLucha getJugador2() {
		return jugador2;
	}
	public void setJugador2(PersonajeEnLucha p2) {
		this.jugador2 = p2;
	}
	public PersonajeEnLucha getEnTurno() {
		return enTurno;
	}
	public void setEnTurno(PersonajeEnLucha enTurno) {
		this.enTurno = enTurno;
	}
	public PersonajeEnLucha getEnEspera() {
		return enEspera;
	}
	public void setEnEspera(PersonajeEnLucha enEspera) {
		this.enEspera = enEspera;
	}
	public boolean isPartidaEnCurso() {
		return partidaEnCurso;
	}
	public void setPartidaEnCurso(boolean partidaEnCurso) {
		this.partidaEnCurso = partidaEnCurso;
	}
	
	
	//constructor
	public PartidaLogic(){
		this.setPartidaEnCurso(false);
	}
	
	//metodo para compenzar la partida
	public void comenzarPartida(Personaje p1, Personaje p2) throws Exception {
		if(p1 == null || p2 == null)
			throw new Exception("Elija ambos personajes!");
		if(p1.getId() <1 || p2.getId() < 1 )
			throw new Exception("Elija ambos personajes!");
		if(p1.getNombre().equals(p2.getNombre()))
			throw new Exception ("Elija personajes diferentes!");
		
		jugador1 = new PersonajeEnLucha(p1);
		jugador2 = new PersonajeEnLucha(p2);
		

		if(new Random().nextDouble()>0.5){
			enTurno = jugador1;
			enEspera = jugador2;
		} else {
			enTurno = jugador2;
			enEspera = jugador1;
		}
		
	}
	
	//metodo para atacar
	public boolean atacar(int cantPuntos) throws Exception {
		boolean exito  = enTurno.atacar(enEspera, cantPuntos);
		
		cambiarTurno();
		
		return exito;
	}
	
	//metodo para defender
	public void defender() throws Exception	{
		enTurno.defender();
		 
		cambiarTurno();
	}
	
	//metodo para cambiar de turno
	private void cambiarTurno() throws Exception {
		if(enEspera.getVidaActual()<=0){
			terminarPartida();
			return;
		}
		
		if (enTurno == jugador1) {
			enTurno = jugador2;
			enEspera = jugador1;
		} else {
			enTurno = jugador1;
			enEspera = jugador2;
		}
		
	}
	
	//metodo para terminar la partida
	public void terminarPartida() throws Exception	{
		//PeleaAdapter pA = 
		new PeleaAdapter().guardar(enTurno.getP(), enEspera.getP());
		//pA.guardar(enTurno.getP(), enEspera.getP());
		
		setPartidaEnCurso(false);
	}
	
	
	
	
	
	
}
