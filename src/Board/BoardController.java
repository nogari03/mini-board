package Board;

import Common.PagingVO;

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

        //필터 적용시 삭제
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        BoardService service = new BoardService();

        String row_num = request.getParameter("row_num");
        String article_no = request.getParameter("article_no");
        String writer_id = request.getParameter("writer_id");
        String writer_name = request.getParameter("writer_name");
        String title = request.getParameter("title");
        String password = request.getParameter("password");
        String content = request.getParameter("content");

        String command = request.getParameter("command");

        BoardVO vo = new BoardVO(row_num,article_no,writer_id,writer_name,title,password,content);

        List<BoardVO> list = null;

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher;

        dispatcher = context.getRequestDispatcher("/static/board.jsp");

        //paging
        PagingVO paging = new PagingVO();
        paging.setTotalCount(service.getBoardCnt());

        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        paging.setCurrentPage(currentPage);

        list = service.getBoardListWithPaging(currentPage);

        paging.setEndPage( ((int) Math.ceil(paging.getCurrentPage() / (double) paging.getDisplayPage())) * paging.getDisplayPage() );	//Math.ceil : 소수점 이하를 올림한다
        paging.setBeginPage( paging.getEndPage() - (paging.getDisplayPage() - 1) );
        paging.setTotalPage( (int) Math.ceil(paging.getTotalCount() / (double) paging.getDisplayRow()) );
        if (paging.getEndPage() > paging.getTotalPage()) {
            paging.setEndPage(paging.getTotalPage());
        }

        //command check
        if("add".equals(command)){ // 글 추가
            service.insertBoard(vo);

        }else if("get".equals(command)){ // 글 읽기
            service.updateBoardCnt(article_no);
            vo = service.getBoardContent(article_no);
            request.setAttribute("vo",vo);
            dispatcher = context.getRequestDispatcher("/static/boardForm.jsp");

        }else if("delete".equals(command)) { // 글 삭제
            if (service.checkPassword(article_no, password) == 0) {
//                response.sendRedirect(request.getHeader("referer"));
                return;
            }
            service.deleteContent(Integer.parseInt(article_no));

        }else if("update".equals(command)){ // 글 수정
            service.updateContent(vo);

        }else if("write".equals(command)) {   // 글작성 페이지로 이동
                dispatcher = context.getRequestDispatcher("/static/boardForm.jsp");

        }else if("updateForm".equals(command)) { // 수정 페이지로 이동
            if(service.checkPassword(article_no,password)==0){
//                response.sendRedirect(request.getHeader("referer"));
                return;
            }
            vo = service.getBoardContent(article_no);
            request.setAttribute("vo",vo);
            dispatcher = context.getRequestDispatcher("/static/boardForm.jsp");

        }else if("updateCheck".equals(command)){ // 수정 비밀번호 체크 페이지
            request.setAttribute("article_no",article_no);
            dispatcher = context.getRequestDispatcher("/static/checkForm.jsp");

        }else if("deleteCheck".equals(command)){ // 삭제 비밀번호 체크 페이지
            request.setAttribute("article_no",article_no);
            dispatcher = context.getRequestDispatcher("/static/checkForm.jsp");
        }

        request.setAttribute("paging",paging);
        request.setAttribute("command",command);
        request.setAttribute("list",list);
        dispatcher.forward(request, response);
    }
}
