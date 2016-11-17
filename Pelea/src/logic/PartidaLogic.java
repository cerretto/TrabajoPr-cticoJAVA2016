package logic;

import entities.*;
import data.*;
import java.util.Random;


public class PartidaLogic {
	private boolean partidaEnCurso;
	
	private PersonajeEnLucha p1;
	private PersonajeEnLucha p2;
	private PersonajeEnLucha enTurno;
	private PersonajeEnLucha enEspera;
	
	public PersonajeEnLucha getP1() {
		return p1;
	}
	public void setP1(PersonajeEnLucha p1) {
		this.p1 = p1;
	}
	public PersonajeEnLucha getP2() {
		return p2;
	}
	public void setP2(PersonajeEnLucha p2) {
		this.p2 = p2;
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
	
	public PartidaLogic(){
		this.partidaEnCurso = false;
	}
	
	public void comenzarPartida(Personaje p1, Personaje p2) throws Exception {
		
		
	}
	
	
	
	
	
}
