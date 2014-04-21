package com.example.where2go;

public class Restaurant {
	private double latitude;
	private double longitude;
	private String name;
	private String description;
	private String tipo;
	private double distancia;
	
	public Restaurant(double latitude,double longitude,String name,String tipo,String description){
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.description = description;
		this.tipo = tipo;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
}