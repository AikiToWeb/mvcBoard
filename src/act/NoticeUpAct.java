package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;
import svc.*;
import vo.*;

public class NoticeUpAct implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int	idx = Integer.parseInt(request.getParameter("idx"));		// 글번호(필수이므로 바로 형변환 가능)
		int cpage = Integer.parseInt(request.getParameter("cpage"));	// 페이지 번호
		String schtype = request.getParameter("schtype");	// 검색조건
		String keyword = request.getParameter("keyword");	// 검색어
		
		PageInfo pageInfo = new PageInfo();		// 페이징에 필요한 정보를 저장할 인스턴스 생성
		pageInfo.setCpage(cpage);
		pageInfo.setSchtype(schtype);
		pageInfo.setKeyword(keyword);

		NoticeUpSvc noticeUpSvc = new NoticeUpSvc();
		NoticeList notice = noticeUpSvc.getNoticeInfo(idx);
		// 수정할 하나의 게시글 정보를 NoticeList 클래스의 인스턴스로 받아 옴
		
		if (notice == null)	{	// 해당 게시글이 없으면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('존재하지 않는 게시글 입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		request.setAttribute("notice", notice);
		request.setAttribute("pageInfo", pageInfo);
		// up_form 파일로 가져갈 두 인스턴스를 request객체의 속성(attribute)으로 저장함
		
		ActionForward forward = new ActionForward();
		forward.setPath("/notice_up_form.jsp");
		// ActionForward 클래스의 redirect변수의 값을 true가 아닌 false로 유지함으로써 이동방식을 디스패치 방식으로 지정함
		// 디스패치 방식으로 이동해야 request객체를 이동할 파일로 넘길 수 있어 request안에 있는 속성들을 사용할 수 있음
		
		return forward;
	}
}
