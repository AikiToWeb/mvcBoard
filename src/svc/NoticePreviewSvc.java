package svc;

import static db.JdbcUtil.*;	// JdbcUtil 클래스의 멤버들을 자유롭게 사용하겠다는 의미
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticePreviewSvc {
	public int readcntUpdate(int idx) {
		int result = 0;
		Connection conn = getConnection();
		NoticePreviewDao noticePreviewDao = NoticePreviewDao.getInstance();
		noticePreviewDao.setConnection(conn);
		result = noticePreviewDao.readcntUpdate(idx);
		
		if (result == 1)	commit(conn);
		else				rollback(conn);
		close(conn);
		
		return result;
	}
}
