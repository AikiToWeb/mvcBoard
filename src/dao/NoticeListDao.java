package dao;

import static db.JdbcUtil.*;	
import java.util.*;
import javax.sql.*;
import java.sql.*;
import vo.*;

public class NoticeListDao {
	private static NoticeListDao noticeListDao;
	private Connection conn;

	private NoticeListDao() {}
	
	public static NoticeListDao getInstance() {
		if (noticeListDao == null)	noticeListDao = new NoticeListDao();
		return noticeListDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int getNoticeCount(String where) {
	// 조건에 맞는 게시글의 총 개수를 리턴하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		int rcnt = 0;	// 리턴할 값(레코드 개수)을 저장할 변수로 값이 없을 경우를 대비하여 0으로 초기화
		
		try {
			stmt = conn.createStatement();
			String sql = "select count(*) from t_notice_list " + where; 
			rs = stmt.executeQuery(sql);
			rs.next();	rcnt = rs.getInt(1);	
			
		} catch(Exception e) {
			System.out.println("NoticeListDao 클래스의 getNoticeCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return rcnt;
	}
	
	public ArrayList<NoticeList> getNoticeList(String where, int cpage, int psize) {
	// 목록에서 보여줄 게시글 목록을 ArrayList<NoticeList>로 리턴하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<NoticeList> noticeList = new ArrayList<NoticeList>();
		// ArrayList의 size() 메소드를 이용하면 ArrayList 안에 데이터가 없을 경우 알아낼 수 있기 때문에 생성한 채로 작업해도 됨
		NoticeList notice = null;	// 하나의 게시글 정보들을 저장할 인스턴스로 ArrayList에 저장될 객체
		
		try {
			stmt = conn.createStatement();
			int snum = (cpage - 1) * psize;
			String sql = "select nl_idx, nl_kind, nl_title, nl_readcnt, nl_date from t_notice_list " + 
				where +	" order by nl_idx desc limit " + snum + ", " + psize;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				notice = new NoticeList();	
				// ArrayList에 저장할 NoticeList형 인스턴스 생성
				
				notice.setNl_idx(rs.getInt("nl_idx"));
				notice.setNl_kind(rs.getString("nl_kind"));
				notice.setNl_title(rs.getString("nl_title"));
				notice.setNl_readcnt(rs.getInt("nl_readcnt"));
				notice.setNl_date(rs.getString("nl_date"));
				
				noticeList.add(notice);
				// rs에 들어있는 레코드들을 NoticeList형 인스턴스로 생성한 후 ArrayList인 noticeList에 차례대로 저장
			}
			
		} catch(Exception e) {
			System.out.println("NoticeListDao 클래스의 getNoticeList() 메소드 오류");
			e.printStackTrace();
		}
		
		return noticeList;
	}
}
	
