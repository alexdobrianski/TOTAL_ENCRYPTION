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
// TOTAL_ENCRYPTIONDlg.cpp : implementation file
//

#include "stdafx.h"
#include "TOTAL_ENCRYPTION.h"
#include "TOTAL_ENCRYPTIONDlg.h"
#include "afxdialogex.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif

extern CTOTAL_ENCRYPTIONApp theApp;
// CAboutDlg dialog used for App About

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

// Dialog Data
	enum { IDD = IDD_ABOUTBOX };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support

// Implementation
protected:
	DECLARE_MESSAGE_MAP()
public:
//    afx_msg void OnButtonGenKey();
};

CAboutDlg::CAboutDlg() : CDialogEx(CAboutDlg::IDD)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
//    ON_COMMAND(IDC_BUTTON_GEN_KEY, &CAboutDlg::OnButtonGenKey)
END_MESSAGE_MAP()


// CTOTAL_ENCRYPTIONDlg dialog




CTOTAL_ENCRYPTIONDlg::CTOTAL_ENCRYPTIONDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CTOTAL_ENCRYPTIONDlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CTOTAL_ENCRYPTIONDlg::DoDataExchange(CDataExchange* pDX)
{
    CDialogEx::DoDataExchange(pDX);
    DDX_Control(pDX, IDC_MFCEDITVIDEO_FILE, m_VIdeoFileName);
    DDX_Control(pDX, IDC_BUTTON_GEN_KEY, m_GenKey);
    DDX_Control(pDX, IDC_BUTTON_DEC_FILE, m_DecryptFile);
    DDX_Control(pDX, IDC_BUTTON_DEC_MSG, m_DEcryptMessage);
    DDX_Control(pDX, IDC_BUTTON_ENC_FILE, m_EncryptFile);
    DDX_Control(pDX, IDC_BUTTON_ENC_MSG, m_EncryptMessage);
    DDX_Control(pDX, IDC_MFCEDIT_DEC, m_DecryptKey);
    DDX_Control(pDX, IDC_MFCEDIT_ENC, m_EncryptKey);
    DDX_Control(pDX, IDC_MFCEDIT_ENC_INFILE, m_EncryptInFileName);
    DDX_Control(pDX, IDC_MFCEDIT_ENC_OUTFILE, m_EncryptOutFileName);
    DDX_Control(pDX, IDC_MFCEDIT_DEC_INFILE, m_DecryptInFileName);
    DDX_Control(pDX, IDC_MFCEDIT_DEC_OUTFILE, m_DecryptOutFileName);
    DDX_Control(pDX, IDC_EDIT_MSG_TEXT, m_Messagetext);
    DDX_Control(pDX, IDC_EDIT_USER, m_UserName);
    DDX_Control(pDX, IDC_PROGRESS_DEC_KEY_USAGE, m_DecryptFileUsage);
    DDX_Control(pDX, IDC_PROGRESS_ENC_KEY_USEAGE, m_EncryptFileUsage);
}

BEGIN_MESSAGE_MAP(CTOTAL_ENCRYPTIONDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
    ON_BN_CLICKED(IDC_BUTTON_GEN_KEY, &CTOTAL_ENCRYPTIONDlg::OnClickedButtonGenKey)
    ON_BN_CLICKED(IDC_BUTTON_DEC_FILE, &CTOTAL_ENCRYPTIONDlg::OnClickedButtonDecFile)
    ON_BN_CLICKED(IDC_BUTTON_DEC_MSG, &CTOTAL_ENCRYPTIONDlg::OnClickedButtonDecMsg)
    ON_BN_CLICKED(IDC_BUTTON_ENC_FILE, &CTOTAL_ENCRYPTIONDlg::OnClickedButtonEncFile)
    ON_BN_CLICKED(IDC_BUTTON_ENC_MSG, &CTOTAL_ENCRYPTIONDlg::OnClickedButtonEncMsg)
END_MESSAGE_MAP()

void EncryptTotal(unsigned char *MessageIn, unsigned int InLen, unsigned char *MessageOut, unsigned int &OutLen, 
    HANDLE KeyFile, LARGE_INTEGER &StartPosition, BOOL flStrongEncryption)
{
    DWORD dwSize;
    unsigned char *CurrentKey = new unsigned char [InLen];
    OutLen = 0;
    if (CurrentKey)
    {
        SetFilePointer(KeyFile, StartPosition.LowPart, &StartPosition.HighPart, FILE_BEGIN);
        ReadFile(KeyFile, CurrentKey, InLen, &dwSize, NULL);
        for (unsigned int i = 0; i < InLen; i ++)
        {
            MessageOut[i] = MessageIn[i] ^ CurrentKey[i];
        }
        if (flStrongEncryption)
        {
            SetFilePointer(KeyFile, StartPosition.LowPart, &StartPosition.HighPart, FILE_BEGIN);
            memset(CurrentKey, 0, InLen);

            WriteFile(KeyFile, CurrentKey, InLen, &dwSize, NULL);
            StartPosition.QuadPart += InLen;
        }
        OutLen = InLen;
        delete CurrentKey;
    }

}
// CTOTAL_ENCRYPTIONDlg message handlers

BOOL CTOTAL_ENCRYPTIONDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// Add "About..." menu item to system menu.

	// IDM_ABOUTBOX must be in the system command range.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon

	// TODO: Add extra initialization here
    CString strTemp = (CString)theApp.szVideoFileName;
    m_VIdeoFileName.SetWindowTextW(strTemp);

    strTemp = (CString)theApp.szEncKeyFileName;
    m_EncryptKey.SetWindowTextW(strTemp);

    strTemp = (CString)theApp.szDecKeyFileName;
    m_DecryptKey.SetWindowTextW(strTemp);

    strTemp = (CString)theApp.szEncInFileName;
    m_EncryptInFileName.SetWindowTextW(strTemp);

    strTemp = (CString)theApp.szEncOutFileName;
    m_EncryptOutFileName.SetWindowTextW(strTemp);

    strTemp = (CString)theApp.szDecInFileName;
    m_DecryptInFileName.SetWindowTextW(strTemp);

    strTemp = (CString)theApp.szDecOutFileName;
    m_DecryptOutFileName.SetWindowTextW(strTemp);

	return TRUE;  // return TRUE  unless you set the focus to a control
}

void CTOTAL_ENCRYPTIONDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.

void CTOTAL_ENCRYPTIONDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // device context for painting

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Draw the icon
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

// The system calls this function to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR CTOTAL_ENCRYPTIONDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



//void CAboutDlg::OnButtonGenKey()
//{
//    // TODO: Add your command handler code here
//}


void CTOTAL_ENCRYPTIONDlg::OnClickedButtonGenKey()
{
    // TODO: Add your control notification handler code here
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
            HANDLE hVideoHandle = CreateFile(FileName, GENERIC_READ, 0, NULL, OPEN_EXISTING, NULL, NULL);
            HANDLE hKeyHandle = CreateFile(KeyFileName, GENERIC_READ | GENERIC_WRITE, FILE_SHARE_READ, NULL, CREATE_ALWAYS,FILE_ATTRIBUTE_NORMAL, NULL);
            if ((hVideoHandle != NULL) && (hKeyHandle != NULL))
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
            if (hVideoHandle != NULL)
                CloseHandle(hVideoHandle);
            if (hKeyHandle != NULL)
                CloseHandle(hKeyHandle);
        }
    }

}


BOOL CTOTAL_ENCRYPTIONDlg::DestroyWindow()
{
    // TODO: Add your specialized code here and/or call the base class
    CString strTemp;
    m_VIdeoFileName.GetWindowTextW(strTemp);
    CStringA strTempA = (CStringA)strTemp; 
    strcpy(theApp.szVideoFileName,strTempA);

    m_DecryptKey.GetWindowTextW(strTemp);
    strTempA = (CStringA)strTemp; 
    strcpy(theApp.szDecKeyFileName,strTempA);

    m_EncryptKey.GetWindowTextW(strTemp);
    strTempA = (CStringA)strTemp; 
    strcpy(theApp.szEncKeyFileName,strTempA);

    m_EncryptInFileName.GetWindowTextW(strTemp);
    strTempA = (CStringA)strTemp; 
    strcpy(theApp.szEncInFileName,strTempA);

    m_EncryptOutFileName.GetWindowTextW(strTemp);
    strTempA = (CStringA)strTemp; 
    strcpy(theApp.szEncOutFileName,strTempA);

    m_DecryptInFileName.GetWindowTextW(strTemp);
    strTempA = (CStringA)strTemp; 
    strcpy(theApp.szDecInFileName,strTempA);

    m_DecryptOutFileName.GetWindowTextW(strTemp);
    strTempA = (CStringA)strTemp; 
    strcpy(theApp.szDecOutFileName,strTempA);

    return CDialogEx::DestroyWindow();
}


void CTOTAL_ENCRYPTIONDlg::OnClickedButtonDecFile()
{
    // TODO: Add your control notification handler code here
}


void CTOTAL_ENCRYPTIONDlg::OnClickedButtonDecMsg()
{
    // TODO: Add your control notification handler code here
    CString FileName;
    m_EncryptKey.GetWindowTextW(FileName);
    CStringA FileNameA = (CStringA)FileName;
    strcpy(theApp.szDecKeyFileName,FileNameA);
    HANDLE hKeyHandle = CreateFile(FileName, GENERIC_READ | GENERIC_WRITE, FILE_SHARE_READ, NULL, CREATE_ALWAYS,FILE_ATTRIBUTE_NORMAL, NULL);
    if (hKeyHandle != NULL)
    {
        CString strMsg;
        CStringA strMsgA;
        m_Messagetext.GetWindowTextW(strMsg);
        strMsgA = strMsg;
        unsigned char *EncryptedMessage = new unsigned char[strMsgA.GetLength()+12];
        if (EncryptedMessage)
        {
            char *EncryptedMessage2 = new char[(strMsgA.GetLength()+12)*2+1];
            if (EncryptedMessage2)
            {
            
                unsigned int szLenOut;
                *(DWORD*)EncryptedMessage = strMsgA.GetLength();
                *(DWORD*)&EncryptedMessage[4] = theApp.PosEncKey.LowPart;
                *(DWORD*)&EncryptedMessage[8] = theApp.PosEncKey.HighPart;
                memcpy(&EncryptedMessage[12], strMsgA,strMsgA.GetLength());
                EncryptTotal(&EncryptedMessage[12], strMsgA.GetLength(), &EncryptedMessage[12], szLenOut, hKeyHandle, theApp.PosEncKey, FALSE);
                for (int i =0; i < strMsgA.GetLength()+12; i++)
                {
                    sprintf(&EncryptedMessage2[i*2],"%02x", EncryptedMessage[i]);
                }

                strMsg = "=";
                strMsg += EncryptedMessage2;
                strMsg += "=";
                m_Messagetext.SetWindowTextW(strMsg);
                delete EncryptedMessage2;
            }
            delete EncryptedMessage;
        }
        CloseHandle(hKeyHandle);
    }
}


void CTOTAL_ENCRYPTIONDlg::OnClickedButtonEncFile()
{
    // TODO: Add your control notification handler code here
}


void CTOTAL_ENCRYPTIONDlg::OnClickedButtonEncMsg()
{
    // TODO: Add your control notification handler code here
    CString FileName;
    m_EncryptKey.GetWindowTextW(FileName);
    CStringA FileNameA = (CStringA)FileName;
    strcpy(theApp.szEncKeyFileName,FileNameA);
    HANDLE hKeyHandle = CreateFile(FileName, GENERIC_READ | GENERIC_WRITE, FILE_SHARE_READ, NULL, CREATE_ALWAYS,FILE_ATTRIBUTE_NORMAL, NULL);
    if (hKeyHandle != NULL)
    {
        CString strMsg;
        CStringA strMsgA;
        m_Messagetext.GetWindowTextW(strMsg);
        strMsgA = strMsg;
        unsigned char *EncryptedMessage = new unsigned char[strMsgA.GetLength()+12];
        if (EncryptedMessage)
        {
            char *EncryptedMessage2 = new char[(strMsgA.GetLength()+12)*2+1];
            if (EncryptedMessage2)
            {
            
                unsigned int szLenOut;
                *(DWORD*)EncryptedMessage = strMsgA.GetLength();
                *(DWORD*)&EncryptedMessage[4] = theApp.PosEncKey.LowPart;
                *(DWORD*)&EncryptedMessage[8] = theApp.PosEncKey.HighPart;
                memcpy(&EncryptedMessage[12], strMsgA,strMsgA.GetLength());
                EncryptTotal(EncryptedMessage, strMsgA.GetLength()+12, EncryptedMessage, szLenOut, hKeyHandle, theApp.PosEncKey, FALSE);
                for (int i =0; i < strMsgA.GetLength()+12; i++)
                {
                    sprintf(&EncryptedMessage2[i*2],"%02x", EncryptedMessage[i]);
                }
                strMsg = "=";
                strMsg += EncryptedMessage2;
                strMsg += "=";
                m_Messagetext.SetWindowTextW(strMsg);
                delete EncryptedMessage2;
            }
            delete EncryptedMessage;
        }
        CloseHandle(hKeyHandle);
    }
}
