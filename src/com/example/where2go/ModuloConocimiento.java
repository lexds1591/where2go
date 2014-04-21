package com.example.where2go;
/**
 * ModuloConocimiento
 * 
 * Clase esencial de este entorno genérico para sistemas
 * expertos. Es la responsable de contener y gestionar
 * todo el conocimiento que administra el sistema experto.
 * 
 * Específicamente contiene la ontología que el experto
 * artificial debe procesar, por medio de una base de
 * conocimiento que contiene todas las reglas expresadas
 * originalmente en LM-Regla y luego en una codificación
 * interna congruente al resto de este modelo.
 * 
 * Responsable de cargar la base de conocimiento, apoyándose
 * en el comportamiento implementado en la clase Regla. 
 * Filtra las reglas que concluyen objetivos, elimina las 
 * marcas de disparo y las marcas para encadenamiento hacia
 * atrás hechas durante este tipo de inferencia.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
 * Fecha: 29/10/2006
 * Hora: 06:10 a.m.
 * 
 * Convertido a Java
 * Alejandro Duarte Sánchez
 * Fecha 27/11/2013
 */


import java.io.IOException;
import java.util.ArrayList;

public class ModuloConocimiento{
	String desc;
	ArrayList<Regla> bc;
	
	public ModuloConocimiento(String desc){
		this.desc=desc;
	}
	public ModuloConocimiento(String desc,String archBC){
		this.desc=desc;
		bc=new ArrayList<Regla>();
		try {
			cargarBC(archBC);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	void cargarBC(String nomArch) throws IOException{
		bc.add(new Regla("<regla> <condicion> <atomo> VERDURAS </atomo> <atomo> CARNE </atomo> <atomo> CEREALES </atomo> <conjuncion/> <conjuncion/> </condicion> <conclusion> <atomo> THT </atomo> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> CARNE </atomo> <atomo> CEREALES </atomo> <atomo> LACTEOS </atomo> <conjuncion/> <conjuncion/> </condicion> <conclusion> <atomo> BAGUETES </atomo> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> BEBIDAS </atomo> <atomo> CARNE </atomo> <conjuncion/> <atomo> OMNIVOROCARNE </atomo> <atomo> CARNIVORO </atomo> <disyuncion/> <disyuncion/> </condicion> <conclusion> <atomoObj> 1 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> PESCADO </atomo> <atomo> CONDIMENTO </atomo> <atomo> VERDURAS </atomo> <conjuncion/> <conjuncion/> <atomo> OMNIVORO_VERDURA_PESCADO </atomo> <disyuncion/> </condicion> <conclusion> <atomoObj> 2 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> LACTEOS </atomo> <atomo> VERDURAS </atomo> <conjuncion/> <atomo> LACTEOCERELEANO </atomo> <disyuncion/> </condicion> <conclusion> <atomoObj> 3 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> PASTAS </atomo> <atomo> VERDURAS </atomo> <atomo> CEREALES </atomo> <atomo> LACTEOS </atomo> <conjuncion/> <conjuncion/> <atomo> MACROBIOTICO </atomo> <disyuncion/> </condicion> <conclusion> <atomoObj> 4 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> CEREALES </atomo> <atomo> CONDIMENTO </atomo> <atomo> PESCADO </atomo> <conjuncion/> </condicion> <conclusion> <atomoObj> 5 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> verduras </atomo> <atomo> CARNE </atomo> <atomo> CEREALES </atomo> <atomo> PESCADO </atomo> <conjuncion/> <conjuncion/> <conjuncion/> </condicion> <conclusion> <atomoObj> 6 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> CONDIMENTO </atomo> <atomo> VERDURAS </atomo> <conjuncion/> </condicion> <conclusion> <atomoObj> 7 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> FRUTAS </atomo> <atomo> VERDURAS </atomo> <atomo> CEREALES </atomo> <atomo> LACTEOS </atomo> <atomo> LEGUMINOSAS </atomo> <conjuncion/> <conjuncion/> <conjuncion/> <conjuncion/> </condicion> <conclusion> <atomoObj> 8 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> CEREALES </atomo> <atomo> LEGUMINOSAS </atomo> <conjuncion/> </condicion> <conclusion> <atomoObj> 9 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> BAGUETES </atomo> <atomo> THT </atomo> <disyuncion/> </condicion> <conclusion> <atomoObj> 10 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> POSTRES </atomo> <atomo> BEBIDAS </atomo> <atomo> ENERGETICO </atomo> <atomo> FRUCTIRISIMO </atomo> <disyuncion/> <disyuncion/> <disyuncion/> </condicion> <conclusion> <atomoObj> 11 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> POSTRES </atomo> <atomo> BEBIDAS </atomo> <atomo> BAGUETES </atomo> <disyuncion/> <disyuncion/> </condicion> <conclusion> <atomoObj> 12 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> VERDURAS </atomo> <atomo> CARNE </atomo> <atomo> CEREALES </atomo> <conjuncion/> <conjuncion/> <atomo> VEGANO </atomo> <atomo> VEGETARIANO </atomo> <atomo> PSEUDOVEGETARIANO </atomo> <disyuncion/> <disyuncion/> <disyuncion/> </condicion> <conclusion> <atomoObj> 13 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> PESCADO </atomo> <atomo> CARNE </atomo> <negacion/> <conjuncion/> <atomo> PESCETARIANO </atomo> <disyuncion/> </condicion> <conclusion> <atomoObj> 14 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> OMNIVORO </atomo> <atomo> OMNIVOROCARNE </atomo> <atomo> OMNIVOROCARNEPESCADO </atomo> <atomo> OMNIVOROCARNEVEGETAL </atomo> <atomo> OMNIVOROPESCADO </atomo> <atomo> OMNIVOROVERDURA </atomo> <atomo> OMNIVOROVERDURAPESCADO </atomo> <disyuncion/> <disyuncion/> <disyuncion/> <disyuncion/> <disyuncion/> <disyuncion/> </condicion> <conclusion> <atomoObj> 15 </atomoObj> </conclusion> </regla>"));
		bc.add(new Regla("<regla> <condicion> <atomo> OMNIVORO </atomo> <atomo> OMNIVOROCARNE </atomo> <atomo> OMNIVOROCARNEPESCADO </atomo> <atomo> OMNIVOROCARNEVEGETAL </atomo> <atomo> OMNIVOROPESCADO </atomo> <atomo> OMNIVOROVERDURA </atomo> <atomo> OMNIVOROVERDURAPESCADO </atomo> <disyuncion/> <disyuncion/> <disyuncion/> <disyuncion/> <disyuncion/> <disyuncion/> <atomo> BEBIDAS </atomo> <conjuncion/> </condicion> <conclusion> <atomoObj> 16 </atomoObj> </conclusion> </regla>"));
		
	}
	@Override
	public String toString(){
		String retorno="Modulo de Conocimiento: "+desc+"\n";
		for(Object elemento : bc){
			retorno+=(elemento+"\n");
		}
		return retorno;
	}
	ArrayList<Regla> filtrarObjs(){
		ArrayList<Regla> objs=new ArrayList<Regla>();
		Regla r=null;
		for(Object elemento : bc){
			r=(Regla)elemento;
			if (r.esObjetivo()) objs.add(r);
		}
		return objs;
	}
	void desmarcar(){
		Regla r=null;
		for(Object elemento : bc){
			r=(Regla)elemento;
			r.marca=false;
		}
	}
	void quitarDisparos(){
		Regla r=null;
		for(Object elemento : bc){
			r=(Regla)elemento;
			r.disparo=false;
		}
	}
}
