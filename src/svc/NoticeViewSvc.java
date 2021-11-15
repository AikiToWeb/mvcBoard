package svc;

import static db.JdbcUtil.*;	
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeViewSvc {
	public NoticeList getNoticeInfo(int idx) {
		Connection conn = getConnection();
		NoticeViewDao noticeViewDao = NoticeViewDao.getInstance();
		noticeViewDao.setConnection(conn);
		NoticeList notice = noticeViewDao.getNoticeInfo(idx);
		
		close(conn);
		
		return notice;
	}
}
