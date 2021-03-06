package fitness.activity;

import java.util.ArrayList;

import com.example.yjl.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;



public class MainActivity extends Activity implements OnMapReadyCallback {
private GoogleMap mMap;
boolean temp = true;
boolean state = true;
double avgSpeed = 0;
long startTime = 0;
long endTime = 0;
double sSum = 0;
int count = 0;
TextView a;
ImageButton startButton 
ImageButton stopButton;
ArrayList<LatLng> traceOfMe= new ArrayList<LatLng>();
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent i=getIntent();
		String username=i.getStringExtra("Username");
		
	    a =(TextView) findViewById(R.id.nameTemp);
		a.setText("Welcome,"+ username +"!");
	    
		startButton=(ImageButton) findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			
				state=false;
				temp=false;
				
			}
		});
		stopButton=(ImageButton) findViewById(R.id.stopButton);
		stopButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			
				state=true;
				temp=true;
				
			}
		});

	       MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment1);
	                
	       mapFragment.getMapAsync(this);
	    }
	  
	public boolean onCreateOptionsMenu(Menu menu)
	  {
	    getMenuInflater().inflate(R.menu.menu, menu);
	    return true;
	  }
	  
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item)
	  {
	    switch (item.getItemId())
	    {
	      case R.id.Account : 
	    	  Intent i=getIntent();
	    	  String username=i.getStringExtra("Username");
	    	  Intent j=new Intent(MainActivity.this,AccountActivity.class);
			  j.putExtra("Username",username);
			  MainActivity.this.startActivity(j);
	                             
	      break;
	    }
	    return true;
	  }

	
	    public void onMapReady( GoogleMap map) {
	    
	    	 map.setMyLocationEnabled(true);
	    	 map.getUiSettings().setZoomControlsEnabled(true);
	    	 
	    	 mMap=map;

             mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

           @Override
           
          public void onMyLocationChange(Location arg0) {
        	   
        	
        	 LatLng address = new LatLng(arg0.getLatitude(), arg0.getLongitude());
         	
        	 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(address, 17));
         	
           
      
          	
          if(state==false){
            
           	if(temp==false){
                
                mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!")
               	    .snippet("Here is the start!")
                       .position(address));
                startTime = arg0.getTime();
                temp= true;
             }
          	
                double speedOrigin = arg0.getSpeed();
                double speed = ((int)(speedOrigin*100))/100.00;
           
           
         
   			    sSum = sSum+speed;
        	    count ++;
   		  
          // seconds=5*counts
           a.setText("moving speed is"+ speed +"m/s!"+count);
           
          
           traceOfMe.add(new LatLng(arg0.getLatitude(), arg0.getLongitude()));
          
           PolylineOptions polylineOpt = new PolylineOptions();
           for (LatLng LatLng : traceOfMe) {
     			polylineOpt.add(LatLng);
     	   }
     		
     		polylineOpt.color(Color.RED);
     		
     		Polyline line = mMap.addPolyline(polylineOpt);
     		line.setWidth(8);
          
           }
           
           else if(state == true ){
        	  
          
           if(temp == false){
       	   
           endTime =arg0.getTime();
       	   avgSpeed=sSum/count;
       	   double distance = avgSpeed*(endTime-startTime)/1000;
       	  
           a.setText("walk distance is"+ (int)(distance) +"m!");
                sSum=0;
    	        count=0;
    	       
    	        
                temp=true;
        	   
        	   }
           }
           
           }
        
           
          });
             
     
	    
		
	}
}
	

