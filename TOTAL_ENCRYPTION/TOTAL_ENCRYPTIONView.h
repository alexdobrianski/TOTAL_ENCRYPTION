
// TOTAL_ENCRYPTIONView.h : interface of the CTOTAL_ENCRYPTIONView class
//

#pragma once


class CTOTAL_ENCRYPTIONView : public CView
{
protected: // create from serialization only
	CTOTAL_ENCRYPTIONView();
	DECLARE_DYNCREATE(CTOTAL_ENCRYPTIONView)

// Attributes
public:
	CTOTAL_ENCRYPTIONDoc* GetDocument() const;

// Operations
public:

// Overrides
public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// Implementation
public:
	virtual ~CTOTAL_ENCRYPTIONView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	afx_msg void OnFilePrintPreview();
	afx_msg void OnRButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnContextMenu(CWnd* pWnd, CPoint point);
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // debug version in TOTAL_ENCRYPTIONView.cpp
inline CTOTAL_ENCRYPTIONDoc* CTOTAL_ENCRYPTIONView::GetDocument() const
   { return reinterpret_cast<CTOTAL_ENCRYPTIONDoc*>(m_pDocument); }
#endif

