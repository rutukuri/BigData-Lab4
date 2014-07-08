package com.example.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
TextView create,insert,save,retrive,delete,output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		create = (TextView) findViewById(R.id.button_create);
		insert = (TextView) findViewById(R.id.button_insert);
		save = (TextView) findViewById(R.id.button_save);
		retrive = (TextView) findViewById(R.id.button_retrive);
		delete = (TextView) findViewById(R.id.button_delete);
		output = (TextView) findViewById(R.id.textView3);
		
		create.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new PostLab3().execute("0");
			}});
		
		insert.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new PostLab3().execute("1");
			}});
		
		retrive.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new PostLab3().execute("2");
			}});
		
		delete.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new PostLab3().execute("3");
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	
	
	  class PostLab3 extends AsyncTask<String, Void, String> { 
		  
	        String st; 
	  
	        private ProgressDialog dialog; 
	        Context context;
	  
	  
	        @Override
	        protected String doInBackground(String... params) { 
	            // TODO Auto-generated method stub 
	  
	            String method_call = params[0]; 
	  
	            HttpClient httpClient = new DefaultHttpClient(); 
	            HttpPost httpPost = new HttpPost();;
	            if(method_call.equalsIgnoreCase("0")){
	            httpPost = new HttpPost(
	                    "http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseCreate/pilla1Table/GeoLocation:Date:Accelerometer"); 
	            }
	            else if(method_call.equalsIgnoreCase("1"))
	            {
	            	httpPost = new HttpPost( 
		                    "http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseInsert/ruykuTable/-home-group8-sensor.txt/GeoLocation:Date:Accelerometer"); 
		            	
	            }
	            else if(method_call.equalsIgnoreCase("2"))
	            {
	            	httpPost = new HttpPost( 
		                    "http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseRetrieveAll/ruTable"); 
		            	
	            }
	            else if(method_call.equalsIgnoreCase("3"))
	            {
	            	httpPost = new HttpPost( 
		                    "http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbasedeletel/ruTable"); 
		            	
	            }
	            
	            
	            
	            
	            try { 
  
				    HttpResponse httpResponse = httpClient.execute(httpPost); 
  
				    // According to the JAVA API, InputStream constructor do 
				    // nothing. 
				    // So we can't initialize InputStream although it is not 
				    // an interface 
				    InputStream inputStream = httpResponse.getEntity() 
				            .getContent(); 
  
				    InputStreamReader inputStreamReader = new InputStreamReader( 
				            inputStream); 
  
				    BufferedReader bufferedReader = new BufferedReader( 
				            inputStreamReader); 
  
				    StringBuilder stringBuilder = new StringBuilder(); 
  
				    String bufferedStrChunk = null; 
  
				    while ((bufferedStrChunk = bufferedReader.readLine()) != null) { 
				        stringBuilder.append(bufferedStrChunk); 
				    } 
  
				    st = stringBuilder.toString(); 
				    return stringBuilder.toString(); 
  
				} catch (ClientProtocolException cpe) { 
				    System.out 
				            .println("First Exception caused by HttpResponese :"
				                    + cpe); 
				    cpe.printStackTrace(); 
				} catch (IOException ioe) { 
				    System.out 
				            .println("Second Exception caused by HttpResponse :"
				                    + ioe); 
				    ioe.printStackTrace(); 
				} 
	            return st; 
	        } 
	  
	        @Override
	        protected void onPostExecute(String result) { 
	            super.onPostExecute(result); 
	            
	  
	                System.out.println("lopala em undi?????"+result);
	                output.setText(result);
	                
	             	   
	        }}
}
