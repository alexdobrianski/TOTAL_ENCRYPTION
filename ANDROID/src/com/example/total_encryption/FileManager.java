package com.example.total_encryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class FileManager {

	private Context context;
	private static String EXTERN_STORAGE_PATH = Environment.getExternalStorageDirectory().getPath();
	private static String TOTAL_ENCRYPT = "TotalEncrypt";
	private static String TMP_FILE = "tmp";
	
	public FileManager(Context c) {
		this.context = c;
		createRootDir();
		resetTmpFile();
	}
	
	public final void resetTmpFile() {
		context.deleteFile(TMP_FILE); //check to see if this method throws exception
		try{
			FileInputStream fInStream = context.openFileInput(TMP_FILE);
			fInStream.close(); 
		} catch (FileNotFoundException ex1) {
			try {
				FileOutputStream fOutStream = context.openFileOutput(TMP_FILE, Context.MODE_WORLD_WRITEABLE | Context.MODE_WORLD_READABLE); //permission param check
				Log.d("FileManager", context.getFilesDir().toString());
				fOutStream.close();
			} catch (FileNotFoundException ex2) {
			} catch (IOException ioEx2) {}
		} catch (IOException ioEx1) {}
	}
	
	public final void createRootDir(){
		Log.d("FileManager", "public final void createRootDir()");
		
		try{
			FileInputStream fInStream = context.openFileInput(TOTAL_ENCRYPT);
			fInStream.close(); 
		} catch (FileNotFoundException ex1) {
			try {
				FileOutputStream fOutStream = context.openFileOutput(TOTAL_ENCRYPT, Context.MODE_WORLD_WRITEABLE | Context.MODE_WORLD_READABLE);
				Log.d("FileManager", context.getFilesDir().toString());
				fOutStream.close();
			} catch (FileNotFoundException ex2) {
			} catch (IOException ioEx2) {}
		} catch (IOException ioEx1) {}
		
/*		File folder = new File(EXTERN_STORAGE_PATH + TOTAL_ENCRYPT);
		boolean success = true;
		if (!folder.exists()) {
		    success = folder.mkdir();
		}
		if (success) {
		    return true;
		} else {
			return false;
		}*/

	}
	
	public static void tmpFileStorage(){
/*		String dir = EXTERN_STORAGE_PATH + TOTAL_ENCRYPT;
		String tmpFileName = TMP_FILE;
		
		File tmpFile = new File(dir, tmpFileName);
		try {
			if(!tmpFile.exists()){
				
				tmpFile.createNewFile();
				tmpFile.setWritable(true);		
			}	
		} catch (IOException ex){
			System.out.println(ex.getMessage());
			//throw new IOException("tmpFileStorage IOException : " + ex.getMessage());
		} */
	}
	
	public String readTmpFile() {
		
		try {
			FileInputStream fIn = context.openFileInput(TMP_FILE);
			
			
			String s = new String();
			for(int content = 0; (content = fIn.read()) != -1; ) {
				s += String.valueOf((char)content);

			}
			Log.d("FileManager CALLING readTmpFile", s);			
//			Log.d("FileManager CALLING readTmpFile", fIn.read() );
			
			
		} catch (FileNotFoundException ex) {
			Toast.makeText(context, "Could not select file. Please restart app and try again.", Toast.LENGTH_LONG).show();
			Log.d("FileManager CALLING : writeFileInfo FileNotFountException", ex.getMessage());
		} catch (IOException ioEx) {
			Toast.makeText(context, ioEx.getMessage(), Toast.LENGTH_LONG).show();
			Log.d("FileManager CALLING : writeFileInfo FileNotFountException", ioEx.getMessage());
		}
		
/*		try{
			StringBuffer outStringBuf = new StringBuffer();
            String inputLine = "";
			FileInputStream fIn = context.openFileInput(EXTERN_STORAGE_PATH + TOTAL_ENCRYPT + TMP_FILE);
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader inBuff = new BufferedReader(isr);
            while ((inputLine = inBuff.readLine()) != null) {
                outStringBuf.append(inputLine);
                outStringBuf.append("\n");
            }
            inBuff.close();
            return outStringBuf.toString();
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		return null;*/
		return null;
	}
	
	private byte[] writeToByteArray(String message) {

		byte[] rByte = new byte[message.length()];
		
		for(int i = 0; i < message.length(); ++i) {
			rByte[i] = (byte) message.charAt(i);
			Log.d("FileManager CALLING : writeToBypte", "index : " + i + " value : " + rByte[i] + " real value : " + (char)rByte[i]);
			
		}
		return rByte;
	}
	
	public void writeFileInfo(Context context, String value){
		
		Log.d("FileManager CALLING : writeFileInfo", value);
		context.deleteFile(TMP_FILE);
		try {
			FileOutputStream fOutStream = context.openFileOutput(TMP_FILE, Context.MODE_WORLD_WRITEABLE | Context.MODE_WORLD_READABLE);
			//writeToByteArray("Hello world");
			String message = "hello world";
			fOutStream.write(message.getBytes());
			fOutStream.flush();
			fOutStream.close();
		} catch ( FileNotFoundException ex) {
			Toast.makeText(context, "Could not select file. Please restart app and try again.", Toast.LENGTH_LONG).show();
			Log.d("FileManager CALLING : writeFileInfo FileNotFountException", ex.getMessage());
		} catch ( IOException ioEx ) {
			Toast.makeText(context, "Could not select file. Please restart app and try again.", Toast.LENGTH_LONG).show();
			Log.d("FileManager CALLING : writeFileInfo IoException", ioEx.getMessage());
		}
		
		
/*		this.context = context;
		try{
			FileOutputStream fOut = context.openFileOutput(new File(TMP_FILEEXTERN_STORAGE_PATH + TOTAL_ENCRYPT + TMP_FILE).getName() , Context.MODE_WORLD_WRITEABLE);
			OutputStreamWriter osw = new OutputStreamWriter(fOut);
            // Write the string to the file
            osw.write(value);
            // save and close
            osw.flush();
            osw.close();
		} catch(FileNotFoundException ex){
			System.out.println(ex.getMessage());
		} catch(IOException ex){
			System.out.println(ex.getMessage());
		}*/
	}
	
}
