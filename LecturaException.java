package conMejora;
/**
 * 
 */


import java.io.File;

/**
 * @author Miguel Angel Sánchez-Tirado Machado
 *
 */
public class LecturaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3407032055905253807L;
	
	private String path = "";
	private File archivo = null;
	
	/**
	 * 
	 */
	public LecturaException() {
		// TODO Auto-generated constructor stub
	}
	public LecturaException(File f) {
		// TODO Auto-generated constructor stub
		setArchivo(f);
		setPath(f.getAbsolutePath());
	}
	public String toString(){
		
		return " LecturaException: El archivo de entrada no cumple con los requisitos que describe la práctica, \n es decir dos números separados por espacios en blanco o retornos de carro. " ;
		
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public File getArchivo() {
		return archivo;
	}
	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

}
