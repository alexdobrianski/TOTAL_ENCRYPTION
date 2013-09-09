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

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
		
		final Button buttonEncrypt = (Button) findViewById(R.id.buttonEncrypt);
		buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) 
            {
             	/* something important goes here
			    SetUserNames();
			    CString FileName;
			    m_EncryptKey.GetWindowTextW(FileName);
			    CStringA FileNameA = (CStringA)FileName;
			    strcpy(theApp.szEncKeyFileName,FileNameA);
			    HANDLE hKeyHandle = CreateFile(FileName, GENERIC_READ | GENERIC_WRITE, FILE_SHARE_READ, NULL, OPEN_EXISTING,FILE_ATTRIBUTE_NORMAL, NULL);
			    if (hKeyHandle != INVALID_HANDLE_VALUE)
			    {
			        CString strMsg;
			        CStringA strMsgA;
			        m_Messagetext.GetWindowTextW(strMsg);
			        strMsgA = strMsg;
			        unsigned char *EncryptedMessage = new unsigned char[strMsgA.GetLength()+12+1];
			        if (EncryptedMessage)
			        {
			            memset(EncryptedMessage, 0, strMsgA.GetLength()+12+1);
			            char *EncryptedMessage2 = new char[(strMsgA.GetLength()+12)*2+1];
			            if (EncryptedMessage2)
			            {
			            
			                unsigned int szLenOut;
			                char szTemp[256];
			                *(DWORD*)EncryptedMessage = strMsgA.GetLength();
			                *(DWORD*)&EncryptedMessage[4] = theApp.PosEncKey.LowPart;
			                *(DWORD*)&EncryptedMessage[8] = theApp.PosEncKey.HighPart;
			                memcpy(&EncryptedMessage[12], strMsgA,strMsgA.GetLength());
			                if ( m_StrongEncryption.GetCheck() == FALSE)
			                {
			                    WriteLog("to", (char*)&EncryptedMessage[12]);
			                }
			                LARGE_INTEGER SizeOfKeyFile;
			                GetFileSizeEx(hKeyHandle, &SizeOfKeyFile);
			                if ((theApp.PosEncKey.QuadPart + strMsgA.GetLength()) < SizeOfKeyFile.QuadPart)
			                {
			                    EncryptTotal(&EncryptedMessage[12], strMsgA.GetLength(), &EncryptedMessage[12], szLenOut, hKeyHandle, theApp.PosEncKey, m_StrongEncryption.GetCheck());
			                    
			                    
			                    sprintf(szTemp,"%lX",theApp.PosEncKey.LowPart);
			                    WritePrivateProfileStringA( theApp.szUserProfile, "ENC_KEY_FILE_POSL",(LPCSTR) szTemp, theApp.szIniFileName);
			                    sprintf(szTemp,"%lX", theApp.PosEncKey.HighPart);
			                    WritePrivateProfileStringA( theApp.szUserProfile, "ENC_KEY_FILE_POSH",(LPCSTR) szTemp, theApp.szIniFileName);
			                    for (int i =0; i < strMsgA.GetLength()+12; i++)
			                    {
			                        sprintf(&EncryptedMessage2[i*2],"%02x", EncryptedMessage[i]);
			                    }
			                    strMsg = "=";
			                    strMsg += EncryptedMessage2;
			                    strMsg += "=";
			                    m_Messagetext.SetWindowTextW(strMsg);
			                    delete EncryptedMessage2;
			                    float flSizeUsed;
			                    if (flSizeEnc>0.0)
			                    {
			                        flSizeUsed = theApp.PosEncKey.QuadPart;
			                        flSizeUsed = (flSizeUsed/flSizeEnc)*100.0;
			                        m_EncryptFileUsage.SetPos(100.0 - flSizeUsed); 
			                    }
			                }
			            }
			            delete EncryptedMessage;
			        }
			        CloseHandle(hKeyHandle);
			    }
             	 */
            }
        });
		
		final Button buttonDecrypt = (Button) findViewById(R.id.buttonDecrypt);
		buttonDecrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) 
            {
             	/*
             	 unsigned int mesageLen;
			    char szTemp[256];
			    CString FileName;
			    m_DecryptKey.GetWindowTextW(FileName);
			    CStringA FileNameA = (CStringA)FileName;
			    strcpy(theApp.szDecKeyFileName,FileNameA);
			    HANDLE hKeyHandle = CreateFile(FileName, GENERIC_READ | GENERIC_WRITE, FILE_SHARE_READ, NULL, OPEN_EXISTING,FILE_ATTRIBUTE_NORMAL, NULL);
			    if (hKeyHandle != INVALID_HANDLE_VALUE)
			    {
			        CString strMsg;
			        CStringA strMsgA;
			        char hexLen[8+1];
			        char hexLow[8+1];
			        char hexHigh[8+1];
			        memset(hexLen, 0, sizeof(hexLen));
			        memset(hexLow, 0, sizeof(hexLow));
			        memset(hexHigh, 0, sizeof(hexHigh));
			        m_Messagetext.GetWindowTextW(strMsg);
			        strMsgA = strMsg;
			        unsigned char *EncryptedMessage = new unsigned char[strMsgA.GetLength()+1];
			        
			        if (EncryptedMessage)
			        {
			            memset(EncryptedMessage, 0, strMsgA.GetLength()+1);
			            memcpy(EncryptedMessage, strMsgA,strMsgA.GetLength());
			            if ((EncryptedMessage[0] == '=') && (EncryptedMessage[strMsgA.GetLength()-1] == '='))
			            {
			                memcpy(hexLen, &EncryptedMessage[1], 8);
			                memcpy(hexLow, &EncryptedMessage[1+8], 8);
			                memcpy(hexHigh, &EncryptedMessage[1+8+8], 8);
			                mesageLen = ReverseHexVal(hexLen);
			                theApp.PosDecKey.LowPart  = ReverseHexVal(hexLow);
			                theApp.PosDecKey.HighPart = ReverseHexVal(hexHigh);
			
			                unsigned char *DecryptedMessage = new unsigned char[(strMsgA.GetLength())/2];
			                if (DecryptedMessage)
			                {
			                    memset(DecryptedMessage, 0, (strMsgA.GetLength())/2);
			                    unsigned int szLenOut;
			                    int SizeOfRealMessage = ((strMsgA.GetLength()));
			                    int j = 0;
			                    for (int i=(1+8+8+8); i < (SizeOfRealMessage-1); i+=2,j++)
			                    {
			                        unsigned char chByte;
			                        if ((EncryptedMessage[i] >= '0') && (EncryptedMessage[i] <= '9'))
			                            chByte = EncryptedMessage[i] - '0';
			                        else if ((EncryptedMessage[i] >= 'A') && (EncryptedMessage[i] <= 'F'))
			                            chByte = EncryptedMessage[i] - 'A' + 10;
			                        else if ((EncryptedMessage[i] >= 'a') && (EncryptedMessage[i] <= 'f'))
			                            chByte = EncryptedMessage[i] - 'a' + 10;
			                        chByte <<=4;
			                        if ((EncryptedMessage[i+1] >= '0') && (EncryptedMessage[i+1] <= '9'))
			                            chByte |= EncryptedMessage[i+1] - '0';
			                        else if ((EncryptedMessage[i+1] >= 'A') && (EncryptedMessage[i+1] <= 'F'))
			                            chByte |= EncryptedMessage[i+1] - 'A' + 10;
			                        else if ((EncryptedMessage[i+1] >= 'a') && (EncryptedMessage[i+1] <= 'f'))
			                            chByte |= EncryptedMessage[i+1] - 'a' + 10;
			                        DecryptedMessage[j] =chByte;
			                    }
			                    EncryptTotal(DecryptedMessage, j, DecryptedMessage, szLenOut, hKeyHandle, theApp.PosDecKey, m_StrongEncryption.GetCheck());
			                    
			                    SetUserNames();
			                    sprintf(szTemp,"%lX",theApp.PosDecKey.LowPart);
			                    WritePrivateProfileStringA( theApp.szUserProfile, "DEC_KEY_FILE_POSL",(LPCSTR) szTemp, theApp.szIniFileName);
			                    sprintf(szTemp,"%lX", theApp.PosDecKey.HighPart);
			                    WritePrivateProfileStringA( theApp.szUserProfile, "DEC_KEY_FILE_POSH",(LPCSTR) szTemp, theApp.szIniFileName);
			                    DecryptedMessage[j]=0;
			                    if ( m_StrongEncryption.GetCheck() == FALSE)
			                    {
			                        WriteLog("from",(char*)&DecryptedMessage[0]);
			                    }
			                    strMsg = DecryptedMessage;
			                    m_Messagetext.SetWindowTextW(strMsg);
			                    delete DecryptedMessage;
			                    float flSizeUsed;
			                    if (flSizeDec>0.0)
			                    {
			                        flSizeUsed = theApp.PosDecKey.QuadPart;
			                        flSizeUsed = (flSizeUsed/flSizeDec)*100.0;
			                        m_DecryptFileUsage.SetPos(100.0 - flSizeUsed); 
			                    }
			                }
			            }
			            delete EncryptedMessage;
			        }
			        CloseHandle(hKeyHandle);
			    }
             	 */
            }
        });
		
		final Button buttonGenKey = (Button) findViewById(R.id.buttonGenerate);
		buttonGenKey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) 
            {
//            	Dialog dialog = null;
//                AlertDialog.Builder builder = new AlertDialog.Builder(activity);

//            	Intent intent = new Intent(MainActivity.this,FileChoose.class);
//                startActivity(intent);
//            	Intent intent = new Intent(MainActivity.this,FileDialog.class);
//            	startActivity(intent);
            	
            	FileDialog fd = new FileDialog(MainActivity.this, "..");
            	fd.createFileDialog();
            	
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
