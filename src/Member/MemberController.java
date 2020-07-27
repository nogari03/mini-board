package Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO memberDAO = new MemberDAO();
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
//		System.out.println("action:" + action);
		if (command.equals("check.do")) { // 회원가입 컨트롤
			System.out.println("가시");
			String member_id = request.getParameter("member_id");
			int check = memberDAO.idCheck(member_id);
			if (check == 1) {
				boolean idDup = true;
//				request.setAttribute("idDup", idDup);
				out.print(1);
//			}else if(password.equals(confirm)){ 
//				boolean pwdDup = true;
//			request.setAttribute("pwdDup", pwdDup);
//			response.sendRedirect("./addMember.jsp");	
			} else if (check == 0) {

				out.print(0);

			}
		}
		else if (command.equals("addMember.do")) { // 회원가입 컨트롤
			MemberVO memberVO = new MemberVO();
			String member_id = request.getParameter("member_id");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
				memberVO.setMember_id(member_id);
				memberVO.setName(name);
				memberVO.setPassword(password);
				System.out.println(member_id + name + password);
				memberDAO.addMember(memberVO);
				System.out.println("가끝");
				request.setAttribute("member_id", member_id);
				System.out.println(name);
				request.setAttribute("name", name);
				RequestDispatcher dis = request.getRequestDispatcher("/static/Member/addMember.jsp");
				dis.forward(request, response);
//				response.sendRedirect("/MiniProject/addMemberOk.jsp");
//				request.setAttribute("msg", "addMember");// 초기 화면 페이지에 alert창 보내기
//				nextPage = "/member.jsp"; // 임시 초기 페이지 주소
		} else if (command.equals("delMember.do")) {// 회원탈퇴 컨트롤
			String member_id = request.getParameter("member_id"); // 삭제 요청시 아이디, 비밀번호 받음
			String password = request.getParameter("password");
			MemberVO memberVO = new MemberVO();
			MemberDAO dao = new MemberDAO();
			memberVO.setMember_id(member_id);
			memberVO.setPassword(password);
			int rst = dao.delMember(memberVO);
			if (rst == 0) {
				out.print("<html><body>");
				out.print("탈퇴 실패<br>");
				out.print("아이디와 비밀번호를 확인해주세요<br>");
				out.print("<a href ='/static/Member/delMember.jsp'>다시입력하기</a>");
				out.print("</body></html>");
				System.out.println("왔다감---00");
			} else if (rst == 1) {
				out.print("<html><body>");
				out.print("탈퇴처리 되었습니다");
				out.print("<a href ='/static/main.jsp'>메인화면으로</a>");
				out.print("</body></html>");
				System.out.println("왔다감---11");
			}
//			request.setAttribute("msg", "delMember");// 초기 화면 페이지에 alert창 보내기
//			nextPage = "/member.jsp"; // 임시 초기 페이지 주소

		} else if (command.equals("editPwd.do")) { // 암호변경
			MemberDAO dao = new MemberDAO();
			MemberVO memberVO = new MemberVO();
			String password = request.getParameter("password");
			String newPassword = request.getParameter("newPassword");
			int rst = dao.pwdCheck(password);
			if (rst == 0) {
				out.print("<html><body>");
				out.print("비밀번호 입력오류<br>");
				out.print("<a href ='/static/Member/editPwd.jsp'>로그인 화면으로</a>");
				out.print("</body></html>");
			} else if (rst == 1) {
				memberVO.setPassword(password);
				dao.editPwd(memberVO, newPassword);
				out.print("<html><body>");
				out.print("비밀번호가 변경되었습니다<br>");
				out.print("<a href ='/static/Member/logOut.jsp'>초기화면으로 돌아가기</a>");
				out.print("</body></html>");
//	         response.sendRedirect("./member.jsp");
			}
//	        	 request.setAttribute("msg", "notPassword");
//	        	 request.setAttribute("msg", "updatePwd"); // 초기 화면 페이지에 alert창 보내기
//	         
//	         nextPage="/member.jsp"; // 임시 초기 페이지 주소
		}
	}
}