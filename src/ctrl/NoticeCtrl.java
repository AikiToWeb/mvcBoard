package ctrl;

import java.io.*;
import javax.servlet.*;		// RequestDispatcher 객체 사용을 위한 import
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;	// request, response, session 객체를 사용하기 위해 import
import act.*;
import vo.*;

@WebServlet("*.brd")	// 공지사항 관련 모든 요청을 처리하는 서블릿 클래스
public class NoticeCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeCtrl() {
        super();
    }
   
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// get과 post 두 가지 모두의 요청을 처리하는 메소드
    	request.setCharacterEncoding("utf-8");
    	String requestUri = request.getRequestURI();	
    	String contextPath = request.getContextPath();	// mvcBoard
    	String command = requestUri.substring(contextPath.length());	//
    	
    	ActionForward forward = null;
    	Action action = null;
    	
    	switch (command) {
    	case "/notice_list.brd" :		// 게시글 목록 요청
    		action = new NoticeListAct();
    		break;
    	case "/notice_in_form.brd" :	// 게시글 등록 폼 요청
    		action = new NoticeInAct();
    		break;
    	case "/notice_in_proc.brd" :	// 게시글 등록 처리 요청
    		action = new NoticeInProcAct();
    		break;
    	case "/notice_view.brd" :		// 게시글 보기 화면 요청
    		action = new NoticeViewAct();
    		break;
    	case "/notice_up_form.brd" :	// 게시글 수정 폼 요청
    		break;
    	case "/notice_up_proc.brd" :	// 게시글 수정 처리 요청
    		break;
    	case "/notice_del_proc.brd" :	// 게시글 삭제 처리 요청
    		break;
    	}
    	
    	try {
    		forward = action.execute(request, response);
    		// 처리 및 실행 후 이동할 경로와 방법을 받아옴
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	if (forward != null) {
    		if (forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		} else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
