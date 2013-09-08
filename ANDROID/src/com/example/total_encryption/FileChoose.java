package com.example.total_encryption;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class FileChoose extends Activity {
	private List<String> item = null;
	 private List<String> path = null;
	 private String root="/";
	 private TextView myPath;
	//@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_choose);
		
		//super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        myPath = (TextView)findViewById(R.id.path);
        
        root = Environment.getExternalStorageDirectory().getPath();
        
        getDir(root);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_choose, menu);
		return true;
	}

	//public boolean onOptionsItemSelected(MenuItem item) {
    //    switch (item.getItemId()) {
    //    case android.R.id.home:
    //        NavUtils.navigateUpFromSameTask(this);
    //        return true;
    //    }
    //    return super.onOptionsItemSelected(item);
    //}
	
	private void getDir(String dirPath)

    {
     myPath.setText("Location: " + dirPath);
     item = new ArrayList<String>();
     path = new ArrayList<String>();
     File f = new File(dirPath);
     File[] files = f.listFiles();
     if (files != null)
     {
    	 if(!dirPath.equals(root))
         {
          item.add(root);
          path.add(root);
          item.add("../");
          path.add(f.getParent());
         }
         
         
         for(int i=0; i < files.length; i++)
         {
           File file = files[i];
           path.add(file.getPath());
           if(file.isDirectory())
            item.add(file.getName() + "/");
           else
            item.add(file.getName());
         }

         ArrayAdapter<String> fileList = new ArrayAdapter<String>(this, R.layout.row, item);
         ListView myListView = (ListView)findViewById(R.id.Mylist);
         myListView.setAdapter(fileList);
	 
     }
     
    }



 protected void onListItemClick(ListView l, View v, int position, long id) {
  File file = new File(path.get(position));

  if (file.isDirectory())
  {
   if(file.canRead())
    getDir(path.get(position));
   else
   {
    new AlertDialog.Builder(this)
    .setIcon(R.drawable.ic_launcher)
    .setTitle("[" + file.getName() + "] folder can't be read!")
    .setPositiveButton("OK", 
      new DialogInterface.OnClickListener() {
       @Override
       public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
       }
      }).show();
   }
  }
  else
  {
   new AlertDialog.Builder(this)
    .setIcon(R.drawable.ic_launcher)
    .setTitle("[" + file.getName() + "]")
    .setPositiveButton("OK", 
      new DialogInterface.OnClickListener() {
       @Override
       public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
       }
      }).show();
  }
 }

}
