package conMejora;
/**
 * 
 */


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Miguel Angel Sánchez-Tirado Machado.
 *
 */

//clase que se ocupa de escribir el resultado del producto
public class Escritura{
	
	
	private File file = null;
	private Numero producto= null;
	
	public Escritura(Numero producto, String nombreArchivo){
		this.file = new File(nombreArchivo);
		setProducto(producto);
	}
	
	public Escritura(Numero producto){
		setProducto(producto);
	}
	
	
	public Numero devuelveProducto() throws IOException{
		char[] arrayChar=getProducto().getNumero();
		String cadena = new String(arrayChar);
		
		if(!(getProducto().isPositivo())){
			cadena = "-"+cadena;
		}
		if(this.file != null){
			FileWriter escritor = new FileWriter(this.file);
			escritor.write(cadena);
			escritor.close();
		}else{
			System.out.println("El resultado de la multiplicacion es:");
			System.out.println(cadena);
		}
		
		return null;
	}
	
	public Numero getProducto() {
		return producto;
	}
	public void setProducto(Numero producto) {
		this.producto = producto;
	}

}
