package GuestBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/guestbook")
public class GuestbookController extends HttpServlet {
	GuestbookDAO guestbookDAO;
	
	public void init() throws ServletException {
		guestbookDAO = new GuestbookDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String command = request.getParameter("command");
		System.out.println("command:   "+command);

		if (command.equals("/listGuestbooks.do")) {
		System.out.println("첫번째로 왔어!");

		
		}else if (command.equals("/delGuestbook.do")) {
            String password = request.getParameter("password");
           
            if( guestbookDAO.delGuestbook(password) == 1) {
                request.setAttribute("command", "ok");
               RequestDispatcher dis = request.getRequestDispatcher("/static/Member/delEditGuestbookOk.jsp");
               dis.forward(request, response);
               return;
            }else {
               request.setAttribute("command", "notok");
               RequestDispatcher dis = request.getRequestDispatcher("/static/Member/delEditGuestbookOk.jsp");
               dis.forward(request, response);
               return;
            }
     
		} else if(command.equals("/checkGuest.do")){               //수정하기 페이지
	         String password = request.getParameter("password");
	         if(guestbookDAO.checkGuest(password)==null) {
	        	 request.setAttribute("command", "notok");
	               RequestDispatcher dis = request.getRequestDispatcher("/static/Member/delEditGuestbookOk.jsp");
	               dis.forward(request, response);
	               return;
	            }else {
	               GuestbookVO guestInfo = guestbookDAO.checkGuest(password);  
	                request.setAttribute("guestInfo", guestInfo); 
	               RequestDispatcher dis = request.getRequestDispatcher("/static/Member/editGuestbookForm.jsp");
	               dis.forward(request, response);
	               return;
	            }
//	         //기존 입력된 메시지 정보 표시
//	     	RequestDispatcher dispatcher = request.getRequestDispatcher("/editGuestbookForm.jsp");
//	         dispatcher.forward(request, response);
		} else {
			System.out.println("혹시 여기로 왔니???");
	    	G_paging gp = new G_paging();	
	    	int count = guestbookDAO.getAllCount(); // 전체 카운트 수 받아야함
	    	gp.setTotalCount(count);
	    	int page = 1;
	    	if(request.getParameter("page")!=null) {
	    		page = Integer.parseInt(request.getParameter("page"));
	    		System.out.println("ㅁㅁㅁㅁㅁ");
	    	}
	    	gp.setPage(page);
	    	List<GuestbookVO> guestbooksList = guestbookDAO.selectAllpage(gp);
	    	gp.setPage(page);
	    	System.out.println(guestbooksList.size()+" :   여기까지는 왔니???");
	    	System.out.println("1111111");
	    	request.setAttribute("guestbooksList", guestbooksList);
	    	request.setAttribute("paging", gp);
	    	
	    	RequestDispatcher dispatchar = request.getRequestDispatcher("/static/Member/listGuestbooks.jsp");
	    	dispatchar.forward(request, response);


		}
	
	}

}
