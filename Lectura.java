package conMejora;
/**
 * 
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Miguel Angel Sánchez-Tirado Machado
 *
 */

//clase que se ocupa de la lectura de datos, es decir, los números
public class Lectura{
	
	private final static int ESPACIO_EN_BLANCO = 32;
	private final static int FIN_DE_LINEA = 10;
	private final static int TABULACION = 9;
	private final static int RETORNO_DE_CARRO = 13;
	private final static int FIN_DE_ARCHIVO = -1;
	
	private File f = null;
	
	public Lectura(String nombreArchivo){
		this.f = new File(nombreArchivo);
	}
	
	public Numero[] devuelveNumeros() throws IOException, LecturaException, FileNotFoundException{
		boolean positivo_numero1=true;
		boolean positivo_numero2=true;
		
		Numero[] numeros = new Numero[2];
		FileReader lector = new FileReader(this.f);
		int c;
		char caracter;
		String cadena = "";
		char[] arrayChar=null;
		while ((c = lector.read()) == Lectura.ESPACIO_EN_BLANCO || c==Lectura.TABULACION || c==Lectura.RETORNO_DE_CARRO){
		}
		if(c==Lectura.FIN_DE_ARCHIVO){
			lector.close();
			throw new LecturaException();
		}else{
			caracter=(char)c;
			cadena = cadena+String.valueOf(caracter);
			while ((c = lector.read()) != Lectura.ESPACIO_EN_BLANCO && c!=Lectura.FIN_DE_LINEA 
					&& c!=Lectura.TABULACION && c!=Lectura.RETORNO_DE_CARRO && c!=Lectura.FIN_DE_ARCHIVO){
				caracter=(char)c;
				cadena = cadena+String.valueOf(caracter);
			}
			String stringPattern = "^(-?)\\d+$";
			Pattern pattern = Pattern.compile(stringPattern);
			Matcher matcher = pattern.matcher(cadena);
			boolean formatoCorrecto = matcher.matches();
			if(cadena.startsWith("-")){
				positivo_numero1=false;
				cadena = cadena.substring(1);
			}
			if(c==Lectura.FIN_DE_ARCHIVO || !formatoCorrecto){
				lector.close();
				throw new LecturaException();
			}else{
				arrayChar=new char[cadena.length()];
				for(int k=0;k<cadena.length();k++){
					arrayChar[k]=cadena.charAt(k);
				}
				numeros[0]=new Numero(arrayChar, positivo_numero1);
				while ((c = lector.read()) == Lectura.ESPACIO_EN_BLANCO || c==Lectura.FIN_DE_LINEA 
						|| c==Lectura.TABULACION || c==Lectura.RETORNO_DE_CARRO){
				}
				cadena="";
				caracter=(char)c;
				cadena = cadena+String.valueOf(caracter);
				while ((c = lector.read()) != Lectura.ESPACIO_EN_BLANCO && c!=Lectura.FIN_DE_LINEA 
						&& c!=Lectura.TABULACION && c!=Lectura.RETORNO_DE_CARRO && c!=Lectura.FIN_DE_ARCHIVO){
					caracter=(char)c;
					cadena = cadena+String.valueOf(caracter);
				}
				matcher = pattern.matcher(cadena);
				formatoCorrecto = matcher.matches();
				if(cadena.startsWith("-")){
					positivo_numero2=false;
					cadena=cadena.substring(1);
				}
			
				while ((c = lector.read()) == Lectura.ESPACIO_EN_BLANCO || c==Lectura.TABULACION || c==Lectura.RETORNO_DE_CARRO){
				}
				
				if(!formatoCorrecto || c!=Lectura.FIN_DE_ARCHIVO){
					lector.close();
					throw new LecturaException();
				}else{
					lector.close();
					arrayChar=new char[cadena.length()];
					for(int k=0;k<cadena.length();k++){
						arrayChar[k]=cadena.charAt(k);
					}
					numeros[1]=new Numero(arrayChar, positivo_numero2);
				}
			}
		}
		return numeros;
	}
}
