package com.example.where2go;


import java.util.ArrayList;

import org.w3c.dom.Document;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;


public class MapaTodos extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks,GooglePlayServicesClient.OnConnectionFailedListener{
	static final int NUM_RESTAURANTES = 40;
	LocationClient mLocationClient;
	Location mCurrentLocation;
	LatLng current;
	GoogleMap map;
	int radio;
	Restaurant []restaurantes;
	static ArrayList<Restaurant> filtrado;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// delete bar title
 		requestWindowFeature(Window.FEATURE_NO_TITLE);
 		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_todos);
        /*Titulo de la actividad*/
        /*TextView titulo = (TextView)findViewById(R.id.textView1);
        titulo.setText("Perfil: "+Gustos.perfil);*/
        //inicializar
        radio=300;//default
        restaurantes = new Restaurant[NUM_RESTAURANTES];
        altaRestaurantes();
     // Get a handle to the Map Fragment
        setUpMapIfNeeded();
        mLocationClient = new LocationClient(this, this, this);
        
    }
	  @Override 
	  public void onConfigurationChanged(Configuration newConfig) { 
	      super.onConfigurationChanged(newConfig); 
	  } 
    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        mLocationClient.connect();
    }
    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        mLocationClient.disconnect();
        super.onStop();
    }
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		
	}
	@Override
	public void onConnected(Bundle arg0) {

        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,new LocationListener() {
                    @Override
                    public void onStatusChanged(String provider, int status,
                            Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                    }

                    @Override
                    public void onLocationChanged(final Location location) {
                    	mCurrentLocation = location;
                    }
                });
        if (servicesConnected() && mLocationClient.isConnected()){
        	ConnectivityManager conMan = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        	
        	State internet_movil = conMan.getNetworkInfo(0).getState();//para 3G
        	State wifi = conMan.getNetworkInfo(1).getState();//para WI-FI
        			 
        		  
        	if( wifi == android.net.NetworkInfo.State.CONNECTED || internet_movil == android.net.NetworkInfo.State.CONNECTED){
        		if( manager.isProviderEnabled(LocationManager.GPS_PROVIDER ) ){
        			mCurrentLocation = mLocationClient.getLastLocation();
        			current = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        	        map.setMyLocationEnabled(true);
        	        map.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 17));
        	        
        	        inicializaObjetos();
        	        
        		}
        		else{
        			Toast toast1 = Toast.makeText(getApplicationContext(),"GPS Desactivado", Toast.LENGTH_LONG);
        			toast1.show();
        			
        			this.finish();
        		}
        			
        	}
        	else{
        		Toast toast1 = Toast.makeText(getApplicationContext(),"WI-FI Desactivado", Toast.LENGTH_LONG);
    			toast1.show();
        		this.finish();
        	}
    			
	        
        }
        else{
    		Toast toast1 = Toast.makeText(getApplicationContext(),"Los servicios estan desactivados", Toast.LENGTH_LONG);
			toast1.show();
    		this.finish();
    	}
		
	}
	@Override
	public void onDisconnected() {

		
	}
	 private boolean servicesConnected() {

	        // Check that Google Play services is available
	        int resultCode =
	                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

	        // If Google Play services is available
	        if (ConnectionResult.SUCCESS == resultCode) {

	            // Continue
	            return true;
	        // Google Play services was not available for some reason
	        } else {

	            return false;
	        }
	    }
	 @Override
	    protected void onResume() {
	        super.onResume();
	        setUpMapIfNeeded();
	        //dibujaRadio();
	    }

	    /**
	     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
	     * installed) and the map has not already been instantiated.. This will ensure that we only ever
	     * call {@link #setUpMap()} once when {@link #mMap} is not null.
	     * <p>
	     * If it isn't installed {@link SupportMapFragment} (and
	     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
	     * install/update the Google Play services APK on their device.
	     * <p>
	     * A user can return to this FragmentActivity after following the prompt and correctly
	     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not have been
	     * completely destroyed during this process (it is likely that it would only be stopped or
	     * paused), {@link #onCreate(Bundle)} may not be called again so we should call this method in
	     * {@link #onResume()} to guarantee that it will be called.
	     */
	    private void setUpMapIfNeeded() {
	        // Do a null check to confirm that we have not already instantiated the map.
	        if (map == null) {
	            // Try to obtain the map from the SupportMapFragment.
	            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	            // Check if we were successful in obtaining the map.

	        }
	    }
	    void insertaRestaurantes(){
	    	Location lugar;
	    	LatLng posicion;

	    	filtrado = new ArrayList<Restaurant>();
	    	
	    	for( int i = 0 ; i < NUM_RESTAURANTES ; i++ ){
	    		lugar = new Location("developer");
		    	lugar.setLatitude(restaurantes[i].getLatitude());
		    	lugar.setLongitude(restaurantes[i].getLongitude());
		    	restaurantes[i].setDistancia(mCurrentLocation.distanceTo(lugar));
		    
		    		filtrado.add(restaurantes[i]);
		    		posicion = new LatLng(lugar.getLatitude(), lugar.getLongitude());
		    		map.addMarker(new MarkerOptions().title(restaurantes[i].getName()).snippet(restaurantes[i].getDescription()).position(posicion));
		    	
	    	}

	    }
	    @Override
		  public boolean onCreateOptionsMenu(Menu menu) {
		      MenuInflater inflater = getMenuInflater();
		      inflater.inflate(R.menu.mapa, menu);
		      return true;
		  }
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	            case R.id.action_settings:
	                //dialog();
	                return true;

	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
	    public void dialog(View view){
	    	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
	    	final EditText input = new EditText(this);
	    	
    		InputFilter[] filterArray = new InputFilter[1];
        	filterArray[0] = new InputFilter.LengthFilter(10);
        	input.setFilters(filterArray);
        	input.setHint("Ex: 10");
        	dialog.setView(input);
	    	
	    	dialog.setTitle("Modificar Rango");
	    	dialog.setMessage("Introduce los metros");
	    	dialog.setNegativeButton("Cancelar", null);//without listener
	    	dialog.setPositiveButton("Guardar", new OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            	radio=Integer.parseInt(input.getText().toString());
	            	inicializaObjetos();
	                dialog.cancel();
	            }
	    	});
	    	dialog.create();
	    	dialog.show();
	    }
	    public void dibujaRadio(){
	    	CircleOptions rango_circulo = new CircleOptions();
	        rango_circulo.center(current);
	    	rango_circulo.radius(radio);
	    	rango_circulo.strokeColor(Color.RED);
	    	rango_circulo.strokeWidth(8);
	    	map.addCircle(rango_circulo);
	    }
	    public void altaRestaurantes(){
	    	restaurantes[0] = new Restaurant(20.74630132169018,-103.38568785180667,"Arandinos","16","No tan buenos");
	    	restaurantes[1] = new Restaurant(20.749110579761748,-103.38506557931521,"Al carbon","16","buenos");
	    	restaurantes[2] = new Restaurant(20.759885607084684,-103.38921763887026,"Asada Al carbon","16","buenos");
	    	restaurantes[3] = new Restaurant(20.76023673564998,-103.38941075791934,"De todos","16","No tan buenos");
	    	restaurantes[4] = new Restaurant(20.759564574539734,-103.38940002908328,"Alteños","16","No tan buenos");
	    	restaurantes[5] = new Restaurant(20.76178169164668,-103.39054801454165,"De todos","16","Regular");
	    	restaurantes[6] = new Restaurant(20.75364541482324,-103.38937857141121,"De todos","16","Regular");
	    	restaurantes[7] = new Restaurant(20.737211007695375,-103.41092207421889,"Daniel","16","Buenos");
	    	restaurantes[8] = new Restaurant(20.735144038904956,-103.4070167778932,"Barbacoa","16","Buenos");
	    	restaurantes[9] = new Restaurant(20.68373165808127,-103.34234335412611,"Manuel Acuña","16","Buenos");
	    	restaurantes[10] = new Restaurant(20.715476322195908,-103.3614943264925,"Asada Chorizo","16","Buenos");
	    	restaurantes[11] = new Restaurant(20.699178279568013,-103.35512139787312,"Asada por la Mañana","16","Buenos");
	    	restaurantes[12] = new Restaurant(20.68268777507923,-103.35013248910548,"Barbacoa al horno","16","Buenos");
	    	restaurantes[13] = new Restaurant(20.633456530138027,-103.3177743195498,"De todo","16","Buenos Segun Leslie XD");
	    	restaurantes[14] = new Restaurant(20.637593210725196,-103.31131556024195,"De cabeza","16","Buenos");
	    	restaurantes[15] = new Restaurant(20.677367875178902,-103.31631519784571,"De placer","16","Buenos");
	    	restaurantes[16] = new Restaurant(20.706635052263152, -103.33365299691798,"Del señor","16","Buenos");
	    	restaurantes[17] = new Restaurant(20.705430753109617, -103.32575657357813,"De todo","16","Buenos");
	    	restaurantes[18] = new Restaurant(20.716931419340714, -103.3206711052859,"De todo","16","Buenos");
	    	restaurantes[19] = new Restaurant(20.694350752031514, -103.3232460259402,"De todo","16","Buenos");
	    	restaurantes[20] = new Restaurant(20.670461764777784, -103.36831786622645,"Tomate","16","Buenos");
	    	restaurantes[21] = new Restaurant(20.681443135961974, -103.36808183183314,"De Aire","16","Buenos");
	    	restaurantes[22] = new Restaurant(20.684414193084702, -103.36966969956995,"De todo","16","Buenos");
	    	restaurantes[23] = new Restaurant(20.69704053685156, -103.35876920213343,"Barbacoa","16","Buenos");
	    	restaurantes[24] = new Restaurant(20.68521717151172, -103.37458350648524,"Al carbon","16","Buenos");
	    	restaurantes[25] = new Restaurant(20.69322664871959, -103.35196712007166,"Michoacana","16","Buenos");
	    	restaurantes[26] = new Restaurant(20.68122231182665, -103.4355232953036,"Barbacoa","16","Buenos");
	    	restaurantes[27] = new Restaurant(20.67092352032952, -103.35926272859217,"Al pastor","16","Buenos");
	    	restaurantes[28] = new Restaurant(20.668403924051827, -103.36172499646784,"Al carbon","16","Buenos");
	    	restaurantes[29] = new Restaurant(20.66833365582742, -103.36340405931116,"Al carbon","16","Buenos");
	    	restaurantes[30] = new Restaurant(20.67459743838745, -103.34129192819239,"Suadero","16","Buenos");
	    	restaurantes[31] = new Restaurant(20.67700651672498,  -103.34054090966822,"Al pastor","16","Regular");
	    	restaurantes[32] = new Restaurant(20.700994835373038, -103.32932927598597,"El paisa","16","Buenos");
	    	restaurantes[33] = new Restaurant(20.746241122731274,  -103.40076186647059,"Enfrente de Acapulqueños","16","Buenos");
	    	restaurantes[34] = new Restaurant(20.746532084140984,  -103.40075113763453,"Acapulqueños","16","Buenos");
	    	restaurantes[35] = new Restaurant(20.656899578946856,  -103.38186838616969,"Asada","16","Buenos");
	    	restaurantes[36] = new Restaurant(20.685247283119793, -103.3850226639712,"Providencia","16","Buenos");
	    	restaurantes[37] = new Restaurant(20.65424923987921, -103.34798672189356,"Las coraje","16","Buenos");
	    	restaurantes[38] = new Restaurant(20.70130595600766, -103.35450985421778,"Envinados","16","Regular");
	    	restaurantes[39] = new Restaurant(20.74485654007859, -103.39899160852076 ,"El mudo","16","Buenos");
	    	
	    }
	    public void inicializaObjetos(){
	    	map.clear();
	    	dibujaRadio();
	    	insertaRestaurantes();
	    }
	    public void guardar(View view){
	    	
	    	Restaurant restaurant;
	    	restaurant = masCercano();
	    	LatLng posicion = new LatLng(restaurant.getLatitude(),restaurant.getLongitude());
	    	
	    	map.clear();
	    	map.addMarker(new MarkerOptions().title(restaurant.getName()).snippet(restaurant.getDescription()).position(posicion));
            map.addPolyline( getRect( posicion ) );

	    }
	    public Restaurant masCercano(){
	    	int cercano_activo=-1;
	    	int cercano_default=0;
	    	boolean activo = false;
	    	for( int i = 0 ; i < filtrado.size() ; i++ ){
    			//checar el mas cercano sin importar el tipo
				if( filtrado.get(i).getDistancia() < filtrado.get(cercano_default).getDistancia() ){
					cercano_default = i;
				}

	    		
	    	}

	    	if( activo == true ){
	    		return  filtrado.get(cercano_activo);
	    	}

	    	return  filtrado.get(cercano_default);
	    }
	    public PolylineOptions getRect( LatLng toPosition ){
	    	
	    	GMapV2Direction md = new GMapV2Direction();
	    	
	    	Document doc = md.getDocument(current, toPosition,GMapV2Direction.MODE_WALKING);

	    	ArrayList<LatLng> directionPoint = md.getDirection(doc);
            PolylineOptions rectLine = new PolylineOptions().width(5).color(Color.RED);

            for (int i = 0; i < directionPoint.size(); i++) {
                rectLine.add(directionPoint.get(i));
            }
            return rectLine;
	    }
	    public void quitaRango(View view){
	    	map.clear();
	    	insertaRestaurantes();
	    }
}
    
