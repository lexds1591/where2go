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
import android.widget.TextView;
import android.widget.Toast;


public class Mapa extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks,GooglePlayServicesClient.OnConnectionFailedListener{
	static final int NUM_RESTAURANTES = 23;
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
        setContentView(R.layout.activity_mapa);
        /*Titulo de la actividad*/
        TextView titulo = (TextView)findViewById(R.id.textView1);
        titulo.setText("Perfil: "+Gustos.perfil);
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
		    	
		    	if( restaurantes[i].getDistancia() <= radio ){
		    		filtrado.add(restaurantes[i]);
		    		posicion = new LatLng(lugar.getLatitude(), lugar.getLongitude());
		    		map.addMarker(new MarkerOptions().title(restaurantes[i].getName()).snippet(restaurantes[i].getDescription()).position(posicion));
		    	}
		    	
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
	                dialog();
	                return true;

	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
	    public void dialog(){
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
	    	//mi casa
	    	restaurantes[0] = new Restaurant(20.754387794387995,-103.38420087906798,"Pollo Pepe","10","Rosticeria de pollo estilo sinaloa");
	    	restaurantes[1] = new Restaurant(20.75417209287763,-103.38457638833006,"Black Coffee","12","Cafe , comida y postres");
	    	restaurantes[2] = new Restaurant(20.748669092147093,-103.38524694058373,"Marissa","11","Pasteles,Nieves");
	    	restaurantes[3] = new Restaurant(20.760236699422343,-103.38937754246672,"Tacos","1 10","Tacos muy malos");
	    	restaurantes[4] = new Restaurant(20.759524409195873,-103.38934535595848,"Tortas Ahogadas","10","Tortas de pollo,carnitas y tacos");
	    	restaurantes[5] = new Restaurant(20.756324077886887,-103.38872308346703,"Pizzas","4 10","Pizzas al horno");
	    	restaurantes[6] = new Restaurant(20.753464790417304,-103.38438863369896,"Carnes","1","Filetes especiales");
	    	restaurantes[7] = new Restaurant(20.752882893713885,-103.38355178448631,"China","2","Comida china");
	    	restaurantes[8] = new Restaurant(20.761631173386633,-103.39045042607262,"Ensaladas","13","Ensaladas con pollo o carne");
	    	restaurantes[9] = new Restaurant(20.75912311744923,-103.38920588108971,"Pescados y Mariscos","14","cockatil y otros platillos");
	    	//cucei
	    	restaurantes[10] = new Restaurant(20.654130546216333,-103.32653468545834 ,"Tacos Paco","10","tacos de barbacoa, asada y tortas ahogadas");
	    	restaurantes[11] = new Restaurant(20.653879564546493,-103.32638448175351,"Ensaladas","13","ensaladas incluye pollo y algunas semillas");
	    	restaurantes[12] = new Restaurant(20.653749053914385,-103.32625573572079,"Pizzas","10 3","Pizzas completas o por rebenadas");
	    	restaurantes[13] = new Restaurant(20.652815397662664,-103.3254296153442,"Carnes Garibaldi","1","Carnes asadas y en su jugo");
	    	restaurantes[14] = new Restaurant(20.65726276259493,-103.32884138521115,"Tortas la michoacana","10","Tortas ahogas y tacos");
	    	restaurantes[15] = new Restaurant(20.657905260539792,-103.32913106378476,"Señora del mas Alla","10 15","comida corrida");
	    	restaurantes[16] = new Restaurant(20.647645046876033,-103.32018321451108,"BurguerKing","10","Hamburguesas");
	    	restaurantes[17] = new Restaurant(20.6473840147951,-103.32023685869137,"Comida china","2","comida china");
	    	restaurantes[18] = new Restaurant(20.64783580080563,-103.32071965631405,"Rollos japoneses","5","comida japonesa");
	    	restaurantes[19] = new Restaurant(20.64767516593346,-103.32008665498654,"Carne a la brasas","1","platillos de carne al carbon");
	    	restaurantes[20] = new Restaurant(20.6473840147951,-103.32025831636349,"Grass","13","comida vegetariana");
	    	restaurantes[21] = new Restaurant(20.63844841568797,-103.31543034013669,"Nieves chapultepec","11","Nieves estilo garrafa");
	    	restaurantes[22] = new Restaurant(20.661037399119934,-103.32386320527951,"Tacos de pescado","14","tacos famosos de pescado");
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

    			if(filtrado.get(i).getTipo().indexOf(Gustos.tipo_restaurant) != -1 ){
    				if(cercano_activo == -1){
    					activo = true;
    					cercano_activo = i;
    				}
    				if( filtrado.get(i).getDistancia() < filtrado.get(cercano_activo).getDistancia() ){
    					cercano_activo = i;
    				}
    			}
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
	    
}
    
