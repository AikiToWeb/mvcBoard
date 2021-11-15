package svc;

import static db.JdbcUtil.*;	// JdbcUtil 클래스의 멤버들을 자유롭게 사용하겠다는 의미
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeInProcSvc {
	public int noticeInsert(NoticeList notice) {
		int result = 0;
		Connection conn = getConnection();
		NoticeInProcDao noticeInProcDao = NoticeInProcDao.getInstance();
		noticeInProcDao.setConnection(conn);
		result = noticeInProcDao.noticeInsert(notice);
		
		if (result >= 1)	commit(conn);
		else				rollback(conn);
		close(conn);
		
		return result;
	}
}
