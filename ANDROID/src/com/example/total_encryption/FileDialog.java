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
    private static final String PARENT_DIR = "..";
    private final String TAG = getClass().getName();
    private String[] fileList;
    private File currentPath;
    private String MyTitle ="choose";

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
    	Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);//THEME_DEVICE_DEFAULT_DARK);
        //builder.setInverseBackgroundForced (false);
        
        builder.setTitle(szTitle);//currentPath.getPath());
        if (writefilename){
        	//final TextView textEnterFileName = new TextView(activity);
            //textEnterFileName.setText("enter file name:");
            final EditText input = new EditText(activity);
            //builder.setView(textEnterFileName);
            builder.setView(input);	
        }
        
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
                if (chosenFile.isDirectory()) {
                    loadFileList(chosenFile);
                    dialog.cancel();
                    dialog.dismiss();
                    showDialog();
                } else
                    fireFileSelectedEvent(chosenFile);
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
            	System.out.println(path.getParentFile());
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
                    else if(sel.isDirectory() && sel.canRead()){
                		
                		File newSel = new File(sel.getAbsolutePath());
                		
                		return getFiles(newSel);
                	}
                    else if(sel.isFile() && sel.getName().contains(".") && FileFormats.checkFileFormat(sel.getName().substring(sel.getName().lastIndexOf(".")))){ //also check for writable
//                			String sub = sel.getName().substring(sel.getName().lastIndexOf("."));
//                			String name = sel.getName();
//                			if(sub.toLowerCase().equals(".jpg")){
//	                			System.out.println(sub);
//	                			System.out.println(sel.getName());
	                			return true;
                    }
                    else if(sel.isFile() && sel.getName().charAt(0) == '.') return false;
                    	
                    else return false;
                    
                    	
                    	
                    	
                        //boolean endsWith = fileEndsWith != null ? filename.toLowerCase().endsWith(fileEndsWith) : true;
                    	//boolean endsWith = FileFormats.checkFileFormat(fileEndsWith);
                        //return endsWith || sel.isDirectory();
                    
                }
                public Boolean getFiles(File f){
                	File files[] = f.listFiles();
                	for(int i=0; i < files.length; ++i){
                		
                		if(files[i].isFile() && files[i].getName().contains(".") && FileFormats.checkFileFormat(files[i].getName().substring(files[i].getName().lastIndexOf(".")))){ //also check for writable
//                			String sub = files[i].getName().substring(files[i].getName().lastIndexOf("."));
//                			String name = files[i].getName();
//                			if(sub.toLowerCase().equals(".jpg")){
//	                			System.out.println(sub);
//	                			System.out.println(files[i].getName());
	                			return true;
//                			}
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
            		System.out.println("file :: " + file);
            		//if(!file.contains("."))
            			r.add(file);
            		if(file.charAt(0) == '.')
            			System.out.println(".");
            		//else if(file.contains(".") && FileFormats.checkFileFormat(file.substring(file.lastIndexOf(".")))){
            		//	r.add(file);
            		//}
                }
            	Collections.sort(r, String.CASE_INSENSITIVE_ORDER);
          	
            }
            	
            
        }
        fileList = (String[]) r.toArray(new String[] {});
        
        
//    	this.currentPath = path;
//        List<String> r = new ArrayList<String>();
//        if (path.exists()) {
//            if (path.getParentFile() != null) {
//            	System.out.println(path.getParentFile());
//                r.add(PARENT_DIR);
//            }
//            FilenameFilter filter = new FilenameFilter() {
//                public boolean accept(File dir, String filename) {
//                    File sel = new File(dir, filename);
//                    if (!sel.canRead())
//                        return false;
//                    if (selectDirectoryOption)
//                        return sel.isDirectory();
//                    else {
//                    	if(fileEndsWith != null)
//                    		System.out.println("fileEndsWith : " + fileEndsWith);
//                        boolean endsWith = fileEndsWith != null ? filename.toLowerCase().endsWith(fileEndsWith) : true;
                    	//boolean endsWith = FileFormats.checkFileFormat(fileEndsWith);
//                        return endsWith || sel.isDirectory();
//                    }
//                }
//            };
//            String[] fileList1 = path.list(filter);
//            if (fileList1 != null)
//            {
//            	for (String file : fileList1) 
//            	{
//            		System.out.println("file :: " + file);
            		//if(!file.contains("."))
//            			r.add(file);
            		//else if(file.contains(".") && FileFormats.checkFileFormat(file.substring(file.lastIndexOf(".")))){
            		//	r.add(file);
            		//}
//                }
//            	Collections.sort(r, String.CASE_INSENSITIVE_ORDER);
//          	
//            }
//            	
//            
//        }
//        fileList = (String[]) r.toArray(new String[] {});        
        
        
        
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