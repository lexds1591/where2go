package com.example.where2go;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class Gustos extends Activity {
	static final int COMIDAS		= 11;
	  
	  static final int DIAS			= 0;
	  static final int VECES		= 1;
	  
	  static final int CARNE		= 0;
	  static final int PESCADOS		= 1;
	  static final int VERDURAS		= 2;
	  static final int FRUTAS 		= 3;
	  static final int LEGUMINOSAS	= 4;
	  static final int PASTAS		= 5;
	  static final int LACTEOS		= 6;
	  static final int BEBIDAS		= 7;
	  static final int POSTRES		= 8;
	  static final int PANES		= 9;
	  static final int CONDIMENTADA	= 10;

	  ArrayList<String> groupItem = new ArrayList<String>();
	  public Componentes [][]componentes = new Componentes[COMIDAS][4];
	  static int [][]valores;
	  static String perfil,tipo_restaurant;

	  @Override
	protected void onCreate(Bundle savedInstanceState) {
		ExpandableListView ex;
		
	    super.onCreate(savedInstanceState);
	    //eliminar la barra de titulo
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_gustos);

	    ex = (ExpandableListView) findViewById(R.id.ExpList);
	    ex.setDividerHeight(2);
	    ex.setGroupIndicator(null);
	    ex.setClickable(true);

	    setGroupData();
	    setChildGroupData();

	    NewAdapter mNewAdapter = new NewAdapter(groupItem, componentes);
	    mNewAdapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),this);

	    ex.setAdapter(mNewAdapter);
	    
	  }
	  @Override 
	  public void onConfigurationChanged(Configuration newConfig) { 
	      super.onConfigurationChanged(newConfig); 
	  }
	    @Override
		  public boolean onCreateOptionsMenu(Menu menu) {
		      MenuInflater inflater = getMenuInflater();
		      inflater.inflate(R.menu.main, menu);
		      return true;
		  }
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	            case R.id.about:
	                aboutDialog();
	                return true;
			case R.id.action_settings: 
					helpDialog();
				return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
	    public void aboutDialog(){
	    	AlertDialog.Builder dialog = new AlertDialog.Builder(this); 	
	    	dialog.setTitle("About");
	    	dialog.setMessage("Version 0.1 \nDesarrollada por: Alejandro Duarte Sánchez\nMotor de Inferencia por: Luis Casillas");
	    	dialog.setNegativeButton("OK", null);//without listener
	    	dialog.create();
	    	dialog.show();
	    }
	    public void helpDialog(){
	    	AlertDialog.Builder dialog = new AlertDialog.Builder(this); 	
	    	dialog.setTitle("Help");
	    	dialog.setMessage("1.- Abrir cada una de las secciones(Ejemplo: Carnes)\n2.- Llenar cada opcion dependiendo sus habitos Alimenticios\n3.-Una vez que haya llenado todos los tipos de comida presione el boton de guardar");
	    	dialog.setNegativeButton("OK", null);//without listener
	    	dialog.create();
	    	dialog.show();
	    }
	  public void setGroupData() {
	    groupItem.add("Carne");
	    groupItem.add("Pescados y Mariscos");
	    groupItem.add("Verduras");
	    groupItem.add("Frutas");
	    groupItem.add("Leguminosas");
	    groupItem.add("Pastas");
	    groupItem.add("Lacteos");
	    groupItem.add("Bebidas Preparadas");
	    groupItem.add("Postres");
	    groupItem.add("Panes y Cereales");
	    groupItem.add("Muy condimentada");
	  }


	  public void setChildGroupData() {

	    SeekBar seekbar;
	    TextView text;
	    LinearLayout ll_titulo;
	    TextView titulo;
	    LinearLayout ll;
	    
	    /*~Asignar Dias a la semana*/
	    for( int i = 0 ; i < COMIDAS ; i++ ){
	    	  ll_titulo = new LinearLayout(getApplicationContext());
	    	  ll = new LinearLayout(getApplicationContext());
		      seekbar = new SeekBar(getApplicationContext());
		      text = new TextView(getApplicationContext());
		      titulo = new TextView(getApplicationContext());
	
		      seekbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		      seekbar.setMax(7);
		      seekbar.setProgress(-1);
		      text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	
		      text.setText(Integer.toString(seekbar.getProgress()));
		      text.setTextColor(Color.BLUE);
		      text.setTextSize(30);
		      
		      /*TITULO*/
		      titulo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		  	
		      titulo.setText("Dias a la semana");
		      titulo.setTextColor(Color.BLUE);
		      titulo.setTextSize(30);
		      componentes[i][0] = new Componentes(ll_titulo,titulo);
		      componentes[i][1] = new Componentes(ll,seekbar,text);
	    }
	    /*~Asignar Veces al Dia*/
	    for( int i = 0 ; i < COMIDAS ; i++ ){
	    	ll_titulo = new LinearLayout(getApplicationContext());
	    	  ll = new LinearLayout(getApplicationContext());
		      seekbar = new SeekBar(getApplicationContext());
		      text = new TextView(getApplicationContext());
		      titulo = new TextView(getApplicationContext());
		      
		      //ll.setOrientation(LinearLayout.VERTICAL);
		      
		      seekbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		      seekbar.setMax(10);
		      seekbar.setProgress(0);
		      seekbar.isSelected();
		      text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		      text.setText(Integer.toString(seekbar.getProgress()));
		      text.setTextColor(Color.BLUE);
		      text.setTextSize(30);
		      
		      /*TITULO*/
		      titulo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		  	
		      titulo.setText("Veces al dia");
		      titulo.setTextColor(Color.BLUE);
		      titulo.setTextSize(30);
		      componentes[i][2] = new Componentes(ll_titulo,titulo);
		      componentes[i][3] = new Componentes(ll,seekbar,text);
	    }
	    

	  }
	  public void Almacenar(View view) throws IOException{
		  valores = new int [COMIDAS][2];

		  /*se obtienen datos del entorno de los gustos del usuario*/
		  for( int i = 0 ; i < COMIDAS ;i++ ){
			  valores[i][DIAS] = componentes[i][1].seekbar.getProgress();
			  valores[i][VECES] = componentes[i][3].seekbar.getProgress();
		  }
		  
		  /*se realiza la fuzificacion*/
		  Fuzzy fuzzy = new Fuzzy(valores);
		  try {
			perfil=fuzzy.getPerfil();
		} catch (AtomoDuplicado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  tipo_restaurant = fuzzy.getRestaurant();
		  if( !tipo_restaurant.equalsIgnoreCase("NADA") ){
			  
			  //this.finish();
			  Intent intent = new Intent(Gustos.this, Mapa.class);
			  startActivity(intent);
		  }
		  else{
			  AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		    	dialog.setTitle("ERROR");
		    	dialog.setMessage("No ha llenado los suficientes campos");
		    	dialog.setNegativeButton("OK", null);//without listener
		    	dialog.create();
		    	dialog.show();
		  }

	  }
	  
	}