package minotauro;

public class Arista implements Comparable<Arista>{
	
	private int nodo1;
	private int nodo2;
	private int costo;
	
	public Arista(int nodo1, int nodo2, int costo) {
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
		this.costo = costo;
	}
	
	public Arista(Arista arista) {
		this.nodo1 = arista.nodo1;
		this.nodo2 = arista.nodo2;
		this.costo = arista.costo;
	}

	public int getNodo1() {
		return nodo1;
	}

	public int getNodo2() {
		return nodo2;
	}

	public int getCosto() {
		return costo;
	}

	@Override
	public int compareTo(Arista arista) {
		return this.costo - arista.costo;
	}
	
	
	
}
