package com.example.where2go;
/**
 * Regla
 * 
 * La clase regla es esencial para este entorno genérico de 
 * sistemas expertos. Representa una expresión del conocimiento
 * en una codificación SI-ENTONCES por medio del lenguaje
 * LM-Regla. 
 * 
 * Responsable de codificar y descodificar el conocimiento 
 * que debe gestionar, puede evaluar y disparar a partir del
 * conocimiento en la memoria de trabajo. Del mismo modo, al
 * disparar ingresa a la memoria de trabajo los elementos 
 * de la conclusión múltiple que debe gestionar, y en el 
 * caso de haber alcanzado un objetivo definitivo del sistema
 * experto lo notifica adecuadamente.
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
 * Fecha: 26/10/2006
 * Hora: 07:49 a.m.
 * 
 * Convertido a Java
 * Alejandro Duarte Sánchez
 * Fecha 27/11/2013
 */


import java.util.ArrayList;

public class Regla{

	ArrayList<ParteRegla> partesCond=new ArrayList<ParteRegla>();		
	ArrayList<ParteRegla> partesConc=new ArrayList<ParteRegla>();
	boolean marca=false;
	boolean disparo=false;
	public Regla(String reglaCad){
		analiza(reglaCad);
	}
	@Override 
	public String toString(){
		String retorno="SI ";
		//for(ParteRegla elemCond : partesCond)
		for(ParteRegla elemCond : partesCond){
			retorno+=(elemCond+" ");
		}
		retorno+="ENTONCES ";
		for(ParteRegla elemConc : partesConc){
			retorno+=(elemConc+" ");
		}
		return retorno;
	}
	boolean probarCondicion(MemoriaTrabajo mt){
		PilaBooleana pb=new PilaBooleana();
		boolean verdad1=false,verdad2=false;
		Atomo aTmp=null,aMT=null;
		Binario bTmp=null;
		for(ParteRegla elemCond : partesCond){				
			if (elemCond.getClass() == Atomo.class){
				aTmp=(Atomo)elemCond;
				aMT=mt.recupera(aTmp);
				//Console.WriteLine(aTmp);
				verdad1=aTmp.verVerdad(aMT);
				pb.push(verdad1);
			} else
			if (elemCond.getClass() == Negacion.class){
				verdad1=pb.pop();
				verdad1=!verdad1;
				pb.push(verdad1);
			} else
			if (elemCond.getClass() == Binario.class){
				bTmp=(Binario)elemCond;
				verdad1=pb.pop();
				verdad2=pb.pop();
				pb.push(bTmp.conj?verdad1&&verdad2:verdad1||verdad2);
			}
		}
		return pb.pop();
	}
	boolean dispara(MemoriaTrabajo mt){
		Atomo aTmp=null;
		boolean llegoObj=false;
		disparo=true;
		ArrayList<Atomo> atomos=new ArrayList<Atomo>();
		for(Object elemConc : partesConc){
			// El nivel de certidumbre que se reciba
			// se asigna a los átomos conclusión
			// que se ingresarán a la MT.
			if (elemConc.getClass() == Atomo.class ){
				aTmp=new Atomo((Atomo)elemConc);		
				atomos.add(aTmp);
				if (aTmp.objetivo) llegoObj=true;
			} else
			if (elemConc.getClass() ==  Negacion.class){
				aTmp.estado=!aTmp.estado;					
			}				
		}
		for(Atomo aa : atomos){
			try{
				mt.guardaAtomo(aa);
			}catch(AtomoDuplicado ad){
				System.out.println("Se duplico el atomo: "+aa);
				// Hacer nada...
			}
		}
		return llegoObj;
	}
	boolean esObjetivo(){
		Atomo aTmp=null;
		for(Object elemento : partesConc){
			if (elemento.getClass() == Atomo.class){
				aTmp=(Atomo)elemento;
				if (aTmp.objetivo) return true;
			}
		}
		return false;
	}
	void analiza(String r){

		String [] partes=r.split("\\s+");
		
		
		boolean cond,conc,atomo,obj;
		ParteRegla pr=null;
		cond=conc=atomo=obj=false;
		//Console.WriteLine(r);
		for(String parte : partes){
				// Etiquetas Dobles //
				if(parte.equals("<atomo>") ){
					atomo=true;
					obj=false;
				}
				// ---------------------------------			         
				else if(parte.equals("</atomo>")){     
					atomo=false;
				    obj=false;
				}
				// ---------------------------------				     
				else if(parte.equals("<atomoObj>")){   
					atomo=true;
				    obj=true;
				}
				// ---------------------------------			         
				else if(parte.equals("</atomoObj>")){  
					atomo=false;
				    obj=false;
				}
				// ---------------------------------				     
				else if(parte.equals("<condicion>")){  
					cond=true;
				}
				// ---------------------------------			         
				else if(parte.equals("</condicion>")){ 
					cond=false;
				}
				// ---------------------------------			         
				else if(parte.equals("<conclusion>")){ 
					conc=true;
				}
				// ---------------------------------			         
				else if(parte.equals("</conclusion>")){
					conc=false;
				}
				// Etiquetas Sencillas //
				else if(parte.equals("<negacion/>")){  
					pr=new Negacion();
					if (cond&&!conc) 
						partesCond.add(pr);
					if (conc&&!cond) 
						partesConc.add(pr);
				}
				// ---------------------------------				     
				else if(parte.equals("<conjuncion/>")){
					pr=new Binario(true);
					if (cond&&!conc) 
						partesCond.add(pr);
					if (conc&&!cond) 
						partesConc.add(pr);
				}
				// ---------------------------------				     
				else if(parte.equals("<disyuncion/>")){
					pr=new Binario(false);
					if (cond&&!conc) 
						partesCond.add(pr);
								     //if (conc&&!cond) partesConc.Add(pr);
				}
				// ---------------------------------				     
				else if (atomo){
					  pr=new Atomo(parte,true,obj);
					  if (cond&&!conc) 
						  partesCond.add(pr);
					  if (conc&&!cond) 
						  partesConc.add(pr);
					}

		}
		//System.out.println("size: "+partesCond.size());

	}
}