package com.example.where2go;
/*
 * Operador
 * 
 * Clase auxiliar que representa a cualquiera de los operadores
 * l�gicos que pueden utilizarse en las expresiones l�gicas
 * utilizadas tanto en las condiciones compuestas como en las
 * conclusiones m�ltiples soportadas por la representaci�n
 * de conocimiento LM-Regla.
 * 
 * Espec�ficamente representa en un primer acercamiento una
 * negaci�n (operador unario) y un operador binario, que a su 
 * vez puede ser de dos naturalezas: conjunci�n o disyunci�n.
 * 
 * Es utilizado en las reglas y es congruente con un enfoque
 * polimorfo, por tal motivo esta sola clase representa diferentes
 * tipos de operadores.
 * 
 * Desarrollador: Luis Alberto Casillas Santill�n
 * Fecha: 26/10/2006
 * Hora: 07:53 a.m.
 * 
 */



public class Operador extends ParteRegla{
	public Operador(){
	}
}
class Negacion extends Operador{
	public Negacion(){			
	}
	@Override 
	public String toString(){
		return "�";
	}
}
class Binario extends Operador{
	boolean conj;
	// Las instancias en creadas con "true" representan una
	// conjunci�n, con "false" representan una disyunci�n.
	public Binario(boolean conj){
		this.conj=conj;
	}
	@Override
	public String toString(){
		return conj?"&":"|";
	}
}