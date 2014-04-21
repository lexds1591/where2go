package com.example.where2go;
/*
 * PilaBooleana
 * 
 * Clase auxiliar, responsable de administrar una pila
 * con una representaci�n de datos espec�fica para el 
 * enfoque de este entorno gen�rico para sistemas expertos.
 * 
 * En particular, es utilizada para realizar la evaluaci�n
 * l�gica de las condiciones compuestas, contenidas en las
 * representaciones LM-Regla; que se encuentran expresadas
 * en notaci�n postfija libre de complejidad en par�ntesis
 * y presedencias, y directamente procesable por la maquinaria
 * computacional.
 * 
 * Desarrollador: Luis Alberto Casillas Santill�n
 * Fecha: 28/10/2006
 * Hora: 06:04 p.m.
 * 
 */



import java.util.ArrayList;

public class PilaBooleana{
	ArrayList<Boolean> datos;
	public PilaBooleana(){
		datos=new ArrayList<Boolean>();
	}
	void anula(){
		datos.clear();
	}
	boolean vacia(){
		return datos.size()==0;
	}
	boolean top(){
		if (!vacia()){
			return (boolean)datos.get(datos.size()-1);
		}
		return false;
	}
	boolean pop(){
		boolean tmp;
		if (!vacia()){
			tmp=(boolean)datos.get(datos.size()-1);
			datos.remove(datos.size()-1);
			return tmp;
		}
		return false;
	}
	void push(boolean valor){
		datos.add(valor);
	}
}