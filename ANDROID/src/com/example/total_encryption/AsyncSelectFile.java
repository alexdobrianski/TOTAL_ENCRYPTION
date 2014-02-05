package com.example.total_encryption;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class AsyncSelectFile extends AsyncTask {
	Activity con;
	FileDialog fd;
	String root;

	AsyncSelectFile(Activity c, FileDialog r){
		con = c;
		fd = r;
		
	}
	
	@Override
    protected File doInBackground(Object... arg0) {
        System.out.println("protected File doInBackground(Object... arg0) START");
        // TODO Auto-generated method stub
        while(fd.getSync() == false) {
            ;
        }
        
        System.out.println("protected File doInBackground(Object... arg0) END");
        MainActivity.selectedFile = fd.returnFile;
        Toast t = Toast.makeText(con.getApplicationContext(), fd.returnFile.toString(), Toast.LENGTH_SHORT);
        t.show();
        return FileDialog.returnFile;
    }
	
	@Override
	public void onPreExecute(){
		//fd = new FileDialog(con, root);
		//fd.createFileDialog("choose video file as a key source", false);
	}
	
    
    protected void onPostExecute(File f) {
            
        MainActivity.selectedFile = f;
        //System.out.println(f.toString());
        Toast t = Toast.makeText(con.getApplicationContext(), f.toString(), Toast.LENGTH_SHORT);
        t.show();
    }

}
