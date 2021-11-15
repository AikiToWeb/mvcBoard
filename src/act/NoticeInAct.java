package act;

import javax.servlet.http.*;
import java.util.*;
import vo.*;

public class NoticeInAct implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/notice_in_form.jsp");
		
		return forward;
	}

}
