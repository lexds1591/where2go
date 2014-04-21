package com.example.where2go;
/**
 * AtomoDuplicado
 * 
 * Es una clase auxiliar que representa la excepción 
 * específica de que un átomo ha sido duplicado al 
 * interior de una regla: en su condición compuesta o 
 * conclusión múltiple, o bien en la memoria de trabajo
 * del propio sistema experto.
 * 
 * Al ser lanzada esta excepción, acciona los mecanismos
 * de respuesta al suceso de duplicación de átomo en los
 * receptáculos diseñados para ello.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
 * Fecha: 28/10/2006
 * Hora: 05:44 p.m.
 * 
 * Convertido a Java
 * Alejandro Duarte Sánchez
 * Fecha 27/11/2013
 */


public class AtomoDuplicado extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AtomoDuplicado(String desc){
		super("Atomo Duplicado: "+desc);
	}
}

