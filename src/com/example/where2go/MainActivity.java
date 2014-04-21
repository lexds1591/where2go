package com.example.where2go;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;


public class MainActivity extends Activity{


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		
	    super.onCreate(savedInstanceState);
	    setRequestedOrientation(ActivityInfo. SCREEN_ORIENTATION_SENSOR_PORTRAIT);
	    //eliminar la barra de titulo
	    
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_main);
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
	    public void gustos(View view){
	    	 Intent intent = new Intent(MainActivity.this, Gustos.class);
			 startActivity(intent);
	    }
	    public void todos(View view){
	    	 Intent intent = new Intent(MainActivity.this, MapaTodos.class);
			 startActivity(intent);
	    }
}
	  