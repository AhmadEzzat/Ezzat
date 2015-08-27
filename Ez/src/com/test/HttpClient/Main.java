package com.test.HttpClient;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	TextView tv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) findViewById(R.id.textView1);
        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!isOnline()) {
					Toast.makeText(getApplicationContext(), "No Network Available", 3000).show();
					return;
				}
				String uri = "http://services.hanselandpetal.com/feeds/flowers.xml";
				new GetUriContents().execute(uri);
			}
		});
    }
    
    public boolean isOnline(){
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo netInfo = cm.getActiveNetworkInfo();
    	if ((netInfo != null && netInfo.isConnectedOrConnecting())) {
			return true;
		} else {
			return false;
		}
    }
    
    class GetUriContents extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String result = HttpManager.getData(params[0]);
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			tv.setText(result);
		}
    	
    }
}