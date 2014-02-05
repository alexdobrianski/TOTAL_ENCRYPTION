/*  
 * 2013 (C) Alex Dobrianski Satellite encryption communication
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
    Design and development by Team "Plan B" is licensed under 
    a Creative Commons Attribution-ShareAlike 3.0 Unported License.
    http://creativecommons.org/licenses/by-sa/3.0/ 
    
 */
package com.example.total_encryption;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.total_encryption.ListenerList.FireHandler;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FileDialog {
	public static File returnFile;
	public static Boolean synced;	
	
	//public synchronized void setSync(Boolean state){
	//	synced = state;
	//}
	
	public synchronized Boolean getSync(){
		return synced;
	}
	
	synchronized void setSync(Boolean state){
		synced = state;
	}
	
    private static final String PARENT_DIR = "..";
    private final String TAG = getClass().getName();
    private String[] fileList;
    private File currentPath;
    private String MyTitle ="choose";
    private Dialog dialog;
    public interface FileSelectedListener {
        void fileSelected(File file);
    }

    public interface DirectorySelectedListener {
        void directorySelected(File directory);
    }

    private ListenerList<FileSelectedListener> fileListenerList = new ListenerList<FileDialog.FileSelectedListener>();
    private ListenerList<DirectorySelectedListener> dirListenerList = new ListenerList<FileDialog.DirectorySelectedListener>();
    private final Activity activity;
    private boolean selectDirectoryOption;
    private boolean writefilename = false;
    private String fileEndsWith;

    public static File getFile(){
    	return returnFile;
    }
    
    /**
     * Constructor
     * @param activity
     * @param initialPath
     */
    public FileDialog(Activity activity, String path) {
        this.activity = activity; 
        loadFileList(new File(path));
    }

    /**
     * @return file dialog
     */
    public Dialog createFileDialog(String szTitle, boolean writef) {
    	
    	writefilename = writef;
    	MyTitle = szTitle;
    	dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);//THEME_DEVICE_DEFAULT_DARK);
        //builder.setInverseBackgroundForced (false);
        
        builder.setTitle(szTitle);//currentPath.getPath());
        //if (writefilename){
        	//final TextView textEnterFileName = new TextView(activity);
            //textEnterFileName.setText("enter file name:");
            //final EditText input = new EditText(activity);
            //builder.setView(textEnterFileName);
        //    builder.setView(input);	
        //}
        
        /*
         * if (selectDirectoryOption) {
         * builder.setPositiveButton("Select directory", new OnClickListener() {
         * public void onClick(DialogInterface dialog, int which) { Log.d(TAG,
         * currentPath.getPath()); fireDirectorySelectedEvent(currentPath); }
         * }); }
         */

        builder.setItems(fileList, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String fileChosen = fileList[which];
                File chosenFile = getChosenFile(fileChosen);
                try{
                	if (chosenFile.isDirectory()) {
                	
                		loadFileList(chosenFile);
	                    dialog.cancel();
	                    dialog.dismiss();
	                    showDialog();
	                } else {
	                    fireFileSelectedEvent(chosenFile);
	                    //MainActivity.selectedFile = chosenFile;
	                    returnFile = chosenFile;
	                    //setSync( true );
	                    FileManager fm = new FileManager();
	                    fm.createRootDir();
	                }
                }catch(Exception ex){
                    dialog.cancel();
                    dialog.dismiss();
                    Toast.makeText(activity, "You have reached the top root folder, please try choosing a file again.", Toast.LENGTH_LONG).show();
                    return;
            	}
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                dialog.dismiss();
            }
        });
        dialog = builder.show();
        
        
        return dialog;
    }

    public void addFileListener(FileSelectedListener listener) {
        fileListenerList.add(listener);
    }

    public void removeFileListener(FileSelectedListener listener) {
        fileListenerList.remove(listener);
    }

    public void setSelectDirectoryOption(boolean selectDirectoryOption) {
        this.selectDirectoryOption = selectDirectoryOption;
    }

    public void addDirectoryListener(DirectorySelectedListener listener) {
        dirListenerList.add(listener);
    }

    public void removeDirectoryListener(DirectorySelectedListener listener) {
        dirListenerList.remove(listener);
    }

    /**
     * Show file dialog
     */
    public void showDialog() {
        createFileDialog(MyTitle,writefilename).show();
    }

    private void fireFileSelectedEvent(final File file) {
        fileListenerList.fireEvent(new FireHandler<FileDialog.FileSelectedListener>() {
            public void fireEvent(FileSelectedListener listener) {
                listener.fileSelected(file);
            }
        });
    }

    private void fireDirectorySelectedEvent(final File directory) {
        dirListenerList.fireEvent(new FireHandler<FileDialog.DirectorySelectedListener>() {
            public void fireEvent(DirectorySelectedListener listener) {
                listener.directorySelected(directory);
            }
        });
    }

    private void loadFileList(File path) {
    	
    	this.currentPath = path;
        List<String> r = new ArrayList<String>();
        if (path.exists()) {
            if (path.getParentFile() != null) {
                r.add(PARENT_DIR);
            }
            FilenameFilter filter = new FilenameFilter() {
                public boolean accept(File dir, String filename) {
                    File sel = new File(dir, filename);
                    if (!sel.canRead())
                        return false;
                    if (selectDirectoryOption){

                        return sel.isDirectory();
                    }
                    else if(sel.isDirectory()){
                		
                		File newSel = new File(sel.getAbsolutePath());
                		
                		return getFiles(newSel);
                	}
                    else if(sel.isFile() && sel.getName().contains(".") && FileFormats.checkFileFormat(sel.getName().substring(sel.getName().lastIndexOf(".")))){ //also check for writable
               			return true;
                    }
                    else if(sel.isFile() && sel.getName().charAt(0) == '.') return false;                    	
                    else return false;

                }
                public Boolean getFiles(File f){
                	
                	File files[] = f.listFiles();
                	
                	if(files.length == 0)
                		return false;
                	
                	for(int i=0; i < files.length; ++i){
                		
                		if(files[i].isFile() && files[i].getName().contains(".") && FileFormats.checkFileFormat(files[i].getName().substring(files[i].getName().lastIndexOf(".")))){ //also check for writable
	                		return true;
                		}
                	}
                	
                	for(int i=0; i < files.length; ++i){
                		
                		if(files[i].isDirectory()){ //also check for writable
                			Boolean hasVid = getFiles(new File(files[i].getAbsolutePath()));
                			if(hasVid)
                				return true;
                		}
                	}                	
                	
                	return false;
                }
            };
            String[] fileList1 = path.list(filter);
            if (fileList1 != null)
            {
            	for (String file : fileList1) 
            	{
           			r.add(file);
                }
            	Collections.sort(r, String.CASE_INSENSITIVE_ORDER);
          	
            }
            	
            
        }
        fileList = (String[]) r.toArray(new String[] {});
               
    }

    private File getChosenFile(String fileChosen) {
        if (fileChosen.equals(PARENT_DIR))
            return currentPath.getParentFile();
        else
            return new File(currentPath, fileChosen);
    }

    public void setFileEndsWith(String fileEndsWith) {
        this.fileEndsWith = fileEndsWith != null ? fileEndsWith.toLowerCase()
                : fileEndsWith;
    }
}

class ListenerList<L> {
    private List<L> listenerList = new ArrayList<L>();

    public interface FireHandler<L> {
        void fireEvent(L listener);
    }

    public void add(L listener) {
        listenerList.add(listener);
    }

    public void fireEvent(FireHandler<L> fireHandler) {

        List<L> copy = new ArrayList<L>(listenerList);
        for (L l : copy) {
            fireHandler.fireEvent(l);
        }
    }

    public void remove(L listener) {
        listenerList.remove(listener);
    }

    public List<L> getListenerList() {
        return listenerList;
    }    
}