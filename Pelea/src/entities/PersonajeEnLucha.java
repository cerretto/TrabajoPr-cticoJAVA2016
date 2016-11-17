package entities;

public class PersonajeEnLucha {
	private Personaje p;
	private int vidaActual;
	private int energiaActual;
	
	//constructor q recibe un personaje base y setea los atributos
	public PersonajeEnLucha(Personaje p){
		this.setP(p);
		this.setVidaActual(this.getP().getVida());
		this.setEnergiaActual(this.getP().getEnergia());
	}
	
	//getters y setters
	public Personaje getP() {
		return p;
	}
	public void setP(Personaje p) {
		this.p = p;
	}
	public int getVidaActual() {
		return vidaActual;
	}
	public void setVidaActual(int vidaActual) {
		this.vidaActual = vidaActual;
	}
	public int getEnergiaActual() {
		return energiaActual;
	}
	public void setEnergiaActual(int energiaActual) {
		this.energiaActual = energiaActual;
	}
	
	//para actualizar la vida actual del personaje en combate
	public void restarVida(int cantPuntos)
	{
		this.setVidaActual(this.getVidaActual()-cantPuntos);
		
	}
	
	//para actualizar la energia actual del personaje en combate
	public void restarEnergia(int cantPuntos)
	{
		this.setEnergiaActual(this.getEnergiaActual()-cantPuntos);
		
	}
	
	/*para atacar al personaje enemigo, se le pasa el personaje a atacar y la cant d puntos d energia con la que se ataca
	 		devuelve si el ataque fue exitoso o no. (se invoca metodos del personje base)*/
	public boolean atacar(PersonajeEnLucha perAtacado, int cantPuntos) throws Exception
	{
		if (this.getEnergiaActual() < cantPuntos){
			throw new Exception("El personaje no tiene suficientes puntos para atacar");
		}
		this.restarEnergia(cantPuntos);
		boolean exito = this.getP().atacar(perAtacado.getP(), cantPuntos);
				
		if(exito){
			perAtacado.restarVida(cantPuntos);
		}
		
		return exito;
	}
	
	//para defender 
	public void defender() {
		int defensaOriginal = this.getP().getDefensa();
		int vidaOriginal = this.getP().getVida();
		int vidaActual = this.getVidaActual();
		int energiaOriginal = this.getP().getEnergia();
		int energiaActual = this.getEnergiaActual();
		
		this.setVidaActual( vidaActual + (defensaOriginal * vidaOriginal) / 250 );
		this.setEnergiaActual( energiaActual + (defensaOriginal * energiaOriginal) / 100 );
		
		/*valido q tanto la energia y vida actual modificada no supere los valores originales del personaje, en caso contrario
		 	seteo los valores actuales como los originales (los maximos)*/
		if (this.getVidaActual() > this.getP().getVida()) setVidaActual(this.getP().getVida());
		if (this.getEnergiaActual() > this.getP().getEnergia()) setEnergiaActual(this.getP().getEnergia());
	}
	
	
	
	
}
