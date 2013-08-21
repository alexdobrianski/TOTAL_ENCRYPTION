/*
    2013 (C) Alex Dobrianski Satellite encryption communication
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
// TOTAL_ENCRYPTION.cpp : Defines the class behaviors for the application.
//

#include "stdafx.h"
#include "TOTAL_ENCRYPTION.h"
#include "TOTAL_ENCRYPTIONDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CTOTAL_ENCRYPTIONApp

BEGIN_MESSAGE_MAP(CTOTAL_ENCRYPTIONApp, CWinApp)
	ON_COMMAND(ID_HELP, &CWinApp::OnHelp)
END_MESSAGE_MAP()


// CTOTAL_ENCRYPTIONApp construction

CTOTAL_ENCRYPTIONApp::CTOTAL_ENCRYPTIONApp()
{
	// support Restart Manager
	m_dwRestartManagerSupportFlags = AFX_RESTART_MANAGER_SUPPORT_RESTART;

	// TODO: add construction code here,
	// Place all significant initialization in InitInstance
    memset(szIniFileName, 0, sizeof(szIniFileName));
    memset(szVideoFileName, 0, sizeof(szVideoFileName));

    memset(szEncKeyFileName, 0, sizeof(szEncKeyFileName));
    memset(szDecKeyFileName, 0, sizeof(szDecKeyFileName));
    memset(szEncInFileName, 0, sizeof(szEncInFileName));
    memset(szEncOutFileName, 0, sizeof(szEncOutFileName));
    memset(szDecInFileName, 0, sizeof(szDecInFileName));
    memset(szDecOutFileName, 0, sizeof(szDecOutFileName));
}


// The one and only CTOTAL_ENCRYPTIONApp object

CTOTAL_ENCRYPTIONApp theApp;


// CTOTAL_ENCRYPTIONApp initialization

BOOL CTOTAL_ENCRYPTIONApp::InitInstance()
{
    DWORD dwSize;
	// InitCommonControlsEx() is required on Windows XP if an application
	// manifest specifies use of ComCtl32.dll version 6 or later to enable
	// visual styles.  Otherwise, any window creation will fail.
	INITCOMMONCONTROLSEX InitCtrls;
	InitCtrls.dwSize = sizeof(InitCtrls);
	// Set this to include all the common control classes you want to use
	// in your application.
	InitCtrls.dwICC = ICC_WIN95_CLASSES;
	InitCommonControlsEx(&InitCtrls);

	CWinApp::InitInstance();


	// Create the shell manager, in case the dialog contains
	// any shell tree view or shell list view controls.
	CShellManager *pShellManager = new CShellManager;

	// Standard initialization
	// If you are not using these features and wish to reduce the size
	// of your final executable, you should remove from the following
	// the specific initialization routines you do not need
	// Change the registry key under which our settings are stored
	// TODO: You should modify this string to be something appropriate
	// such as the name of your company or organization
	SetRegistryKey(_T("Local AppWizard-Generated Applications"));
    GetModuleFileNameA(  GetModuleHandle(NULL), (LPSTR) szIniFileName, sizeof(szIniFileName)-1);
    char *DotPtr = strrchr(szIniFileName,'.');
    if (DotPtr != NULL)
        *DotPtr = 0;
    strcat(szIniFileName, ".ini");
    
    
    GetPrivateProfileStringA( "TOTAL_ENCRYPTION", "VIDEO_FILE_NAME", "name", szVideoFileName, sizeof(szVideoFileName)-1,szIniFileName);

    GetPrivateProfileStringA( "TOTAL_ENCRYPTION", "ENC_KEY_FILE_NAME", "name", szEncKeyFileName, sizeof(szEncKeyFileName)-1,szIniFileName);
    GetPrivateProfileStringA( "TOTAL_ENCRYPTION", "DEC_KEY_FILE_NAME", "name", szDecKeyFileName, sizeof(szDecKeyFileName)-1,szIniFileName);
    GetPrivateProfileStringA( "TOTAL_ENCRYPTION", "ENC_IN_FILE_NAME", "name", szEncInFileName, sizeof(szEncInFileName)-1,szIniFileName);
    GetPrivateProfileStringA( "TOTAL_ENCRYPTION", "ENC_OUT_FILE_NAME", "name", szEncOutFileName, sizeof(szEncOutFileName)-1,szIniFileName);
    GetPrivateProfileStringA( "TOTAL_ENCRYPTION", "DEC_IN_FILE_NAME", "name", szDecInFileName, sizeof(szDecInFileName)-1,szIniFileName);
    GetPrivateProfileStringA( "TOTAL_ENCRYPTION", "DEC_OUT_FILE_NAME", "name", szDecOutFileName, sizeof(szDecOutFileName)-1,szIniFileName);

	CTOTAL_ENCRYPTIONDlg dlg;
	m_pMainWnd = &dlg;
    
	INT_PTR nResponse = dlg.DoModal();
    
    //strcpy(szVideoFileName,strTemp);
    WritePrivateProfileStringA( "TOTAL_ENCRYPTION", "VIDEO_FILE_NAME",(LPCSTR) szVideoFileName, szIniFileName);

    WritePrivateProfileStringA( "TOTAL_ENCRYPTION", "ENC_KEY_FILE_NAME",(LPCSTR) szEncKeyFileName, szIniFileName);
    WritePrivateProfileStringA( "TOTAL_ENCRYPTION", "DEC_KEY_FILE_NAME",(LPCSTR) szDecKeyFileName, szIniFileName);

    WritePrivateProfileStringA( "TOTAL_ENCRYPTION", "ENC_IN_FILE_NAME",(LPCSTR) szEncInFileName, szIniFileName);
    WritePrivateProfileStringA( "TOTAL_ENCRYPTION", "ENC_OUT_FILE_NAME",(LPCSTR)szEncOutFileName, szIniFileName);
    WritePrivateProfileStringA( "TOTAL_ENCRYPTION", "DEC_IN_FILE_NAME",(LPCSTR) szDecInFileName, szIniFileName);
    WritePrivateProfileStringA( "TOTAL_ENCRYPTION", "DEC_OUT_FILE_NAME",(LPCSTR) szDecOutFileName, szIniFileName);

	if (nResponse == IDOK)
	{
		// TODO: Place code here to handle when the dialog is
		//  dismissed with OK
	}
	else if (nResponse == IDCANCEL)
	{
		// TODO: Place code here to handle when the dialog is
		//  dismissed with Cancel
	}

	// Delete the shell manager created above.
	if (pShellManager != NULL)
	{
		delete pShellManager;
	}
    	// Since the dialog has been closed, return FALSE so that we exit the
	//  application, rather than start the application's message pump.
	return FALSE;
}

