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

This file contains a summary of what you will find in each of the files that
make up your TOTAL_ENCRYPTION application.

TOTAL_ENCRYPTION.vcxproj
    This is the main project file for VC++ projects generated using an application wizard.
    It contains information about the version of Visual C++ that generated the file, and
    information about the platforms, configurations, and project features selected with the
    application wizard.

TOTAL_ENCRYPTION.vcxproj.filters
    This is the filters file for VC++ projects generated using an Application Wizard. 
    It contains information about the association between the files in your project 
    and the filters. This association is used in the IDE to show grouping of files with
    similar extensions under a specific node (for e.g. ".cpp" files are associated with the
    "Source Files" filter).

TOTAL_ENCRYPTION.h
    This is the main header file for the application.  It includes other
    project specific headers (including Resource.h) and declares the
    CTOTAL_ENCRYPTIONApp application class.

TOTAL_ENCRYPTION.cpp
    This is the main application source file that contains the application
    class CTOTAL_ENCRYPTIONApp.

TOTAL_ENCRYPTION.rc
    This is a listing of all of the Microsoft Windows resources that the
    program uses.  It includes the icons, bitmaps, and cursors that are stored
    in the RES subdirectory.  This file can be directly edited in Microsoft
    Visual C++. Your project resources are in 1033.

res\TOTAL_ENCRYPTION.ico
    This is an icon file, which is used as the application's icon.  This
    icon is included by the main resource file TOTAL_ENCRYPTION.rc.

res\TOTAL_ENCRYPTION.rc2
    This file contains resources that are not edited by Microsoft
    Visual C++. You should place all resources not editable by
    the resource editor in this file.


/////////////////////////////////////////////////////////////////////////////

The application wizard creates one dialog class:

TOTAL_ENCRYPTIONDlg.h, TOTAL_ENCRYPTIONDlg.cpp - the dialog
    These files contain your CTOTAL_ENCRYPTIONDlg class.  This class defines
    the behavior of your application's main dialog.  The dialog's template is
    in TOTAL_ENCRYPTION.rc, which can be edited in Microsoft Visual C++.


/////////////////////////////////////////////////////////////////////////////

Other standard files:

StdAfx.h, StdAfx.cpp
    These files are used to build a precompiled header (PCH) file
    named TOTAL_ENCRYPTION.pch and a precompiled types file named StdAfx.obj.

Resource.h
    This is the standard header file, which defines new resource IDs.
    Microsoft Visual C++ reads and updates this file.

TOTAL_ENCRYPTION.manifest
	Application manifest files are used by Windows XP to describe an applications
	dependency on specific versions of Side-by-Side assemblies. The loader uses this
	information to load the appropriate assembly from the assembly cache or private
	from the application. The Application manifest  maybe included for redistribution
	as an external .manifest file that is installed in the same folder as the application
	executable or it may be included in the executable in the form of a resource.
/////////////////////////////////////////////////////////////////////////////
