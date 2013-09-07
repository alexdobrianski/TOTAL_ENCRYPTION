package com.example.total_encryption;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button buttonClean = (Button) findViewById(R.id.buttonClean);
		buttonClean.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) 
            {
             	CharSequence MesageText = getText(R.id.editTextMessage);
             	EditText editText = (EditText)findViewById(R.id.editTextMessage);
             	editText.setText("", TextView.BufferType.EDITABLE);
            }
        });
		
		
		final ImageButton buttonAdobri = (ImageButton) findViewById(R.id.imageButton1);
		buttonAdobri.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) 
            {
            	String url = "http://www.adobri.com/blog.aspx";
            	Intent i = new Intent(Intent.ACTION_VIEW);
            	i.setData(Uri.parse(url));
            	startActivity(i);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
