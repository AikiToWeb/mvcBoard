package svc;

import static db.JdbcUtil.*;	
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeListSvc {
	public int getNoticeCount(String where) {
	// 목록에서 사용할 전체 게시글 개수를 리턴하는 메소드
		int rcnt = 0;	// 전체 게시글 개수를 저장할 변수이자 리턴할 값
		Connection conn = getConnection();
		NoticeListDao noticeListDao = NoticeListDao.getInstance();
		noticeListDao.setConnection(conn);
		rcnt = noticeListDao.getNoticeCount(where);
		close(conn);
		
		return rcnt;
	}
	
	public ArrayList<NoticeList> getNoticeList(String where, int cpage, int psize) {
	// 목록 화면에서 보여줄 게시글 목록을 NoticeList형 인스턴스만 저장할 수 있는 ArrayList로 리턴하는 메소드
		ArrayList<NoticeList> noticeList = null;
		// 게시글 목록을 저장할  ArrayList로 NoticeList형 인스턴스만 저장함
		Connection conn = getConnection();
		NoticeListDao noticeListDao = NoticeListDao.getInstance();
		noticeListDao.setConnection(conn);
		noticeList = noticeListDao.getNoticeList(where, cpage, psize);
		close(conn);
		
		return noticeList;
	}
}
