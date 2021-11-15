<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
// 목록 화면에서 사용할 페이징 정보들과 검색 관련 정보들을 담은 pageInfo 인스턴스 생성

ArrayList<NoticeList> noticeList = (ArrayList<NoticeList>)request.getAttribute("noticeList");
// 목록 화면에서 보여줄 게시글의 목록을  ArrayList<NoticeList> 인스턴스로 생성

String args = "", schargs = "";
if (pageInfo.getSchtype() == null || pageInfo.getKeyword() == null) {
	pageInfo.setSchtype("");	pageInfo.setKeyword("");
	// 검색 관련 정보가 null인 경우 빈 문자열로 변경시킴
} else if (!pageInfo.getKeyword().equals("") && !pageInfo.getSchtype().equals("")){
	schargs = "&schtype=" + pageInfo.getSchtype() + "&keyword=" + pageInfo.getKeyword();
}
args = "&cpage=" + pageInfo.getCpage() + schargs;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#brd th { border-bottom:3px black double; }
#brd td { border-bottom:1px black solid; }
</style>
</head>
<body>
<h2>공지사항 목록</h2>
<table width="700" cellpadding="5" cellspacing="0" id="brd">
<tr>
<th width="8%">번호</th><th width="15%">글종류</th><th width="*">제목</th>
<th width="15%">작성일</th><th width="10%">조회수</th>
</tr>
<%
if (noticeList.size() > 0 && pageInfo.getRcnt() > 0) {	// 보여줄 게시글 목록이 있으면
	int num = pageInfo.getRcnt() - (pageInfo.getPsize() * (pageInfo.getCpage() - 1));
	for (int i = 0 ; i < noticeList.size() ; i++) {
		NoticeList nl = noticeList.get(i);	// noticeList에 들어있는 NoticeList 인스턴스를 추출하여 nl이라는 인스턴스로 생성
		
		String kind = "단순공지";
		if (nl.getNl_kind().equals("b"))		kind = "이벤트";
		else if (nl.getNl_kind().equals("c"))	kind = "상품관련";
		else if (nl.getNl_kind().equals("d"))	kind = "보도자료";
		
		String title = nl.getNl_title();
		if (title.length() > 30)	title = title.substring(0, 28) + "...";
		String lnk = "<a href='notice_preview.brd?idx=" + nl.getNl_idx() + args + "'>";
%>
<tr align="center" onmouseover="this.style.background='#efefef';" onmouseout="this.style=background='';">
<td><%=num %></td>
<td><%=kind %></td>
<td align="left"><%=lnk + title + "</a>" %></td>
<td><%=nl.getNl_date().substring(2,10).replace('-','.') %></td>
<td><%=nl.getNl_readcnt() %></td>
</tr>
<% 
	num--;
	}
} else {
	out.println("<tr><td colspan='5' align='center'>검색 결과가 없습니다.</td></tr>");
}
%>
</table>
<br />
<table width="700" cellpadding="5" cellspacing="0">
<tr>
<td width="600" align="center">
<%
if (noticeList.size() > 0 && pageInfo.getRcnt() > 0) {	// 보여줄 게시글 목록이 있으면 페이지 번호를 보여줌
	String pageLink = "notice_list.brd?cpage=";
	if (pageInfo.getCpage() == 1) {
		out.println("[&lt;&lt;]&nbsp;&nbsp;[&lt;]&nbsp;&nbsp;");
	} else {
		out.print("<a href='" + pageLink + "1" + schargs + "'>[&lt;&lt;]</a>&nbsp;&nbsp;");
		out.println("<a href='" + pageLink + (pageInfo.getCpage() - 1) + schargs + "'>[&lt;]</a>&nbsp;&nbsp;");
	}
	
	for (int i = 1, j = pageInfo.getSpage() ; i <= pageInfo.getBsize() && j <= pageInfo.getEpage() ; i++, j++) {
	// i : 루프를 돌릴 횟수를 검사하는 용도의 변수, j : 페이지 번호 출력용 변수
		if (pageInfo.getCpage() == j) {
			out.print("&nbsp;<strong>" + j + "</strong>&nbsp;");
		} else {
			out.print("&nbsp;<a href='" + pageLink + j + schargs + "'>" + j + "</a>&nbsp;");
		}
	}
	
	if (pageInfo.getCpage() == pageInfo.getPcnt()) {
		out.println("&nbsp;&nbsp;[&gt;]&nbsp;&nbsp;[&gt;&gt;]");
	} else {
		out.print("&nbsp;&nbsp;<a href='" + pageLink + (pageInfo.getCpage() + 1) + schargs + "'>[&gt;]</a>");
		out.println("&nbsp;&nbsp;<a href='" + pageLink + (pageInfo.getPcnt()) + schargs + "'>[&gt;&gt;]</a>");
	}
}
%>
</td>
<td width="*" align="right">
	<input type="button" value="글 등록" onclick="location.href='notice_in_form.brd';" />
</td>
</tr>
<form name="frmSch" method="get">
<tr><td colspan="2" align="center">
	<select name="schtype">
		<option value="title" <% if (pageInfo.getSchtype().equals("title")) { %>selected="selected" <% } %>>제목</option>
		<option value="content"<% if (pageInfo.getSchtype().equals("content")) { %>selected="selected" <% } %>>내용</option>
		<option value="tc"<% if (pageInfo.getSchtype().equals("tc")) { %>selected="selected" <% } %>>제목+내용</option>
	</select>
	<input type="text" name="keyword" value="<%=pageInfo.getKeyword() %>" />
	<input type="submit" value="검색" />
</td></tr>
</form>
</table>
</body>
</html>
