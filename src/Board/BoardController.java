package Board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board")
public class BoardController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }
    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        BoardService service = new BoardService();

        String article_no = request.getParameter("article_no");
        String writer_id = request.getParameter("writer_id");
        String writer_name = request.getParameter("writer_name");
        String title = request.getParameter("title");
        String password = request.getParameter("password");
        String content = request.getParameter("content");

        System.out.println(article_no);
        String command = request.getParameter("command");

        BoardVO vo = new BoardVO(article_no,writer_id,writer_name,title,password,content);

        List<BoardVO> list = null;

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher;

        dispatcher = context.getRequestDispatcher("/static/board.jsp");
//        list = service.printBoardlist();

        int pageNo = 1;

        if(request.getParameter("page")!=null){
            pageNo = Integer.parseInt(request.getParameter("page"));
        }
        PagingVO paging = new PagingVO();
        paging.setPage(pageNo);
        paging.setTotalCount(12); //임의

        list = service.selectBoard(pageNo);

        if("write".equals(command)) {
            dispatcher = context.getRequestDispatcher("/static/boardForm.jsp");
        }else if("add".equals(command)){
            service.addBoard(vo);
            dispatcher = context.getRequestDispatcher("/static/board.jsp");
        }else if("select".equals(command)){
            vo = service.searchById(article_no);
            request.setAttribute("title",vo.getTitle());
            request.setAttribute("content",vo.getContent());
            dispatcher = context.getRequestDispatcher("/static/boardForm.jsp");
        }

        request.setAttribute("paging",paging);
        request.setAttribute("command",command);
        request.setAttribute("list",list);
        dispatcher.forward(request, response);
    }
}
