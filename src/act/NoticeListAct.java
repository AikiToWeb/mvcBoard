package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class NoticeListAct implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int cpage = 1, psize = 5, bsize = 5, rcnt = 0, pcnt = 0 , spage = 0, epage = 0;
		// 현재 페이지 번호, 페이지 크기, 블록 크기, 게시글 개수, 페이지 개수, 시작페이지, 종료페이지를 저장할 변수들

		if (request.getParameter("cpage") != null)	cpage = Integer.parseInt(request.getParameter("cpage"));
		// cpage 값이 있을 경우 int형으로 형변환하여 사용
		
		String schtype = request.getParameter("schtype");	// 검색조건(제목, 내용, 제목+내용)
		String keyword = request.getParameter("keyword");	// 검색어
		String where = "";	// 쿼리의 where절을 저장할 변수
		if (keyword != null && !keyword.equals("")) {		// keyword(검색어)가 존재한다면 
			if (schtype.equals("tc")) {
				where = " where nl_title like '%" + keyword + "%' nl_content like '%" + keyword + "%' ";
			} else {
				where = " where nl_" + schtype + " like '%" + keyword + "%' ";
			}
		}
		
		NoticeListSvc noticeListSvc = new NoticeListSvc();
		rcnt = noticeListSvc.getNoticeCount(where);
		// 전체 레코드(게시글) 개수를 구함 - 검색 조건이 있을 경우 그 조건에 맞는 레코드들의 개수를 구함
		ArrayList<NoticeList> noticeList = noticeListSvc.getNoticeList(where, cpage, psize);
		// 목록 화면에서 보여줄 게시글 목록을 NoticeList형 인스턴스만 담을 수 있는 ArrayList로 받아옴
		
		pcnt = rcnt / psize;
		if (rcnt % psize > 0) 	pcnt++;				// 전체 페이지 개수이면서 마지막 페이지 번호
		spage = (cpage - 1) / bsize * bsize + 1;	// 블록 시작 페이지 번호
		epage = spage + bsize - 1;
		if (epage > pcnt)	epage = pcnt;			// 블록 종료 페이지 번호

		PageInfo pageInfo = new PageInfo();
		pageInfo.setCpage(cpage);	pageInfo.setBsize(bsize);		pageInfo.setEpage(epage);
		pageInfo.setPcnt(pcnt);		pageInfo.setPsize(psize);		pageInfo.setRcnt(rcnt);
		pageInfo.setSpage(spage);	pageInfo.setKeyword(keyword);		pageInfo.setSchtype(schtype);
		// 목록 화면에서 페이징과 검색에 필요한 정보들을 pageInfo 인스턴스에 담음
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("noticeList", noticeList);
		// 페이징 정보 인스턴스와 게시글 목록 ArrayList 인스턴스를 request의 속성(attribute)에 저장한 채 디스패치 방식으로 이동
		
		ActionForward forward = new ActionForward();
		forward.setPath("/notice_list.jsp");
		
		return forward;
	}
}
