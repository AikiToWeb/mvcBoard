package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeUpSvc {
	public NoticeList getNoticeInfo(int idx) {
		Connection conn = getConnection();
		NoticeUpDao noticeUpDao = NoticeUpDao.getInstance();
		noticeUpDao.setConnection(conn);
		NoticeList notice = noticeUpDao.getNoticeInfo(idx);
		
		close(conn);
		
		return notice;
		}
}