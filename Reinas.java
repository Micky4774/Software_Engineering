/**
 * 
 */
package Backtracking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Miguel Angel Sánchez-Tirado Machado
 *
 */

public class Reinas {

	/**
	 * @param args
	 */
	
	static boolean t=false;
	static boolean h=false;
	static boolean g=false;
	static boolean bFichero=false;
	static int n=0;
	static String ficheroSalida=null;
	static int[] col;
	static Escritura escritura= null;
	
	public static void main(String[] args) {
		
		System.out.println("La ejecucion ha comenzado\r\n");
		int tamanyo = args.length;

		for(int k=0;k<tamanyo;k++){
			if(args[k].equals("-t")){
				t=true;
			}else if(args[k].equals("-h")){
				h=true;
			}else if(args[k].equals("-g")){
				g=true;
			}else if(tamanyo>=2){
				String stringPattern = "[0-9]";
				Pattern pattern = Pattern.compile(stringPattern);
				Matcher matcher = pattern.matcher(args[tamanyo-2]);
				bFichero=matcher.matches();
				
			}
		}
		
		if(h){
			System.out.println("SINTAXIS:");
			System.out.println("reinas [-t] [-g] [-h] n [fichero_salida]");
			System.out.println("-t		Traza");
			System.out.println("-g		Modo gráfico");
			System.out.println("-h		Muestra esta ayuda");
			System.out.println("n 		Tamaño del tablero y número de reinas.");
			System.out.println("fichero_salida		Nombre del fichero de salida");	
		}
		
		if(t && h && g){
			
			n=Integer.parseInt(args[3]);
			
			if(bFichero){
				ficheroSalida=args[4];
			}
		}else if((t && h && !g)||(t && !h && g)||(!t && h && g)){
			
			n=Integer.parseInt(args[2]);
			
			
			if(bFichero){
				ficheroSalida=args[3];
			}
		}else if((!t && !h && g)||(!t && h && !g)||(t && !h && !g)){
			
			if(t||g){
				n=Integer.parseInt(args[1]);
			}
			
			if(bFichero){
				ficheroSalida=args[2];
			}
		}else if(!t && !g && !h){
			
			n=Integer.parseInt(args[0]);
			
			
			if(bFichero){
				ficheroSalida=args[1];
			}
		}
		col=new int[n];
		escritura = new Escritura(ficheroSalida,n,g);
		if(n!=0){
			buscarSoluciones(col,n,0);
			escritura.escribirSoluciones();
		}
		System.out.println("\r\nFinal de la ejecución");
		
	}
	public static void buscarSoluciones(int[] vector, int n, int k){
		
		vector[k]=0;
		while(vector[k]<n){
			vector[k]=vector[k]+1;
			if(t){
				escritura.escribirVector(vector,k);
			}
			if(completable(vector,k)){
				if(k==n-1){
					int [] solucion= vector.clone();
					escritura.guardarSolucion(solucion);
				}else{
					buscarSoluciones(vector, n, k+1);
				}
				
			}
		}
	}
	
	public static boolean completable(int[] vector,int k){
		int a,b;
		int j=1;
		for(j=0;j<k;j++){
			a=(int)Math.abs(vector[j]-vector[k]);
			b=(int)Math.abs(j-k);
			if(vector[j]==vector[k] || a==b){
				return false;
			}
		}
		return true;

	}
		
}


