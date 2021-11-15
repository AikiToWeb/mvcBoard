package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class NoticeInProcAct implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		NoticeList notice = new NoticeList();	
		// 사용자가 입력한 공지사항 글의 정보들을 저장할 인스턴스 생성
		notice.setNl_kind(request.getParameter("nl_kind"));
		notice.setNl_title(request.getParameter("nl_title").trim().replace("'", "''"));
		notice.setNl_content(request.getParameter("nl_content").trim().replace("'", "''"));
		// 등록할 게시글 데이터를 받아와 notice인스턴스에 담음(매개변수로 사용하기 편하기 때문)
		
		NoticeInProcSvc noticeInProcSvc = new NoticeInProcSvc();
		// DB를 제외한 비즈니스 로직을 처리하기 위한 인스턴스 생성
		int result = noticeInProcSvc.noticeInsert(notice);
		
		if (result == 0) {	// 글 등록에 실패했으면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 등록에 실패했습니다.\n다시 시도해 주십시오.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);	// dispatch가 아닌 sendRedirect 방식으로 이동한다는 설정
		forward.setPath("/mvcBoard/notice_view.brd?cpage=1&idx=" + result);
		
		return forward;
	}
}
	
