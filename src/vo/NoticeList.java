package vo;

public class NoticeList {
// 공지사항에서 하나의 게시글을 저장할 클래스
	private int nl_idx, nl_readcnt, ai_idx;
	private String nl_kind, nl_title, nl_content, nl_date;
	
	public int getNl_idx() {
		return nl_idx;
	}
	public void setNl_idx(int nl_idx) {
		this.nl_idx = nl_idx;
	}
	public int getNl_readcnt() {
		return nl_readcnt;
	}
	public void setNl_readcnt(int nl_readcnt) {
		this.nl_readcnt = nl_readcnt;
	}
	public int getAi_idx() {
		return ai_idx;
	}
	public void setAi_idx(int ai_idx) {
		this.ai_idx = ai_idx;
	}
	public String getNl_kind() {
		return nl_kind;
	}
	public void setNl_kind(String nl_kind) {
		this.nl_kind = nl_kind;
	}
	public String getNl_title() {
		return nl_title;
	}
	public void setNl_title(String nl_title) {
		this.nl_title = nl_title;
	}
	public String getNl_content() {
		return nl_content;
	}
	public void setNl_content(String nl_content) {
		this.nl_content = nl_content;
	}
	public String getNl_date() {
		return nl_date;
	}
	public void setNl_date(String nl_date) {
		this.nl_date = nl_date;
	}
	
}
