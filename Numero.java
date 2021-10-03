package conMejora;
/**
 * 
 */


/**
 * @author Miguel Angel Sánchez-Tirado Machado
 *
 */
public class Numero {
	// nos da la longitud del número
	int longitud = 0; 
	
	// nos da el número en un array char
	char[] numero = null; 
	
	// nos dice si es el número cero
	boolean cero = false; 
	
	// nos dice si es positivo o no
	boolean positivo; 
	
	// el número cero en char
	final static char ZERO = '0'; 
	
	//constructor del objeto Numero pasándole como argumento un tipo int
	public Numero(int numero){
		if(numero == 0){
			this.setCero(true);
			char[] array = new char[1];
			array[0] = Numero.ZERO;
			this.setNumero(array);
			this.setLongitud(1);
		}else{
			this.setCero(false);
			this.setLongitud(((int)Math.floor(Math.log10(numero)))+1);
		
			char[] array = new char[(int) this.getLongitud()];
			int resto;
			
			int number=numero;
			for(int k=1;k <= this.getLongitud();k++){
				
				resto=number % 10;
				number= (number-resto)/10;
				array[k-1] = (char)(resto+48); 
				
			}
			char[] aux = new char[(int) this.getLongitud()];
			for(int k=1; k<=this.getLongitud(); k++){
				aux[this.getLongitud()-k]= array[k-1];
			}
			this.setNumero(aux);
		}
	}
	
	//constructor del objeto Numero pasándole como argumento un array de char
	public Numero(char[] array){
		
		this.setLongitud(array.length);
		this.setNumero(array);
		this.setCero(false);
	}
	
	//constructor del objeto Numero pasándole como argumento un array de char y estableciendo si 
	//el número es positivo o negativo
	public Numero(char[] array, boolean positivo){
		this.setLongitud(array.length);
		this.setNumero(array);
		this.setCero(false);
		this.setPositivo(positivo);
	}
	//divide el número correspondiente en dos números con m dígitos.
	public Numero[] divide(int m){
		
		char[] arrayChar= this.getNumero();
		int tamanyo=this.getLongitud();
		int restante= tamanyo-m;
		if(restante<0){
			restante=0;
			m=tamanyo;
		}
		char[] array1 = new char[restante];
		char[] array2 = new char[m];
		for(int k=0;k<restante;k++){
			array1[k]=arrayChar[k];
		}
		int j=0;
		for(int k=restante;k<tamanyo;k++){
			array2[j]=arrayChar[k];
			j++;
		}
		Numero[] numeros= new Numero[2];
		if(m>=tamanyo){
			numeros[0]=new Numero(0);
		}else{
			numeros[0]=new Numero(array1);
		}
		numeros[1]=new Numero(array2);
		return numeros;
	}
	
	//suma dos números
	public Numero mas(Numero sumando) throws TamanyoExeption{
		int lon1 = this.getLongitud();
		int lon2 = sumando.getLongitud();
		char[] array1 = this.getNumero();
		char[] array2 = sumando.getNumero();
		if(lon1<lon2){
			array1=rellenaConCerosIzquierda(array1,lon2);
		}else if(lon2<lon1){
			array2=rellenaConCerosIzquierda(array2, lon1);
		}
		Numero suma = null;
		int s,s1,s2;

		int max= Math.max(lon1, lon2);
		char[] array = new char[max+1];
		int acarreo = 0;
		char caracter;
		String cadena;
		
		for(int k=1; k<=max; k++){
			s1=Character.getNumericValue(array1[max-k]);
			s2=Character.getNumericValue(array2[max-k]);
			s= s1+s2+acarreo;
			cadena =String.valueOf(s);
			if(s>=10){
				acarreo = 1;
				caracter = cadena.charAt(1);
			}else{
				acarreo = 0;
				caracter = cadena.charAt(0);
			}
			array[array.length-k]=caracter;
		}
		if(acarreo == 0){
			array[0]=Numero.ZERO;
		}else if(acarreo == 1){
			array[0]='1';
		}
		suma = new Numero(array);

		return suma;
	}
	
	//resta dos números
	public Numero menos(Numero sustraendo) throws TamanyoExeption{
		
		int lon1 = this.getLongitud();
		int lon2 = sustraendo.getLongitud();
		char[] array1 = this.getNumero();
		char[] array2 = sustraendo.getNumero();
		if(lon1>lon2){
			array2 = rellenaConCerosIzquierda(array2, lon1);
		}else if(lon1<lon2){
			throw new TamanyoExeption(TamanyoExeption.RESTA,lon1, lon2);
		}
		Numero resta = null;
		int r,m,s;
		char[] array = new char[lon1];
		int acarreo = 0;
		char caracter;
		for(int k=1; k<=lon1; k++){
			m=Character.getNumericValue(array1[lon1-k]);
			s=Character.getNumericValue(array2[lon1-k]);
			if((s+acarreo)>m){
				m=m+10;
				r=m-(s+acarreo);
				acarreo = 1;
				caracter = (char)(r+48);
			}else{
				r=m-(s+acarreo);
				acarreo = 0;
				caracter = (char)(r+48);
			}
			array[array.length-k]=caracter;
		}

		resta = new Numero(array);
		return resta;
	}
	
	//rellena con ceros a la izquierda si es necesario alcanzar el tamaño tamanyo
	private char[] rellenaConCerosIzquierda(char[] array, int tamanyo){
		
		char[] aux = new char[tamanyo];
		for(int k=0; k<tamanyo-array.length; k++){
			aux[k]=Numero.ZERO;
		}
		int j=0;
		for(int k=tamanyo-array.length; k<tamanyo; k++){
			aux[k]=array[j];
			j++;
		}
		
		return aux;
		
	}
	
	// este método es equivalente a multiplicar el número por una potencia de 10^(numeroCeros)
	public Numero rellenaConCerosDerecha(int numeroCeros){
		int tamanyo = this.getNumero().length+numeroCeros;
		char[] aux = new char[tamanyo];
		for(int k=0; k<this.getNumero().length;k++){
			aux[k]=this.getNumero()[k];
		}
		for(int k=this.getNumero().length;k<tamanyo;k++){
			aux[k]=Numero.ZERO;
		}
		Numero numero = new Numero(aux);
		return numero;
	}
	
	//quita los ceros no significativos del número, es decir los que están a la izquierda
	public Numero quitarCerosIzquierda(){
		char[] array= this.getNumero();
		
		int tamanyo=this.getLongitud();
		int k=0;
		while(array[k]==Numero.ZERO && k<tamanyo-1){
			k++;
		}
		int size=tamanyo-k;
		char[] aux=new char[size];
		int i=0;
		
		for(int j=k; j<tamanyo;j++){
			aux[i]=array[j];
			i++;
		}
		Numero numero = new Numero(aux);
		return numero;
	}
	
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public char[] getNumero() {
		return numero;
	}
	public void setNumero(char[] numero) {
		this.numero = numero;
	}
	
	public boolean isCero() {
		return cero;
	}

	public void setCero(boolean cero) {
		this.cero = cero;
	}

	public boolean isPositivo() {
		return positivo;
	}

	public void setPositivo(boolean positivo) {
		this.positivo = positivo;
	}
	
}
