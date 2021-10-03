package conMejora;
/**
 * 
 */


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Miguel Angel Sánchez-Tirado Machado
 *
 */
public class Multiplica{

	/**
	 * @param args
	 */
	
	static boolean t=false;
	static boolean h=false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("La ejecucion ha comenzado\n");
		int tamanyo = args.length;

		Lectura leer=null;
		for(int k=0;k<args.length;k++){
			if(args[k].equals("-t")){
				t=true;
			}else if(args[k].equals("-h")){
				h=true;
			}
		}
		if(h){
			System.out.println("SINTAXIS:");
			System.out.println("multiplica [-t][-h] [fichero_entrada] [fichero_salida]");
			System.out.println("-t		Traza");
			System.out.println("-h		Muestra esta ayuda");
			System.out.println("fichero_salida		Nombre del fichero de salida");	
		}
		boolean read=false;
		if(t && h && (tamanyo==3 || tamanyo==4)){
			leer = new Lectura(args[2]);
			read = true;
		}else if(t && h && tamanyo==2){
			System.out.println("Debe introducir la ruta del archivo de entrada");
			read = false;
		}else if(((t && !h)||(!t && h)) && (tamanyo==2||tamanyo==3)){
			leer = new Lectura(args[1]);
			read = true;
		}else if(((t && !h)||(!t && h)) && tamanyo==1){
			System.out.println("Debe introducir la ruta del archivo de entrada");
			read = false;
		}else if(!t && !h && (tamanyo==1 || tamanyo==2)){
			leer = new Lectura(args[0]);
			read=true;
		}else if(!t && !h && tamanyo==0){
			read=false;
			System.out.println("Debe introducir al menos la ruta del archivo de entrada");
		}
		if(read){
			final Numero[] numeros;
			try{

				numeros = leer.devuelveNumeros();
				final Numero numero1 = numeros[0];
				final Numero numero2 = numeros[1];
				final Numero resultado = producto(numero1, numero2);
				boolean positivo_producto = (numero1.isPositivo() && numero2.isPositivo()) || (!numero1.isPositivo() && !numero2.isPositivo());
				resultado.setPositivo(positivo_producto);
				Escritura escribir = null;
				if(t && h && tamanyo==4){
					escribir = new Escritura(resultado,args[3]);
				}else if(t && h && tamanyo==3){
					escribir = new Escritura(resultado);
				}else if(((t && !h)||(!t && h)) && tamanyo==3){
					escribir = new Escritura(resultado,args[2]);
				}else if(((t && !h)||(!t && h)) && tamanyo==2){
					escribir = new Escritura(resultado);
				}else if(!t && !h && tamanyo==2){
					escribir = new Escritura(resultado,args[1]);
				}else{
					escribir= new Escritura(resultado);
				}

				if(escribir !=null){
					escribir.devuelveProducto();
				}
				System.out.println("La ejecucion ha finalizado");
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			catch(LecturaException e){
				System.out.println("Capturada "+e);
				e.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}

	}
	
	// método que implementa el método recursivo que efectua el producto de los números
	public static Numero producto(Numero numero1, Numero numero2){
		
		//		maximo_tamanyo indica el máximo tamaño de un número
		//		con el que ya se puede multiplicar directamente sin la técnica del divide y vencerás

		final int maximo_tamanyo = 4;
		numero1=numero1.quitarCerosIzquierda();
		numero2=numero2.quitarCerosIzquierda();
		
		int n = Math.max(numero1.getLongitud(), numero2.getLongitud());
		int m;
		Numero resultado = null;
		if (numero1.isCero()|| numero2.isCero()){
			resultado = new Numero(0);
			return resultado;
		}else if(n <= maximo_tamanyo){
			final int a = Integer.parseInt(new String(numero1.getNumero()));
			final int b = Integer.parseInt(new String(numero2.getNumero()));
			int product = a*b;
			resultado = new Numero(product);
			return resultado;
		}else{
			m = (int) Math.floor((double)n/(double)2);
			final Numero[] numeros1 = numero1.divide(m);
			final Numero[] numeros2 = numero2.divide(m);
			final Numero x = numeros1[0];
			final Numero y = numeros1[1].quitarCerosIzquierda();
			final Numero w = numeros2[0];
			final Numero z = numeros2[1].quitarCerosIzquierda();
			try{
				Numero xw = producto(x,w);
				Numero yz = producto(y,z);
				Numero producto_de_sumas = producto(x.mas(y),w.mas(z));
				Numero primera_resta=producto_de_sumas.menos(xw);
				Numero segunda_resta=primera_resta.menos(yz);
				
				//equivale a multiplicar por 10^m
				Numero primer_desplazamiento = segunda_resta.rellenaConCerosDerecha(m);
				
				//equivale a multiplicar por 10^2m
				Numero segundo_desplazamiento = xw.rellenaConCerosDerecha(2*m);
				
				Numero total = (segundo_desplazamiento.mas(primer_desplazamiento)).mas(yz);
				resultado = total.quitarCerosIzquierda();
				if(t){
					System.out.println("A(l) = "+new String(x.getNumero()));
					System.out.println("A(r) = "+new String(y.getNumero()));
					System.out.println("B(l) = "+new String(w.getNumero()));
					System.out.println("B(r) = "+new String(z.getNumero()));
					System.out.println("El producto para estos numeros es: \n"+ new String(resultado.getNumero()));
					System.out.println("\n----------------------------------------------------------------------------------------------------------\n");
				}
			}catch(TamanyoExeption e){
				e.printStackTrace();
				System.out.println("Capturada "+e);
			}
			return resultado;
		}
	}

}
