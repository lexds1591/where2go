package com.example.where2go;
/*
 * Atomo
 * 
 * La clase �tomo es esencial para este entorno gen�rico 
 * para sistemas expertos. Representa elementos concretos
 * de la realidad que busca ser procesada a trav�s de un
 * experto artificial modelado al interior del presente
 * entorno.
 * 
 * Esta clase incluye diversos atributos y operadores, a 
 * partir de los cuales logra el tratamiento adecuado de la
 * porci�n m�nima de realidad a tratar, un �tomo. Elemento
 * simb�lico de la programaci�n declarativa, �til en la
 * confecci�n de una ontolog�a.
 * 
 * Desarrollador: Luis Alberto Casillas Santill�n
 * Fecha: 26/10/2006
 * Hora: 07:14 a.m.
 * 
 */


public class Atomo extends ParteRegla{
	String desc;
	boolean estado;
	boolean objetivo;
	public Atomo(String desc,boolean estado,boolean objetivo){
		this.desc=desc.toUpperCase();
		this.estado=estado&&true;
		this.objetivo=objetivo&&true;
	}
	public Atomo(Atomo otro){
		desc=new String(otro.desc.toCharArray());
		estado=otro.estado&&true;
		objetivo=otro.objetivo&&true;
	}

	@Override
	public String toString(){
		return (estado?"":"�")+(objetivo?"*":"")+desc;
	}
	@Override
	public int hashCode(){
		return desc.hashCode()^(estado?1:0)^(objetivo?1:0);
	}
	@Override
	public boolean equals(Object obj){
		Atomo aTmp=(Atomo)obj;
		return desc.equals(aTmp.desc)/*&&(estado==aTmp.Estado)*/;			
	}
	boolean verIgualdad(Atomo aTmp){
		return desc.equals(aTmp.desc)&&(estado==aTmp.estado);
	}
	boolean verVerdad(Atomo aTmp){
		if (aTmp==null) return false;
		return estado&&aTmp.estado;
	}
}
