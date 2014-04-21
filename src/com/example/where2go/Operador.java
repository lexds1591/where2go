package com.example.where2go;
/*
 * Operador
 * 
 * Clase auxiliar que representa a cualquiera de los operadores
 * lógicos que pueden utilizarse en las expresiones lógicas
 * utilizadas tanto en las condiciones compuestas como en las
 * conclusiones múltiples soportadas por la representación
 * de conocimiento LM-Regla.
 * 
 * Específicamente representa en un primer acercamiento una
 * negación (operador unario) y un operador binario, que a su 
 * vez puede ser de dos naturalezas: conjunción o disyunción.
 * 
 * Es utilizado en las reglas y es congruente con un enfoque
 * polimorfo, por tal motivo esta sola clase representa diferentes
 * tipos de operadores.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
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
		return "¬";
	}
}
class Binario extends Operador{
	boolean conj;
	// Las instancias en creadas con "true" representan una
	// conjunción, con "false" representan una disyunción.
	public Binario(boolean conj){
		this.conj=conj;
	}
	@Override
	public String toString(){
		return conj?"&":"|";
	}
}