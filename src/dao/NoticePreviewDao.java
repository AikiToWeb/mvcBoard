package dao;

import static db.JdbcUtil.*;
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;

public class NoticePreviewDao {
	private static NoticePreviewDao noticePreviewDao;
	private Connection conn;

	private NoticePreviewDao() {}

	public static NoticePreviewDao getInstance() {
		if (noticePreviewDao == null)	noticePreviewDao = new NoticePreviewDao();
		return noticePreviewDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int readcntUpdate(int idx) {
	// 특정 게시글의 조회수를 1증가 시키는 메소드
		Statement stmt = null;	// 쿼리 실행용 인스턴스
		int result = 0;		// 쿼리(update) 결과 저장용 변수

		try {
			stmt = conn.createStatement();
			String sql = "update t_notice_list set nl_readcnt = nl_readcnt + 1 where nl_idx = " + idx;
			result = stmt.executeUpdate(sql);

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}

		return result;
	}
}
