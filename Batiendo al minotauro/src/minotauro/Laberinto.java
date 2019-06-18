package minotauro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Laberinto {

	private int cantidadDescansos;
	private int aristasUsadas;
	private int[][] matrizAdyacencia;
	private ArrayList<Arista> aristas = new ArrayList<Arista>();
	private Arista[] aristasVector; 
	private static int padre[];
	
	public Laberinto(Scanner entrada) {
		this.cantidadDescansos = entrada.nextInt();
		this.aristasUsadas = 0;
		this.padre = new int[this.cantidadDescansos+1];
		for(int i=1; i<=this.cantidadDescansos; i++) {
			this.padre[i] = i;
		}
		this.matrizAdyacencia = new int[this.cantidadDescansos][this.cantidadDescansos];
		for(int i=0; i<this.cantidadDescansos; i++) {
			for(int j=0; j<this.cantidadDescansos; j++) {
				this.matrizAdyacencia[i][j] = entrada.nextInt();
			}
		}
	}
	
	public static int find(int x) {
		if(x == padre[x])
			return x;
		else 
			return find(padre[x]);
	}
	
	public static boolean mismoComponente(int x, int y) {
		if(find(x) == find(y))
			return true;
		return false;
	}
	
	public static void union(int x, int y) {
		padre[find(x)] = find(y);
	}
	
	private void deMatrizAVector() {
		for(int i=0; i<this.cantidadDescansos; i++) {
			for(int j=i+1; j<this.cantidadDescansos; j++) {
				this.aristas.add(new Arista(i+1, j+1, this.matrizAdyacencia[i][j]));
			}
		}
		this.aristasVector = new Arista[this.aristas.size()];
		this.aristasVector = this.aristas.toArray(new Arista[this.aristas.size()]);
		this.aristas.clear();
		Arrays.sort(aristasVector);
	}
	
	public void resolver(PrintWriter salida) {
		this.deMatrizAVector();
		int  i = 0, x, y, costo;
		Arista aristaAux;
		while(this.aristasUsadas != this.cantidadDescansos-1 && i < this.aristasVector.length) {
			x = this.aristasVector[i].getNodo1();
			y = this.aristasVector[i].getNodo2();
			if(mismoComponente(x,y) == false) {
				costo = this.aristasVector[i].getCosto();
				this.aristas.add(this.aristasVector[i]);
				this.aristasUsadas++;
				union(x,y);
			}
			i++;
		}
		salida.println(this.aristasUsadas);
		for(i=0; i<this.aristas.size(); i++) {
			aristaAux = new Arista(this.aristas.get(i));
			salida.println(aristaAux.getNodo1() + " " + aristaAux.getNodo2() + " " + aristaAux.getCosto());
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(new FileReader("minotauro.in"));
		Laberinto laberinto = new Laberinto(entrada);
		entrada.close();
		PrintWriter salida = new PrintWriter(new FileWriter("minotauro.out"));
		laberinto.resolver(salida);
		salida.close();
	}

}
