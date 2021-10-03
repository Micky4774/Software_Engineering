package conMejora;
/**
 * 
 */


/**
 * @author Miguel Angel Sánchez-Tirado Machado
 *
 */

public class TamanyoExeption extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4068007514746069569L;
	
	public final static String SUMA = "SUMA";
	public final static String RESTA = "RESTA";
	private int longitud_sumando1 = 0;
	private int longitud_sumando2 = 0;
	private int longitud_minuendo = 0;
	private int longitud_sustraendo = 0;
	private boolean suma = false;
	private boolean resta = false;

	/**
	 * 
	 */
	public TamanyoExeption() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("static-access")
	public TamanyoExeption(String operacion, int lon1, int lon2){
		if(operacion.equals(this.SUMA)){
			setLongitud_sumando1(lon1);
			setLongitud_sumando2(lon2);
			setSuma(true);
		}else if(operacion.equals(this.RESTA)){
			setLongitud_minuendo(lon1);
			setLongitud_sustraendo(lon2);
			setResta(true);
		}
	}
	
	public String toString(){
		
		if(isSuma()){
			return " TamanyoException: Los dos sumandos tienen longitudes diferentes "+getLongitud_sumando1()+" y "+getLongitud_sumando2();
		}else if(isResta()){
			return " TamanyoException: El minuendo tiene longitud "+getLongitud_minuendo()+" y el sustraendo tiene longitud "+getLongitud_sustraendo();
		}
		
		return " TamanyoException: los dos operandos tienen longitudes diferentes";
		
	}

	public int getLongitud_sumando1() {
		return longitud_sumando1;
	}

	public void setLongitud_sumando1(int longitud_sumando1) {
		this.longitud_sumando1 = longitud_sumando1;
	}

	public int getLongitud_sumando2() {
		return longitud_sumando2;
	}

	public void setLongitud_sumando2(int longitud_sumando2) {
		this.longitud_sumando2 = longitud_sumando2;
	}

	public int getLongitud_minuendo() {
		return longitud_minuendo;
	}

	public void setLongitud_minuendo(int longitud_minuendo) {
		this.longitud_minuendo = longitud_minuendo;
	}

	public int getLongitud_sustraendo() {
		return longitud_sustraendo;
	}

	public void setLongitud_sustraendo(int longitud_sustraendo) {
		this.longitud_sustraendo = longitud_sustraendo;
	}

	public boolean isSuma() {
		return suma;
	}

	public void setSuma(boolean suma) {
		this.suma = suma;
	}

	public boolean isResta() {
		return resta;
	}

	public void setResta(boolean resta) {
		this.resta = resta;
	}

	
}
