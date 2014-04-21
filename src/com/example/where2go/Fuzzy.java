package com.example.where2go;

import java.io.IOException;
import java.util.ArrayList;


public class Fuzzy {
	
	static final int MUCHO 		= 4;
	static final int MAS_NORMAL = 3;
	static final int NORMAL 	= 2;
	static final int CASI_NADA 	= 1;
	static final int NADA 		= 0;
	
	ModuloConocimiento mc=new ModuloConocimiento("Comida","where2go.bc");
	MotorInferencia mi=new MotorInferencia();
	MemoriaTrabajo mt=new MemoriaTrabajo();
	ArrayList<ParteRegla> res=null;
	
	int [][]datos;
	private int []datosFuzzy;
	private String restaurant;
	
	public Fuzzy(int [][]valores){
		datos = valores;
	}
	private void Valores() throws AtomoDuplicado{
	    datosFuzzy = new int [Gustos.COMIDAS];

	    /*
	     * CARNE
	     */
		if( datos[Gustos.CARNE][Gustos.DIAS] >= 7  && datos[Gustos.CARNE][Gustos.VECES] >= 3 ){
			datosFuzzy[Gustos.CARNE] = MUCHO;
			mt.guardaAtomo(new Atomo("CARNE",true,false));
		}
		else if( ( datos[Gustos.CARNE][Gustos.DIAS] >= 5 && datos[Gustos.CARNE][Gustos.VECES] >= 3) || 
				 ( datos[Gustos.CARNE][Gustos.DIAS] >= 6 && datos[Gustos.CARNE][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.CARNE] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("CARNE",true,false));
		}
		else if( (datos[Gustos.CARNE][Gustos.DIAS] >= 3 && datos[Gustos.CARNE][Gustos.VECES] >= 2 ) ||
				(datos[Gustos.CARNE][Gustos.DIAS] >= 4 && datos[Gustos.CARNE][Gustos.VECES] >= 1 ) ){
			datosFuzzy[Gustos.CARNE] = NORMAL;
			mt.guardaAtomo(new Atomo("CARNE",true,false));
		}
		else if( datos[Gustos.CARNE][Gustos.DIAS] >= 1 && datos[Gustos.CARNE][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.CARNE] = CASI_NADA;
			mt.guardaAtomo(new Atomo("CARNE",false,false));
		}
		else if( datos[Gustos.CARNE][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.CARNE] = NADA;
			mt.guardaAtomo(new Atomo("CARNE",false,false));
		}
		
		/*
		 * PESCADOS
		 */
		if( datos[Gustos.PESCADOS][Gustos.DIAS] >= 6  && datos[Gustos.PESCADOS][Gustos.VECES] >= 3 ){
			datosFuzzy[Gustos.PESCADOS] = MUCHO;
			mt.guardaAtomo(new Atomo("PESCADO",true,false));
		}
		else if( (datos[Gustos.PESCADOS][Gustos.DIAS] >= 5 && datos[Gustos.PESCADOS][Gustos.VECES] >= 2 ) ||
				(datos[Gustos.PESCADOS][Gustos.DIAS] >= 6 && datos[Gustos.PESCADOS][Gustos.VECES] >= 1 )){
			datosFuzzy[Gustos.PESCADOS] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("PESCADO",true,false));
		}
		else if( datos[Gustos.PESCADOS][Gustos.DIAS] >= 2 && datos[Gustos.PESCADOS][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.PESCADOS] = NORMAL;
			mt.guardaAtomo(new Atomo("PESCADO",true,false));
		}
		else if( datos[Gustos.PESCADOS][Gustos.DIAS] >= 1 && datos[Gustos.PESCADOS][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.PESCADOS] = CASI_NADA;
			mt.guardaAtomo(new Atomo("PESCADO",false,false));
		}
		else if( datos[Gustos.PESCADOS][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.PESCADOS] = NADA;
			mt.guardaAtomo(new Atomo("PESCADO",false,false));
		}

		/*
		 * VERDURAS
		 */
		if( datos[Gustos.VERDURAS][Gustos.DIAS] >= 7  && datos[Gustos.VERDURAS][Gustos.VECES] >= 4 ){
			datosFuzzy[Gustos.VERDURAS] = MUCHO;
			mt.guardaAtomo(new Atomo("VERDURAS",true,false));
		}
		else if( (datos[Gustos.VERDURAS][Gustos.DIAS] >= 6 && datos[Gustos.VERDURAS][Gustos.VECES] >= 3) ||
				(datos[Gustos.VERDURAS][Gustos.DIAS] >= 7 && datos[Gustos.VERDURAS][Gustos.VECES] >= 1) ){
			datosFuzzy[Gustos.VERDURAS] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("VERDURAS",true,false));
		}
		else if( (datos[Gustos.VERDURAS][Gustos.DIAS] >= 5 && datos[Gustos.VERDURAS][Gustos.VECES] >= 2) || 
				(datos[Gustos.VERDURAS][Gustos.DIAS] >= 6 && datos[Gustos.VERDURAS][Gustos.VECES] >= 1) ){
			datosFuzzy[Gustos.VERDURAS] = NORMAL;
			mt.guardaAtomo(new Atomo("VERDURAS",true,false));
		}
		else if( datos[Gustos.VERDURAS][Gustos.DIAS] >= 2 && datos[Gustos.VERDURAS][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.VERDURAS] = CASI_NADA;
			mt.guardaAtomo(new Atomo("VERDURAS",false,false));
		}
		else if( datos[Gustos.VERDURAS][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.VERDURAS] = NADA;
			mt.guardaAtomo(new Atomo("VERDURAS",false,false));
		}
		
		/*
		 * FRUTAS
		 */
		if( datos[Gustos.FRUTAS][Gustos.DIAS] >= 7  && datos[Gustos.FRUTAS][Gustos.VECES] >= 4 ){
			datosFuzzy[Gustos.FRUTAS] = MUCHO;
			mt.guardaAtomo(new Atomo("FRUTAS",true,false));
		}
		else if( (datos[Gustos.FRUTAS][Gustos.DIAS] >= 6 && datos[Gustos.FRUTAS][Gustos.VECES] >= 3) ||
				(datos[Gustos.FRUTAS][Gustos.DIAS] >= 7 && datos[Gustos.FRUTAS][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.FRUTAS] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("FRUTAS",true,false));
		}
		else if( (datos[Gustos.FRUTAS][Gustos.DIAS] >= 5 && datos[Gustos.FRUTAS][Gustos.VECES] >= 2) || 
				(datos[Gustos.FRUTAS][Gustos.DIAS] >= 6 && datos[Gustos.FRUTAS][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.FRUTAS] = NORMAL;
			mt.guardaAtomo(new Atomo("FRUTAS",true,false));
		}
		else if( datos[Gustos.FRUTAS][Gustos.DIAS] >= 2 && datos[Gustos.FRUTAS][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.FRUTAS] = CASI_NADA;
			mt.guardaAtomo(new Atomo("FRUTAS",false,false));
		}
		else if( datos[Gustos.FRUTAS][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.FRUTAS] = NADA;
			mt.guardaAtomo(new Atomo("FRUTAS",false,false));
		}

		/*
		 * LEGUMINOSAS
		 */
		if( datos[Gustos.LEGUMINOSAS][Gustos.DIAS] >= 7  && datos[Gustos.LEGUMINOSAS][Gustos.VECES] >= 3 ){
			datosFuzzy[Gustos.LEGUMINOSAS] = MUCHO;
			mt.guardaAtomo(new Atomo("LEGUMINOSAS",true,false));
		}
		else if( (datos[Gustos.LEGUMINOSAS][Gustos.DIAS] >= 6 && datos[Gustos.LEGUMINOSAS][Gustos.VECES] >= 3) ||
				(datos[Gustos.LEGUMINOSAS][Gustos.DIAS] >= 7 && datos[Gustos.LEGUMINOSAS][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.LEGUMINOSAS] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("LEGUMINOSAS",true,false));
		}
		else if( (datos[Gustos.LEGUMINOSAS][Gustos.DIAS] >= 4 && datos[Gustos.LEGUMINOSAS][Gustos.VECES] >= 2) ||
				(datos[Gustos.LEGUMINOSAS][Gustos.DIAS] >= 5 && datos[Gustos.LEGUMINOSAS][Gustos.VECES] >= 1) ){
			datosFuzzy[Gustos.LEGUMINOSAS] = NORMAL;
			mt.guardaAtomo(new Atomo("LEGUMINOSAS",true,false));
		}
		else if( datos[Gustos.LEGUMINOSAS][Gustos.DIAS] >= 1 && datos[Gustos.LEGUMINOSAS][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.LEGUMINOSAS] = CASI_NADA;
			mt.guardaAtomo(new Atomo("LEGUMINOSAS",false,false));
		}
		else if( datos[Gustos.LEGUMINOSAS][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.LEGUMINOSAS] = NADA;
			mt.guardaAtomo(new Atomo("LEGUMINOSAS",false,false));
		}

		/*
		 * PASTAS
		 */
		if( datos[Gustos.PASTAS][Gustos.DIAS] >= 7  && datos[Gustos.PASTAS][Gustos.VECES] >= 3 ){
			datosFuzzy[Gustos.PASTAS] = MUCHO;
			mt.guardaAtomo(new Atomo("PASTAS",true,false));
		}
		else if( (datos[Gustos.PASTAS][Gustos.DIAS] >= 5 && datos[Gustos.PASTAS][Gustos.VECES] >= 3) ||
				(datos[Gustos.PASTAS][Gustos.DIAS] >= 6 && datos[Gustos.PASTAS][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.PASTAS] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("PASTAS",true,false));
		}
		else if( datos[Gustos.PASTAS][Gustos.DIAS] >= 3 && datos[Gustos.PASTAS][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.PASTAS] = NORMAL;
			mt.guardaAtomo(new Atomo("PASTAS",true,false));
		}
		else if( datos[Gustos.PASTAS][Gustos.DIAS] >= 1 && datos[Gustos.PASTAS][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.PASTAS] = CASI_NADA;
			mt.guardaAtomo(new Atomo("PASTAS",false,false));
		}
		else if( datos[Gustos.PASTAS][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.PASTAS] = NADA;
			mt.guardaAtomo(new Atomo("PASTAS",false,false));
		}

		/*
		 * LACTEOS
		 */
		if( datos[Gustos.LACTEOS][Gustos.DIAS] >= 7  && datos[Gustos.LACTEOS][Gustos.VECES] >= 4 ){
			datosFuzzy[Gustos.LACTEOS] = MUCHO;
			mt.guardaAtomo(new Atomo("LACTEOS",true,false));
		}
		else if( (datos[Gustos.LACTEOS][Gustos.DIAS] >= 6 && datos[Gustos.LACTEOS][Gustos.VECES] >= 3) || 
				(datos[Gustos.LACTEOS][Gustos.DIAS] >= 7 && datos[Gustos.LACTEOS][Gustos.VECES] >= 1) ){
			datosFuzzy[Gustos.LACTEOS] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("LACTEOS",true,false));
		}
		else if( (datos[Gustos.LACTEOS][Gustos.DIAS] >= 4 && datos[Gustos.LACTEOS][Gustos.VECES] >= 2) || 
				(datos[Gustos.LACTEOS][Gustos.DIAS] >= 5 && datos[Gustos.LACTEOS][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.LACTEOS] = NORMAL;
			mt.guardaAtomo(new Atomo("LACTEOS",true,false));
		}
		else if( datos[Gustos.LACTEOS][Gustos.DIAS] >= 2 && datos[Gustos.LACTEOS][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.LACTEOS] = CASI_NADA;
			mt.guardaAtomo(new Atomo("LACTEOS",false,false));
		}
		else if( datos[Gustos.LACTEOS][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.LACTEOS] = NADA;
			mt.guardaAtomo(new Atomo("LACTEOS",false,false));
		}
		
		/*
		 * BEBIDAS
		 */
		if( datos[Gustos.BEBIDAS][Gustos.DIAS] >= 6  && datos[Gustos.BEBIDAS][Gustos.VECES] >= 4 ){
			datosFuzzy[Gustos.BEBIDAS] = MUCHO;
			mt.guardaAtomo(new Atomo("BEBIDAS",true,false));
		}
		else if( (datos[Gustos.BEBIDAS][Gustos.DIAS] >= 5 && datos[Gustos.BEBIDAS][Gustos.VECES] >= 3) || 
				(datos[Gustos.BEBIDAS][Gustos.DIAS] >= 6 && datos[Gustos.BEBIDAS][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.BEBIDAS] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("BEBIDAS",true,false));
		}
		else if( (datos[Gustos.BEBIDAS][Gustos.DIAS] >= 4 && datos[Gustos.BEBIDAS][Gustos.VECES] >= 2) || 
				(datos[Gustos.BEBIDAS][Gustos.DIAS] >= 5 && datos[Gustos.BEBIDAS][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.BEBIDAS] = NORMAL;
			mt.guardaAtomo(new Atomo("BEBIDAS",true,false));
		}
		else if( datos[Gustos.BEBIDAS][Gustos.DIAS] >= 1 && datos[Gustos.BEBIDAS][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.BEBIDAS] = CASI_NADA;
			mt.guardaAtomo(new Atomo("BEBIDAS",false,false));
		}
		else if( datos[Gustos.BEBIDAS][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.BEBIDAS] = NADA;
			mt.guardaAtomo(new Atomo("BEBIDAS",false,false));
		}

		/*
		 * POSTRES
		 */
		if( datos[Gustos.POSTRES][Gustos.DIAS] >= 7  && datos[Gustos.POSTRES][Gustos.VECES] >= 4 ){
			datosFuzzy[Gustos.POSTRES] = MUCHO;
			mt.guardaAtomo(new Atomo("POSTRES",true,false));
		}
		else if( (datos[Gustos.POSTRES][Gustos.DIAS] >= 5 && datos[Gustos.POSTRES][Gustos.VECES] >= 3) ||
				(datos[Gustos.POSTRES][Gustos.DIAS] >= 6 && datos[Gustos.POSTRES][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.POSTRES] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("POSTRES",true,false));
		}
		else if( datos[Gustos.POSTRES][Gustos.DIAS] >= 3 && datos[Gustos.POSTRES][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.POSTRES] = NORMAL;
			mt.guardaAtomo(new Atomo("POSTRES",true,false));
		}
		else if( datos[Gustos.POSTRES][Gustos.DIAS] >= 1 && datos[Gustos.POSTRES][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.POSTRES] = CASI_NADA;
			mt.guardaAtomo(new Atomo("POSTRES",false,false));
		}
		else if( datos[Gustos.POSTRES][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.POSTRES] = NADA;
			mt.guardaAtomo(new Atomo("POSTRES",false,false));
		}
		
		/*
		 * PANES
		 */
		if( datos[Gustos.PANES][Gustos.DIAS] >= 7  && datos[Gustos.PANES][Gustos.VECES] >= 4 ){
			datosFuzzy[Gustos.PANES] = MUCHO;
			mt.guardaAtomo(new Atomo("CEREALES",true,false));
		}
		else if( (datos[Gustos.PANES][Gustos.DIAS] >= 6 && datos[Gustos.PANES][Gustos.VECES] >= 3) ||
				(datos[Gustos.PANES][Gustos.DIAS] >= 7 && datos[Gustos.PANES][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.PANES] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("CEREALES",true,false));
		}
		else if( (datos[Gustos.PANES][Gustos.DIAS] >= 4 && datos[Gustos.PANES][Gustos.VECES] >= 2) ||
				(datos[Gustos.PANES][Gustos.DIAS] >= 5 && datos[Gustos.PANES][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.PANES] = NORMAL;
			mt.guardaAtomo(new Atomo("CEREALES",true,false));
		}
		else if( datos[Gustos.PANES][Gustos.DIAS] >= 2 && datos[Gustos.PANES][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.PANES] = CASI_NADA;
			mt.guardaAtomo(new Atomo("CEREALES",false,false));
		}
		else if( datos[Gustos.PANES][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.PANES] = NADA;
			mt.guardaAtomo(new Atomo("CEREALES",false,false));
		}

		/*
		 * CONDIMENTADA
		 */
		if( datos[Gustos.CONDIMENTADA][Gustos.DIAS] >= 6  && datos[Gustos.CONDIMENTADA][Gustos.VECES] >= 3 ){
			datosFuzzy[Gustos.CONDIMENTADA] = MUCHO;
			mt.guardaAtomo(new Atomo("CONDIMENTO",true,false));
		}
		else if( (datos[Gustos.CONDIMENTADA][Gustos.DIAS] >= 5 && datos[Gustos.CONDIMENTADA][Gustos.VECES] >= 2) || 
				(datos[Gustos.CONDIMENTADA][Gustos.DIAS] >= 6 && datos[Gustos.CONDIMENTADA][Gustos.VECES] >= 1)){
			datosFuzzy[Gustos.CONDIMENTADA] = MAS_NORMAL;
			mt.guardaAtomo(new Atomo("CONDIMENTO",true,false));
		}
		else if( datos[Gustos.CONDIMENTADA][Gustos.DIAS] >= 3 && datos[Gustos.CONDIMENTADA][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.CONDIMENTADA] = NORMAL;
			mt.guardaAtomo(new Atomo("CONDIMENTO",true,false));
		}
		else if( datos[Gustos.CONDIMENTADA][Gustos.DIAS] >= 1 && datos[Gustos.CONDIMENTADA][Gustos.VECES] >= 1){
			datosFuzzy[Gustos.CONDIMENTADA] = CASI_NADA;
			mt.guardaAtomo(new Atomo("CONDIMENTO",false,false));
		}
		else if( datos[Gustos.CONDIMENTADA][Gustos.DIAS] >= 0 ){
			datosFuzzy[Gustos.CONDIMENTADA] = NADA;
			mt.guardaAtomo(new Atomo("CONDIMENTO",false,false));
		}

	}//fin funcion valores
	public String getPerfil() throws AtomoDuplicado{
		//TODO arreglar codigo repetitivo
		Valores();
		if( datosFuzzy[Gustos.VERDURAS] > CASI_NADA && datosFuzzy[Gustos.CARNE] == NADA && 
				datosFuzzy[Gustos.LACTEOS]==NADA && datosFuzzy[Gustos.PESCADOS]==NADA ){
			mt.guardaAtomo(new Atomo("VEGANO",true,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "Vegano";
		}
		else if( datosFuzzy[Gustos.VERDURAS] > CASI_NADA && datosFuzzy[Gustos.CARNE] == NADA && 
				datosFuzzy[Gustos.LACTEOS] > CASI_NADA && datosFuzzy[Gustos.PESCADOS]==NADA){
			mt.guardaAtomo(new Atomo("VEGANO",false,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",true,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "Vegetariano";
		}
		else if( datosFuzzy[Gustos.VERDURAS] > CASI_NADA && datosFuzzy[Gustos.FRUTAS] > CASI_NADA &&
				datosFuzzy[Gustos.PANES] > CASI_NADA && datosFuzzy[Gustos.PESCADOS]== NADA &&
				 datosFuzzy[Gustos.CARNE] == NADA && datosFuzzy[Gustos.LACTEOS]==NADA ){
			mt.guardaAtomo(new Atomo("VEGANO",false,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",true,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "Macrobiotico";
		}
		else if( datosFuzzy[Gustos.PANES]>CASI_NADA && datosFuzzy[Gustos.VERDURAS] == NADA && 
				datosFuzzy[Gustos.FRUTAS] == NADA && datosFuzzy[Gustos.LACTEOS]==NADA && 
				datosFuzzy[Gustos.PESCADOS]== NADA && datosFuzzy[Gustos.CARNE] == NADA  ){
			mt.guardaAtomo(new Atomo("VEGANO",false,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",true,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "Granivoro";
		}
		else if( datosFuzzy[Gustos.LACTEOS]> CASI_NADA && datosFuzzy[Gustos.PANES]> CASI_NADA &&
				 datosFuzzy[Gustos.CARNE]== NADA  && datosFuzzy[Gustos.FRUTAS]== NADA && 
				 datosFuzzy[Gustos.LEGUMINOSAS]== NADA && datosFuzzy[Gustos.PESCADOS]== NADA &&
				 datosFuzzy[Gustos.VERDURAS]== NADA){
			mt.guardaAtomo(new Atomo("VEGANO",false,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",true,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "LacteoCereleano";
		}
		else if( datosFuzzy[Gustos.FRUTAS] > CASI_NADA && datosFuzzy[Gustos.PANES] == NADA &&
				 datosFuzzy[Gustos.CARNE]== NADA  && datosFuzzy[Gustos.LACTEOS]== NADA && 
				 datosFuzzy[Gustos.LEGUMINOSAS]== NADA && datosFuzzy[Gustos.PESCADOS]== NADA &&
				 datosFuzzy[Gustos.VERDURAS]== NADA){
			mt.guardaAtomo(new Atomo("VEGANO",false,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",true,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "Fructirisimo";
		}
		else if( datosFuzzy[Gustos.VERDURAS] > CASI_NADA && datosFuzzy[Gustos.CARNE] == CASI_NADA && 
				datosFuzzy[Gustos.LACTEOS] > CASI_NADA && datosFuzzy[Gustos.PESCADOS]== CASI_NADA){
			mt.guardaAtomo(new Atomo("VEGANO",false,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",true,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "PseudoVegetariano";
		}
		else if( datosFuzzy[Gustos.VERDURAS] == NADA && datosFuzzy[Gustos.CARNE] == NADA && 
				datosFuzzy[Gustos.LACTEOS] == NADA && datosFuzzy[Gustos.PESCADOS]> CASI_NADA){
			mt.guardaAtomo(new Atomo("VEGANO",false,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",true,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "Pescetariano";
		}
		else if( datosFuzzy[Gustos.VERDURAS] == NADA && datosFuzzy[Gustos.CARNE] >= CASI_NADA && 
				datosFuzzy[Gustos.LACTEOS] == NADA && datosFuzzy[Gustos.PESCADOS]== NADA){
			mt.guardaAtomo(new Atomo("VEGANO",false,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",true,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "Carnivoro";
		}
		else if(  datosFuzzy[Gustos.POSTRES] == CASI_NADA && datosFuzzy[Gustos.BEBIDAS] > CASI_NADA &&
				datosFuzzy[Gustos.VERDURAS] == NADA && datosFuzzy[Gustos.CARNE] == NADA && 
				datosFuzzy[Gustos.LACTEOS] == NADA && datosFuzzy[Gustos.PESCADOS] == NADA){
			mt.guardaAtomo(new Atomo("VEGANO",false,false));
			mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
			mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
			mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
			mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
			mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
			mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
			mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
			mt.guardaAtomo(new Atomo("ENERGETICO",true,false));
			mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
			mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
			return "Energetico";
		}
		else if( datosFuzzy[Gustos.CARNE] >= NADA && datosFuzzy[Gustos.FRUTAS] >= NADA && 
				datosFuzzy[Gustos.LACTEOS] >= NADA && datosFuzzy[Gustos.LEGUMINOSAS] >= NADA && 
				datosFuzzy[Gustos.PANES] >= NADA && datosFuzzy[Gustos.PESCADOS] >= NADA && 
				datosFuzzy[Gustos.VERDURAS] >= NADA){
			
				if(  datosFuzzy[Gustos.CARNE] > datosFuzzy[Gustos.PESCADOS] && 
					 datosFuzzy[Gustos.CARNE] > datosFuzzy[Gustos.VERDURAS]){
					mt.guardaAtomo(new Atomo("VEGANO",false,false));
					mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
					mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
					mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
					mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
					mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
					mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
					mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNE",true,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
						return "OmnivoroCarne";
				}
					
				else if(  datosFuzzy[Gustos.VERDURAS] > datosFuzzy[Gustos.PESCADOS] && 
						 datosFuzzy[Gustos.VERDURAS] > datosFuzzy[Gustos.CARNE]){
					mt.guardaAtomo(new Atomo("VEGANO",false,false));
					mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
					mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
					mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
					mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
					mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
					mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
					mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",true,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
					return "OmnivoroVerdura";
				}
						
				else if(  datosFuzzy[Gustos.PESCADOS] > datosFuzzy[Gustos.VERDURAS] && 
						 datosFuzzy[Gustos.PESCADOS] > datosFuzzy[Gustos.CARNE]){
					mt.guardaAtomo(new Atomo("VEGANO",false,false));
					mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
					mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
					mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
					mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
					mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
					mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
					mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",true,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
					return "OmnivoroPescado";
				}
						
				else if(  datosFuzzy[Gustos.CARNE] > datosFuzzy[Gustos.PESCADOS] && 
						 datosFuzzy[Gustos.CARNE] == datosFuzzy[Gustos.VERDURAS]){
					mt.guardaAtomo(new Atomo("VEGANO",false,false));
					mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
					mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
					mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
					mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
					mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
					mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
					mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",true,false));
					mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
					return "OmnivoroCarneVegetal";
				}
						
				else if(  datosFuzzy[Gustos.CARNE] == datosFuzzy[Gustos.PESCADOS] && 
						 datosFuzzy[Gustos.CARNE] > datosFuzzy[Gustos.VERDURAS]){
					mt.guardaAtomo(new Atomo("VEGANO",false,false));
					mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
					mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
					mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
					mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
					mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
					mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
					mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",true,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
					return "OmnivoroCarnePescado";
				}
						
				else if(  datosFuzzy[Gustos.VERDURAS] == datosFuzzy[Gustos.PESCADOS] && 
						 datosFuzzy[Gustos.VERDURAS] > datosFuzzy[Gustos.CARNE]){
					mt.guardaAtomo(new Atomo("VEGANO",false,false));
					mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
					mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
					mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
					mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
					mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
					mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
					mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVORO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",true,false));
					return "OmnivoroVerduraPescado";
				}
						
				else if(  datosFuzzy[Gustos.VERDURAS] == datosFuzzy[Gustos.PESCADOS] && 
						 datosFuzzy[Gustos.VERDURAS] == datosFuzzy[Gustos.CARNE] && 
						 datosFuzzy[Gustos.VERDURAS] != NADA ){
					mt.guardaAtomo(new Atomo("VEGANO",false,false));
					mt.guardaAtomo(new Atomo("VEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("MACROBIOTICO",false,false));
					mt.guardaAtomo(new Atomo("GRANIVORO",false,false));
					mt.guardaAtomo(new Atomo("LACTEOCERELEANO",false,false));
					mt.guardaAtomo(new Atomo("FRUCTIRISIMO",false,false));
					mt.guardaAtomo(new Atomo("PSEUDOVEGETARIANO",false,false));
					mt.guardaAtomo(new Atomo("PESCETARIANO",false,false));
					mt.guardaAtomo(new Atomo("CARNIVORO",false,false));
					mt.guardaAtomo(new Atomo("ENERGETICO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVORO",true,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNE",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROCARNEVEGETAL",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROPESCADO",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURA",false,false));
					mt.guardaAtomo(new Atomo("OMNIVOROVERDURAPESCADO",false,false));
					return "Omnivoro";
				}
						
		}
		return "NADA";
	}
	public String getRestaurant() throws IOException {
		res=mi.encadenarAtras(mc,mt);
		
		if (res!=null){
			for(ParteRegla a : res){
				restaurant = a.toString();
			}

		}
		else{
			restaurant = "*NADA";
		}
		
		return restaurant.substring(1);
	}
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
}//fin clase Fuzzy
