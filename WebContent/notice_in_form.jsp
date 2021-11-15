<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>공지사항 등록 폼</h2>
<form name="frmNotice" action="notice_in_proc.brd" method="post">
글종류 : <input type="radio" name="nl_kind" value="a" id="kinda" checked="checked" /><label for="kinda">단순공지</label>
<input type="radio" name="nl_kind" value="b" id="kindb" checked="checked" /><label for="kindb">이벤트</label>
<input type="radio" name="nl_kind" value="c" id="kindc" checked="checked" /><label for="kindc">상품관련</label>
<input type="radio" name="nl_kind" value="d" id="kindd" checked="checked" /><label for="kindd">보도자료</label>
<br />
글제목 : <input type="text" name="nl_title" />
<br />
글내용 : <textarea name="nl_content" cols="50" rows="5"></textarea>
<br /><br />
<input type="button" value="목록으로" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="공지사항 등록" />
</form>
</body>
</html>
