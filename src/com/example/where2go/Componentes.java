package com.example.where2go;

import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class Componentes {
	
	public SeekBar seekbar;
	public boolean seekbarEstado;
	public TextView text;
	public LinearLayout ll;
	public LinearLayout ll_titulo;
	public TextView titulo;
	
	public Componentes(LinearLayout ll_titulo, TextView titulo){
		this.ll_titulo = ll_titulo;
	    this.titulo = titulo;
	}
	public Componentes( LinearLayout ll,SeekBar seekbar , TextView text){
        
		this.ll = ll;
		this.seekbar = seekbar;
		this.seekbarEstado = false;
		this.text = text;
	}
}
