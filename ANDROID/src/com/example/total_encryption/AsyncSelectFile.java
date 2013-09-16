package com.example.total_encryption;

import java.io.File;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class AsyncSelectFile extends AsyncTask  {

	@Override
	protected File doInBackground(Object... arg0) {
		System.out.println("protected File doInBackground(Object... arg0) START");
		// TODO Auto-generated method stub
		while(FileDialog.synced == false) {
			;
		}
		FileDialog.synced = false;
		onPostExecute(FileDialog.returnFile);
		System.out.println("protected File doInBackground(Object... arg0) END");
		return FileDialog.returnFile;
	}
	
	protected void onPostExecute(File f) {
		
		MainActivity.selectedFile = f;
		System.out.println(f.toString());
	}

}
