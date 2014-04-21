package com.example.where2go;
/*
 * MemoriaTrabajo
 * 
 * Clase esencial de este entorno genérico para sistemas
 * expertos. Responsable de contener los datos concretos
 * del caso de la realidad que actualmente procesa este
 * sistema experto.
 * 
 * En particular, cuando el sistema experto está verificando
 * diferentes hechos de la realidad; almacena en esta 
 * estructura los átomos que se han cotejado por medio de un
 * simple interrogatorio, o bien cuando una regla se dispara
 * y los átomos contenidos en su conclusión se almacenan.
 * 
 * Es capaz de controlar los átomos que se han afirmado y 
 * negado en el proceso, con el fin de lograr un tratamiento
 * preciso de la lógica modelada en la ontología del sistema
 * experto.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
 * Fecha: 28/10/2006
 * Hora: 05:39 p.m.
 * 
 */


import java.util.*;

public class MemoriaTrabajo{
	ArrayList<Atomo> afirmados;
	ArrayList<Atomo> negados;
	public MemoriaTrabajo(){
		afirmados=new ArrayList<Atomo>();
		negados=new ArrayList<Atomo>();
	}
	void guardaAtomo(Atomo aa) throws AtomoDuplicado{
		if (!afirmados.contains(aa)&&!negados.contains(aa)){
			if (aa.estado) afirmados.add(aa);
			else negados.add(aa);
		}else{
			throw new AtomoDuplicado(aa.desc);
		}
	}
	boolean presente(Atomo aa){
		Atomo aTmp=new Atomo(aa);
		aTmp.estado=!aTmp.estado;
		return (afirmados.contains(aa)||negados.contains(aa)||
		        afirmados.contains(aTmp)||negados.contains(aTmp));
	}
	boolean fueAfirmado(Atomo aa){
		return afirmados.contains(aa);
	}
	boolean fueNegado(Atomo aa){
		return negados.contains(aa);
	}
	Atomo recupera(Atomo aa){
		int pa=afirmados.indexOf(aa);
		int pn=negados.indexOf(aa);
		if (pa>-1) return (Atomo)afirmados.get(pa);
		if (pn>-1) return (Atomo)negados.get(pn);
		return null;
	}
	
	@Override
	public String toString(){
		String retorno="\nMemoria de Trabajo\nAfirmados: [ ";
		for(Atomo a : afirmados) retorno+=(a+" ");
		retorno+="]\nNegados: [ ";
		for(Atomo a : negados) retorno+=(a+" ");
		retorno+="]";
		return retorno;
	}
}

