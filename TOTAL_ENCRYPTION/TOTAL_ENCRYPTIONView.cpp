
// TOTAL_ENCRYPTIONView.cpp : implementation of the CTOTAL_ENCRYPTIONView class
//

#include "stdafx.h"
// SHARED_HANDLERS can be defined in an ATL project implementing preview, thumbnail
// and search filter handlers and allows sharing of document code with that project.
#ifndef SHARED_HANDLERS
#include "TOTAL_ENCRYPTION.h"
#endif

#include "TOTAL_ENCRYPTIONDoc.h"
#include "TOTAL_ENCRYPTIONView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CTOTAL_ENCRYPTIONView

IMPLEMENT_DYNCREATE(CTOTAL_ENCRYPTIONView, CView)

BEGIN_MESSAGE_MAP(CTOTAL_ENCRYPTIONView, CView)
	// Standard printing commands
	ON_COMMAND(ID_FILE_PRINT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &CTOTAL_ENCRYPTIONView::OnFilePrintPreview)
	ON_WM_CONTEXTMENU()
	ON_WM_RBUTTONUP()
END_MESSAGE_MAP()

// CTOTAL_ENCRYPTIONView construction/destruction

CTOTAL_ENCRYPTIONView::CTOTAL_ENCRYPTIONView()
{
	// TODO: add construction code here

}

CTOTAL_ENCRYPTIONView::~CTOTAL_ENCRYPTIONView()
{
}

BOOL CTOTAL_ENCRYPTIONView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	return CView::PreCreateWindow(cs);
}

// CTOTAL_ENCRYPTIONView drawing

void CTOTAL_ENCRYPTIONView::OnDraw(CDC* /*pDC*/)
{
	CTOTAL_ENCRYPTIONDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	// TODO: add draw code for native data here
}


// CTOTAL_ENCRYPTIONView printing


void CTOTAL_ENCRYPTIONView::OnFilePrintPreview()
{
#ifndef SHARED_HANDLERS
	AFXPrintPreview(this);
#endif
}

BOOL CTOTAL_ENCRYPTIONView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// default preparation
	return DoPreparePrinting(pInfo);
}

void CTOTAL_ENCRYPTIONView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add extra initialization before printing
}

void CTOTAL_ENCRYPTIONView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add cleanup after printing
}

void CTOTAL_ENCRYPTIONView::OnRButtonUp(UINT /* nFlags */, CPoint point)
{
	ClientToScreen(&point);
	OnContextMenu(this, point);
}

void CTOTAL_ENCRYPTIONView::OnContextMenu(CWnd* /* pWnd */, CPoint point)
{
#ifndef SHARED_HANDLERS
	theApp.GetContextMenuManager()->ShowPopupMenu(IDR_POPUP_EDIT, point.x, point.y, this, TRUE);
#endif
}


// CTOTAL_ENCRYPTIONView diagnostics

#ifdef _DEBUG
void CTOTAL_ENCRYPTIONView::AssertValid() const
{
	CView::AssertValid();
}

void CTOTAL_ENCRYPTIONView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CTOTAL_ENCRYPTIONDoc* CTOTAL_ENCRYPTIONView::GetDocument() const // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CTOTAL_ENCRYPTIONDoc)));
	return (CTOTAL_ENCRYPTIONDoc*)m_pDocument;
}
#endif //_DEBUG


// CTOTAL_ENCRYPTIONView message handlers
