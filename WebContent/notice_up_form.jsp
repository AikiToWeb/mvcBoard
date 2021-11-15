<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");	// 페이징 정보가 들어있는 인스턴스
NoticeList notice = (NoticeList)request.getAttribute("notice");	// 게시글 정보가 들어있는 인스턴스

String args = "?cpage=" + pageInfo.getCpage();
if (pageInfo.getKeyword() != null && !pageInfo.getKeyword().equals("")) {
	args += "&schtype=" + pageInfo.getSchtype() + "&keyword=" + pageInfo.getKeyword();
}	// 검색 조건이 있을 경우에만 쿼리 스트링으로 만들어 줌
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>공지사항 수정 폼</h2>
<form name="frmNotice" action="notice_up_proc.brd" method="post">
글종류 : 
<input type="radio" name="nl_kind" value="a" id="kinda"
	<% if (notice.getNl_kind().equals("a")) { %> checked="checked" <% } %> /><label for="kinda">단순공지</label>
<input type="radio" name="nl_kind" value="b" id="kindb" 
	<% if (notice.getNl_kind().equals("b")) { %> checked="checked" <% } %> /><label for="kindb">이벤트</label>
<input type="radio" name="nl_kind" value="c" id="kindc" 
	<% if (notice.getNl_kind().equals("c")) { %> checked="checked" <% } %> /><label for="kindc">상품관련</label>
<input type="radio" name="nl_kind" value="d" id="kindd" 
	<% if (notice.getNl_kind().equals("d")) { %> checked="checked" <% } %> /><label for="kindd">보도자료</label>
<br />
글제목 : <input type="text" name="nl_title" value="<%=notice.getNl_title() %> "/>
<br />
글내용 : <textarea name="nl_content" cols="50" rows="5"><%=notice.getNl_content() %></textarea>
<br /><br />
<input type="button" value="목록으로" onclick="location.href='notice_list.brd<%=args %>';"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="공지사항 수정" />
</form>
</body>
</html>
