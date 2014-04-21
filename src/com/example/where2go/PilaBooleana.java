package com.example.where2go;
/*
 * PilaBooleana
 * 
 * Clase auxiliar, responsable de administrar una pila
 * con una representación de datos específica para el 
 * enfoque de este entorno genérico para sistemas expertos.
 * 
 * En particular, es utilizada para realizar la evaluación
 * lógica de las condiciones compuestas, contenidas en las
 * representaciones LM-Regla; que se encuentran expresadas
 * en notación postfija libre de complejidad en paréntesis
 * y presedencias, y directamente procesable por la maquinaria
 * computacional.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
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