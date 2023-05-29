package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

public class Giocatore {
	static final private int CFU_INIZIALI = new Configuratore().getCfuIniziali();
	private Borsa inventario;
	private int cfu;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.inventario = new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}

	public Borsa getInventario() {
		return inventario;
	}

	public void setInventario(Borsa inventario) {
		this.inventario = inventario;
	}	
	
}
