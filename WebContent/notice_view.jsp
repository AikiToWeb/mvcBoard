<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.*"%>
<%
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	NoticeList noticeList = (NoticeList) request.getAttribute("noticeList");

	String args = "?cpage=" + pageInfo.getCpage();
	if (pageInfo.getKeyword() != null && !pageInfo.getKeyword().equals("")) {
		args += "&schtype=" + pageInfo.getSchtype() + "&keyword=" + pageInfo.getKeyword();
	} // 검색조건이 있을 경우에만 쿼리스트링으로 만들어 줌
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function callDel() {
	if (confirm("정말 삭제하시겠습니까?")) {
		location.href = "notice_del_proc.brd?nidx=<%=noticeList.getNl_idx()%>";
		}
	}
</script>
</head>
<body>
	<h2>게시글 보기</h2>
	<table width="700" cellpadding="5">
		<tr>
			<th width="20%">작성자</th>
			<td width="*"><%=noticeList.getNl_writer()%></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td><%=noticeList.getNl_title()%></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td><%=noticeList.getNl_content().replace("\r\n", "<br />")%></td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td><%=noticeList.getNl_date()%></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" value="글 수정"
				onclick="location.href='notice_up_form.brd<%=args%>&nidx=<%=noticeList.getNl_idx()%>';" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글 삭제"
				onclick="callDel();" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
				type="button" value="글 목록"
				onclick="location.href='notice_list.brd<%=args%>';" /></td>
		</tr>
	</table>
</body>
</html>
