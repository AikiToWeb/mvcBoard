package dao;

import static db.JdbcUtil.*;	
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;

public class NoticeUpDao {
	private static NoticeUpDao noticeUpDao;
	private Connection conn;

	private NoticeUpDao() {}
	// 외부에서는 함부로 인스턴스를 생성하지 못하게 private으로 생성자를 선언함
	
	public static NoticeUpDao getInstance() {
	// 싱글톤 방식으로 인스턴스 낭비를 줄임
		if (noticeUpDao == null)	noticeUpDao = new NoticeUpDao();
		// 기존의 인스턴스가 살아있으면 새롭게 만들지 않고, 없을 경우에만 새로 생성하게 함 
		return noticeUpDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public NoticeList getNoticeInfo(int idx) {
	// 지정한 글번호(idx)에 해당하는 게시글 정보를 NoticeList형 인스턴스에 담아 리턴하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		NoticeList notice = null;	// 데이터가 없을 경우 null을 리턴하게 함
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from t_notice_list where nl_idx = " + idx;	/* 권한 체크를 위한 조건이 추가되어야 함 */
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				notice = new NoticeList();	// rs에 담긴 데이터들을 저장할 인스턴스 생성
				notice.setNl_idx(idx);
				notice.setNl_kind(rs.getString("nl_kind"));
				notice.setNl_title(rs.getString("nl_title"));
				notice.setNl_content(rs.getString("nl_content"));
				notice.setNl_readcnt(rs.getInt("nl_readcnt"));
				notice.setNl_date(rs.getString("nl_date"));
				notice.setAi_idx(rs.getInt("ad_idx"));
				
			}	 // rs가 비었으면 else 없이 그냥 notice에 null이 들어있는 상태로 리턴함
			
		} catch(Exception e) {
			System.out.println("NoticeUpDao 클래스의 getNoticeInfo() 메소드 오류");
			e.printStackTrace();
		}
		
		return notice;
	}
}
