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
// TOTAL_ENCRYPTION.h : main header file for the PROJECT_NAME application
//

#pragma once

#ifndef __AFXWIN_H__
	#error "include 'stdafx.h' before including this file for PCH"
#endif

#include "resource.h"		// main symbols


// CTOTAL_ENCRYPTIONApp:
// See TOTAL_ENCRYPTION.cpp for the implementation of this class
//

class CTOTAL_ENCRYPTIONApp : public CWinApp
{
public:
	CTOTAL_ENCRYPTIONApp();
    char szIniFileName[_MAX_PATH];
    char szVideoFileName[_MAX_PATH];
    char szEncKeyFileName[_MAX_PATH];
    char szDecKeyFileName[_MAX_PATH];

    char szEncInFileName[_MAX_PATH];
    char szEncOutFileName[_MAX_PATH];
    char szDecInFileName[_MAX_PATH];
    char szDecOutFileName[_MAX_PATH];

// Overrides
public:
	virtual BOOL InitInstance();

// Implementation

	DECLARE_MESSAGE_MAP()
};

extern CTOTAL_ENCRYPTIONApp theApp;