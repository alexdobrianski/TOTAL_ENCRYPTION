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
		
		final Button buttonGenKey = (Button) findViewById(R.id.buttonGenerate);
		buttonGenKey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) 
            {
             	//CharSequence MesageText = getText(R.id.editTextMessage);
             	//EditText editText = (EditText)findViewById(R.id.editTextMessage);
             	//editText.setText("", TextView.BufferType.EDITABLE);
            	/*
            	 DWORD dwError = 0;
    			long int Distr[256];
			    for (int ii=0; ii <256; ii++)
			    {
			        Distr[ii] = 0;
			    }
			    CString FileName;
			    m_VIdeoFileName.GetWindowTextW(FileName);
			    CStringA FileNameA = (CStringA)FileName;
			    // set output Key file
			    CFileDialog dlg(FALSE, _T("tkey"), _T("*.tkey"), OFN_OVERWRITEPROMPT);
			    if(dlg.DoModal() == IDOK)
			    {
			        CString KeyFileName;
			        CStringA KeyFileNameA;
			        POSITION fileNamesPosition  = dlg.GetStartPosition();
			        if (fileNamesPosition != NULL)
			        {
			            KeyFileName = dlg.GetNextPathName(fileNamesPosition);
			            KeyFileNameA = (CStringA)KeyFileName;
			            HANDLE hVideoHandle = CreateFile(FileName, GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_EXISTING, NULL, NULL);
			            dwError = GetLastError();
			            HANDLE hKeyHandle = CreateFile(KeyFileName, GENERIC_READ | GENERIC_WRITE, FILE_SHARE_READ, NULL, CREATE_ALWAYS,FILE_ATTRIBUTE_NORMAL, NULL);
			            if ((hVideoHandle != INVALID_HANDLE_VALUE) && (hKeyHandle != INVALID_HANDLE_VALUE))
			            {
			                DWORD dwSize;
			                unsigned char Buffer1[256];
			                unsigned char Buffer2[256];
			                unsigned char Buffer3[256];
			                unsigned char Buffer4[256];
			                unsigned char Buffer5[256];
			                unsigned char Buffer6[256];
			                unsigned char Buffer7[256];
			                unsigned char Buffer8[256];
			                LONG Hpos = 0;
			                LONG Lpos = 0;
			                SetFilePointer(hVideoHandle, Lpos, &Hpos, FILE_END);
			                LARGE_INTEGER SizeOfVideoFile;
			                GetFileSizeEx(hVideoHandle, &SizeOfVideoFile);
			                unsigned __int64 VideoFileSize = (((unsigned __int64)  SizeOfVideoFile.HighPart)<<32) +
			                    (unsigned __int64)SizeOfVideoFile.LowPart;
			                unsigned __int64 lowbyte = 0x7ff & VideoFileSize;
			                VideoFileSize -=lowbyte;
			
			                unsigned __int64 sizeKeyFile = VideoFileSize/8;
			                LARGE_INTEGER Pos1 = {0,0};
			                LARGE_INTEGER Pos2 = {0,0};
			                LARGE_INTEGER Pos3 = {0,0};
			                LARGE_INTEGER Pos4 = {0,0};
			                LARGE_INTEGER Pos5 = {0,0};
			                LARGE_INTEGER Pos6 = {0,0};
			                LARGE_INTEGER Pos7 = {0,0};
			                LARGE_INTEGER Pos8 = {0,0};
			                Pos2.QuadPart = sizeKeyFile;
			                Pos3.QuadPart = Pos2.QuadPart*2;
			                Pos4.QuadPart = Pos2.QuadPart*3;
			                Pos5.QuadPart = Pos2.QuadPart*4;
			                Pos6.QuadPart = Pos2.QuadPart*5;
			                Pos7.QuadPart = Pos2.QuadPart*6;
			                Pos8.QuadPart = Pos2.QuadPart*7;
			                for (unsigned __int64 i64 =0; i64 < sizeKeyFile; i64+=sizeof(Buffer1))
			                {
			                    SetFilePointer(hVideoHandle, Pos1.LowPart, &Pos1.HighPart, FILE_BEGIN);
			                    ReadFile(hVideoHandle, Buffer1, sizeof(Buffer1),&dwSize,NULL);
			                    Pos1.QuadPart += sizeof(Buffer1);
			
			                    SetFilePointer(hVideoHandle, Pos2.LowPart, &Pos2.HighPart, FILE_BEGIN);
			                    ReadFile(hVideoHandle, Buffer2, sizeof(Buffer2),&dwSize,NULL);
			                    Pos2.QuadPart += sizeof(Buffer2);
			
			                    SetFilePointer(hVideoHandle, Pos3.LowPart, &Pos3.HighPart, FILE_BEGIN);
			                    ReadFile(hVideoHandle, Buffer3, sizeof(Buffer3),&dwSize,NULL);
			                    Pos3.QuadPart += sizeof(Buffer3);
			
			                    SetFilePointer(hVideoHandle, Pos4.LowPart, &Pos4.HighPart, FILE_BEGIN);
			                    ReadFile(hVideoHandle, Buffer4, sizeof(Buffer4),&dwSize,NULL);
			                    Pos4.QuadPart += sizeof(Buffer4);
			
			                    SetFilePointer(hVideoHandle, Pos5.LowPart, &Pos5.HighPart, FILE_BEGIN);
			                    ReadFile(hVideoHandle, Buffer5, sizeof(Buffer5),&dwSize,NULL);
			                    Pos5.QuadPart += sizeof(Buffer5);
			
			                    SetFilePointer(hVideoHandle, Pos6.LowPart, &Pos6.HighPart, FILE_BEGIN);
			                    ReadFile(hVideoHandle, Buffer6, sizeof(Buffer6),&dwSize,NULL);
			                    Pos6.QuadPart += sizeof(Buffer6);
			
			                    SetFilePointer(hVideoHandle, Pos7.LowPart, &Pos7.HighPart, FILE_BEGIN);
			                    ReadFile(hVideoHandle, Buffer7, sizeof(Buffer7),&dwSize,NULL);
			                    Pos7.QuadPart += sizeof(Buffer7);
			                    for (int i = 0; i <sizeof(Buffer1); i++)
			                    {
			                        Buffer1[i] = Buffer1[i] ^ Buffer2[i] ^ Buffer3[i] ^ Buffer4[i] ^ Buffer5[i] ^ Buffer6[i] ^ Buffer7[i];
			                        Distr[Buffer1[i]]++;
			                    }
			                    WriteFile(hKeyHandle, Buffer1, sizeof(Buffer1),&dwSize, NULL);
			                }
			            }
			            
			
			            if (hVideoHandle != INVALID_HANDLE_VALUE)
			                CloseHandle(hVideoHandle);
			            if (hKeyHandle != INVALID_HANDLE_VALUE)
			                CloseHandle(hKeyHandle);
			        }
			    }
            	 */
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
