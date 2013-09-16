package com.example.total_encryption;

import java.io.File;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class AsyncSelectFile extends AsyncTask  {

	@Override
	protected File doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		while(FileDialog.synced == false) {
			;
		}
		FileDialog.synced = false;
		return FileDialog.returnFile;
	}
	
	protected void onPostExecute(File f) {
		
		MainActivity.selectedFile = f;
		System.out.println(f.toString());
	}

}
