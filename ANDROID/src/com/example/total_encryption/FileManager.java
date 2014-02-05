package com.example.total_encryption;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class FileManager {

	private static String EXTERN_STORAGE_PATH = Environment.getExternalStorageDirectory().getPath();
	private static String TOTAL_ENCRYPT = "/TotalEncrypt";
	private static String TMP_FILE = "/tmp";
	static{
		createRootDir();
		tmpFileStorage();
	}
	
	public static Boolean createRootDir(){
		File folder = new File(EXTERN_STORAGE_PATH + TOTAL_ENCRYPT);
		boolean success = true;
		if (!folder.exists()) {
		    success = folder.mkdir();
		}
		if (success) {
		    return true;
		} else {
			return false;
		}
	}
	
	public static void tmpFileStorage(){
		String dir = EXTERN_STORAGE_PATH + TOTAL_ENCRYPT;
		String tmpFileName = TMP_FILE;
		
		File tmpFile = new File(dir, tmpFileName);
		try {
			if(!tmpFile.exists()){
				
				tmpFile.createNewFile();
			}	
		} catch (IOException ex){
			System.out.println(ex.getMessage());
		}	
	}
	
}
