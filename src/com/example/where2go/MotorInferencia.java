package com.example.where2go;
/**
 * MotorInferencia
 * 
 * El motor de inferencia es una clase esencial para este gestor
 * genérico de sistemas expertos. Eje de la inferencia y del 
 * proceso de confrontación de datos (almacenados en la memoria
 * de trabajo) con el conocimiento ontológico almacenado en la base 
 * de conocimiento, para producir el resultado meta.
 * 
 * Específicamente este motor de inferencia cuenta con los
 * operadores responsables de realizar encadenamiento hacia 
 * adelante (modus ponens) y hacia atrás (modus ponens invertido), 
 * así como los operadores auxiliares para cumplir estas tareas:
 * concatenar y esElegible.
 * 
 * 
 * Desarrollador: Luis Alberto Casillas Santillán
 * Fecha: 29/10/2006
 * Hora: 07:17 a.m.
 * 
 * Convertido a Java
 * Alejandro Duarte Sánchez
 * Fecha 27/11/2013
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MotorInferencia {

	boolean backward = false;

	ArrayList<ParteRegla> encadenarAdelante(ModuloConocimiento mc, MemoriaTrabajo mt) throws IOException {
		Regla ra = null;
		Atomo aa = null;
		//boolean resConsulta = false;
		boolean resCondicion = false;
		for (Object elemento : mc.bc) {
			ra = (Regla) elemento;
			for (Object elemCond : ra.partesCond) {
				if (elemCond.getClass() == Atomo.class) {
					aa = (Atomo) elemCond;
					aa = new Atomo(aa.desc, aa.estado, aa.objetivo);
					//cuando no esta lleno la memoria de trabajo
					/*if (!mt.presente(aa)) {
						resConsulta = Consultar.porAtomo(aa, ra);
						// Verificación de certidumbre: [0,1] elem R
						aa.estado = resConsulta;
						try {
							// Console.WriteLine("Guardando {0}",aa);
							// El átomo clonado debería llevar
							// la certidumbre.
							 mt.guardaAtomo(new Atomo(aa));
							 System.out.println("MT "+mt);
						} catch (AtomoDuplicado ad) {
							System.out.println("Se duplico el atomo: "+aa);
							// Hacer nada...
						}
					}*/
				}
			}
			resCondicion = ra.probarCondicion(mt);
			if (resCondicion) {
				System.out.println("Se disparo: "+ra);
				// Antes de llamar a ra.dispara(mt)
				// calcular certidumbre del resultado.
				// Debería enviarse como otro parámetro.
				ra.dispara(mt);
				// Console.WriteLine(mt);
				resCondicion = false;
				if (ra.esObjetivo())
					return ra.partesConc;
			} else {
				// if (backward) return null;
				System.out.println("No se disparo...");
				// si la condicion no se cumple, se almacenan
				// los atomos de la conclusion negados
				// esta seccion es opcional y debatible.
				/*
				 * Atomo ac=null; ArrayList atomos=new ArrayList();
				 * foreach(object pc in ra.partesConc){ if (pc is Atomo){
				 * atomos.Add(ac=new Atomo((Atomo)pc)); } if (pc is Negacion){
				 * ac.Estado=!ac.Estado; } }
				 * Console.WriteLine("Hay {0} atomos...",atomos.Count); for(int
				 * i=0;i<atomos.Count;i++){
				 * Console.WriteLine("Por negar: ",atomos[i]); ac=new
				 * Atomo((Atomo)atomos[i]); ac.Estado=!ac.Estado;
				 * Console.WriteLine("Negado: ",ac.Desc); try{
				 * mt.guardaAtomo(ac); Console.WriteLine("Guardado: ",ac);
				 * }catch(AtomoDuplicado ad){
				 * Console.WriteLine("Se duplico el atomo: {0}",ac); // Hacer
				 * nada... }
				 * 
				 * }
				 */
			}
		}
		return null;
	}

	private void concatena(ArrayList<Atomo> destino,ArrayList<ParteRegla> fuente){
		Atomo aTmp=null;
		for(Object aAgregar : fuente){
			if (aAgregar.getClass() == Atomo.class){
				aTmp=new Atomo((Atomo)aAgregar);
				destino.add(aTmp);
			}
			// Ojo, aquí es donde se niega la BC. Debes arreglarlo
			// Parece que ya...
			if (aAgregar.getClass() == Negacion.class)
				aTmp.estado=!aTmp.estado;
		}
	}

	private boolean esElegible(Regla r,ArrayList<Atomo> porSatisfacer){
		ArrayList<Atomo> atomosConc=new ArrayList<Atomo>();
		Atomo aTmp=null;
		for(Object aa : r.partesConc){
			if (aa.getClass() == Atomo.class){
				aTmp=new Atomo((Atomo)aa);
				atomosConc.add(aTmp);
			}
			if (aa.getClass() == Negacion.class) aTmp.estado=!aTmp.estado;
		}
		for(Atomo aa : atomosConc){
			if (porSatisfacer.contains(aa)) return true;
		}
		return false;
	}

	ArrayList<ParteRegla> encadenarAtras(ModuloConocimiento mc,MemoriaTrabajo mt) throws IOException{
		ArrayList<Regla> reglasObj=mc.filtrarObjs();
		ArrayList<Atomo> aSatisfacer=new ArrayList<Atomo>();
		ArrayList<Regla> bcPrima=new ArrayList<Regla>();
		ArrayList<ParteRegla> resultado=null;
		String nomBCPrima=null;
		boolean [] usadas=new boolean[reglasObj.size()];
		boolean salir=false;
		int pos=-1,veces=0,total=reglasObj.size();
		Random r=new Random();
		backward=true;
		ModuloConocimiento mcTmp=null;
		do{
			pos=r.nextInt(total);
			if (!usadas[pos]){					
				veces++;
				usadas[pos]=true;
				aSatisfacer.clear();
				bcPrima.clear();
				mc.desmarcar();
				mc.quitarDisparos();
				for(ParteRegla pConc : ((Regla)reglasObj.get(pos)).partesConc){
					if (pConc.getClass() == Atomo.class){
						Atomo aObj=(Atomo)pConc;
						if (aObj.objetivo){
							nomBCPrima=aObj.desc+"";
						}
					}
				}
				mcTmp=new ModuloConocimiento(nomBCPrima);
				// Bootstrap!!!
				concatena(aSatisfacer,((Regla)reglasObj.get(pos)).partesConc);
				do{
					salir=true;
					for(Regla ra : mc.bc){
						if (!ra.marca&&esElegible(ra,aSatisfacer)){
							salir=false;
							System.out.println("Elegida: "+ra);
							ra.marca=true;
							concatena(aSatisfacer,ra.partesCond);
							bcPrima.add(0,ra);
						}
					}
				}while(!salir);
				mcTmp.bc=bcPrima;
				//Console.WriteLine("Intentando con:\n{0}",mcTmp);				
				resultado=encadenarAdelante(mcTmp,mt);
				if (resultado!=null){
					backward=false;
					return resultado;
				}
			}
		}while(veces<total);
		backward=false;
		return null;
	}
}