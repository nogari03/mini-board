package Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginController extends HttpServlet {

	public LoginController() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {

	}

	public void destroy() {

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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String command = request.getParameter("command");
		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setMember_id(member_id);
		vo.setPassword(password);
		String rst= dao.logInCheck(vo) ;
		if (!rst.equals("")) {
			session.setAttribute("member_id", member_id);
			session.setAttribute("name", rst);
			System.out.println(vo.getName());
			response.sendRedirect("/static/Member/logInOk.jsp");

		} else if (rst.equals("")) {
			out.print("<html><body>");
			out.print("떙! 틀렸지롱 로그인 불가<br>");
			out.print("<a href ='/static/Member/logIn.jsp'>로그인 화면 다시 간당</a>");
			out.print("</body></html>");
			System.out.println("초기화면으로 튕김");

		}

	}
}
