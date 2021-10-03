/**
 * 
 */
package Backtracking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Miguel Angel Sánchez-Tirado Machado
 *
 */

public class Escritura {
	
	private int dimensionesTablero;
	private ArrayList<int []> soluciones = new ArrayList<int[]>();
	private String ficheroSalida;
	private File fichero;
	private boolean modoGrafico = false;
	private boolean salidaEstandar = true;
	private Tablero tablero;
	private final static String RETORNO_DE_CARRO = "\r\n";
	
	public Escritura(String ficheroSalida, int n, boolean modoGrafico){
		setDimensionesTablero(n);
		setFicheroSalida(ficheroSalida);
		if(ficheroSalida!=null){
			File fichero = new File(ficheroSalida);
			setFichero(fichero);
			setSalidaEstandar(false);
		}else{
			setSalidaEstandar(true);
		}
		setModoGrafico(modoGrafico);
		Tablero tablero = new Tablero(n);
		setTablero(tablero);
	
	}
	
	public void escribirVector(int[] vector, int k){
		if(getDimensionesTablero()<28){
			System.out.print("[");
			for(int j=0;j<k;j++){
				System.out.print(getTablero().getLetras()[j]+vector[j]+",");
			}
			System.out.print(getTablero().getLetras()[k]+vector[k]+"]"+RETORNO_DE_CARRO);
		}else{
			System.out.print("[");
			for(int j=0;j<k;j++){
				System.out.print("("+(j+1)+","+vector[j]+");");
			}
			System.out.print("("+k+","+vector[k]+")]"+RETORNO_DE_CARRO);
		}
		
	}
	
	public void guardarSolucion(int[] solucion){
		ArrayList<int[]> soluciones = getSoluciones();
		soluciones.add(solucion);
		setSoluciones(soluciones);
	}
	
	public void escribirSoluciones(){
		
		ArrayList<int[]> soluciones = getSoluciones();
		String salida = "";
		if(!isModoGrafico() || (isModoGrafico() && isSalidaEstandar() && getDimensionesTablero()>19)){
			Iterator<int[]> oIterator=soluciones.iterator();
			int i=1;
				
			while(oIterator.hasNext()){
				int[] solucion = oIterator.next();
				salida=salida+i+": ";
				for(int j=0;j<getDimensionesTablero();j++){
					salida= salida+getTablero().getLetras()[j]+solucion[j]+" ";
				}
				salida= salida+RETORNO_DE_CARRO;
				i++;
			}
		
		}else{
			int i=1;
			Tablero tablero = getTablero();
			Iterator<int[]> oIterator=soluciones.iterator();
			while(oIterator.hasNext()){
				int[] solucion = oIterator.next();
				salida = tablero.colocaCabecera(salida,i);
				salida = tablero.colocaSeparadorFila(salida);
				for(int k=getDimensionesTablero(); k>0;k--){
					salida = tablero.colocaFila(salida,solucion,k);
					salida = tablero.colocaSeparadorFila(salida);
				}
				salida = tablero.colocaIdentificadoresColumnas(salida);
				i++;
			}
		}
		if(isSalidaEstandar()){
			System.out.println(salida);
		}else{
			File fichero = getFichero();
			FileWriter escritor;
			try {
				escritor = new FileWriter(fichero);
				escritor.write(salida);
				escritor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public int getDimensionesTablero() {
		return dimensionesTablero;
	}

	public void setDimensionesTablero(int dimensionesTablero) {
		this.dimensionesTablero = dimensionesTablero;
	}

	public ArrayList<int []> getSoluciones() {
		return soluciones;
	}
	public void setSoluciones(ArrayList<int []> soluciones) {
		this.soluciones = soluciones;
	}
	public String getFicheroSalida() {
		return ficheroSalida;
	}

	public void setFicheroSalida(String ficheroSalida) {
		this.ficheroSalida = ficheroSalida;
	}

	public File getFichero() {
		return fichero;
	}

	public void setFichero(File fichero) {
		this.fichero = fichero;
	}

	public boolean isModoGrafico() {
		return modoGrafico;
	}

	public void setModoGrafico(boolean modoGrafico) {
		this.modoGrafico = modoGrafico;
	}

	public boolean isSalidaEstandar() {
		return salidaEstandar;
	}

	public void setSalidaEstandar(boolean salidaEstandar) {
		this.salidaEstandar = salidaEstandar;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
}
