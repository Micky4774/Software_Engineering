/**
 * 
 */
package Backtracking;

/**
 * @author Miguel Angel Sánchez-Tirado Machado
 *
 */
public class Tablero {
	
	private int dimension;
	private final static String[] letras = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z"};
	private final static String ESPACIO_SIMPLE = " ";
	private final static String ESPACIO = "   ";
	private final static String CARACTER_SEPARADOR_FILA = "-";
	private final static String CARACTER_SEPARADOR_COLUMNA = "|";
	private final static String CASILLA_NEGRA = " * |";
	private final static String CASILLA_BLANCA= "   |";
	private final static String REINA = " R |";
	
	private final static String RETORNO_DE_CARRO = "\r\n";
	public Tablero(int dimension){
		setDimension(dimension);
	}
	
	public String colocaCabecera(String salida,int numero){
		salida = salida+RETORNO_DE_CARRO+RETORNO_DE_CARRO+"Solución número "+numero+":"+RETORNO_DE_CARRO+RETORNO_DE_CARRO;
		return salida;
	}
	
	public String colocaSeparadorFila(String salida){
		if(getDimension()>9){
			salida=salida+ESPACIO_SIMPLE+ESPACIO_SIMPLE;
		}else{
			salida=salida+ESPACIO_SIMPLE;
		}
		
		for(int k=0;k<4*getDimension();k++){
			salida = salida+CARACTER_SEPARADOR_FILA;
		}
		salida = salida+CARACTER_SEPARADOR_FILA+RETORNO_DE_CARRO;
		return salida;
	}
	
	public String colocaFila(String salida, int[] solucion, int k){
		
		if(getDimension()>9 && k<10){
			salida = salida+k+ESPACIO_SIMPLE+CARACTER_SEPARADOR_COLUMNA;
		}else{
			salida = salida+k+CARACTER_SEPARADOR_COLUMNA;
		}
		
		for(int j=0; j<getDimension(); j++){
			if((j+1)==solucion[k-1]){
				salida = salida+REINA;
			}else if((j+1+k)%2==0){
				salida=  salida+CASILLA_BLANCA;
			}else{
				salida = salida+CASILLA_NEGRA;
			}
		}
		salida = salida+RETORNO_DE_CARRO;
		return salida;
	}
	
	public String colocaIdentificadoresColumnas(String salida){
		if (getDimension()<=27){
			for(int j=0; j<getDimension(); j++){
				salida = salida+ESPACIO+getLetras()[j];
			}
		}else{
			for(int j=0; j<getDimension(); j++){
				salida = salida+ESPACIO+(j+1);
			}
		}
		return salida;
		
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public String[] getLetras() {
		return letras;
	}

}
