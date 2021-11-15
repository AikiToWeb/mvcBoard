package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.net.*;
import svc.*;
import vo.*;

public class NoticePreviewAct implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int idx = Integer.parseInt(request.getParameter("idx"));		// 글번호(필수이므로 바로 형변환 가능)
		int cpage = Integer.parseInt(request.getParameter("cpage"));	// 페이지 번호
		String schtype = request.getParameter("schtype");	// 검색조건
		String keyword = request.getParameter("keyword");	// 검색어

		String args = "?idx=" + idx + "&cpage=" + cpage;
		if (schtype != null && keyword != null && !schtype.equals("") && !keyword.equals("")) {
			args += "&schtype=" + schtype + "&keyword=" + URLEncoder.encode(keyword, "UTF-8");
		}	// 조건은 있는 경우에만 쿼리스트링으로 연결시킴

		NoticePreviewSvc noticePreviewSvc = new NoticePreviewSvc();
		int result = noticePreviewSvc.readcntUpdate(idx);

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/mvcBoard/notice_view.brd" + args);

		return forward;
	}
}
