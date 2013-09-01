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
// TOTAL_ENCRYPTIONDlg.h : header file
//

#pragma once


// CTOTAL_ENCRYPTIONDlg dialog
class CTOTAL_ENCRYPTIONDlg : public CDialogEx
{
// Construction
public:
    float flSizeEnc;
    float flSizeDec;

	CTOTAL_ENCRYPTIONDlg(CWnd* pParent = NULL);	// standard constructor

// Dialog Data
	enum { IDD = IDD_TOTAL_ENCRYPTION_DIALOG };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support


// Implementation
protected:
	HICON m_hIcon;

	// Generated message map functions
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()
public:
    CMFCEditBrowseCtrl m_VIdeoFileName;
    CButton m_GenKey;
    afx_msg void OnClickedButtonGenKey();
    virtual BOOL DestroyWindow();
    CButton m_DecryptFile;
    CButton m_DEcryptMessage;
    CButton m_EncryptFile;
    CButton m_EncryptMessage;
    CMFCEditBrowseCtrl m_DecryptKey;
    CMFCEditBrowseCtrl m_EncryptKey;
    CMFCEditBrowseCtrl m_EncryptInFileName;
    CMFCEditBrowseCtrl m_EncryptOutFileName;
    CMFCEditBrowseCtrl m_DecryptInFileName;
    CMFCEditBrowseCtrl m_DecryptOutFileName;
    CEdit m_Messagetext;
    CEdit m_UserName;
    CProgressCtrl m_DecryptFileUsage;
    CProgressCtrl m_EncryptFileUsage;
    afx_msg void OnClickedButtonDecFile();
    afx_msg void OnClickedButtonDecMsg();
    afx_msg void OnClickedButtonEncFile();
    afx_msg void OnClickedButtonEncMsg();
//    BOOL m_StrongEncryption;
//    afx_msg void OnClickedCheckStrong();
    CButton m_StrongEncryption;
    CButton m_StoreInRegistry;
    afx_msg void OnClickedCheckStrong();
    afx_msg void OnClickedButtonHistory();
    afx_msg void OnClickedButtonEditHistory();
    afx_msg void OnClickedButtonDeleteHistory();
};
