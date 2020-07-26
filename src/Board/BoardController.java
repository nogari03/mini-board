package Board;

import Common.PagingVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        response.setCharacterEncoding("utf-8");

        BoardService service = new BoardServiceImpl();

        String article_no = request.getParameter("article_no");
        String password = request.getParameter("password");

        String command = request.getParameter("command");
        PrintWriter out = response.getWriter();

        BoardVO vo;

        List<BoardVO> list = null;

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher;

        dispatcher = context.getRequestDispatcher("/static/Board/board.jsp");

        //command check


        if("get".equals(command)){ // 글 읽기
            service.updateBoardCnt(article_no);
            vo = service.getBoardContent(article_no);
            request.setAttribute("vo",vo);
            dispatcher = context.getRequestDispatcher("/static/Board/boardForm.jsp");

        }else if("delete".equals(command)) { // 글 삭제
            if (service.checkPassword(article_no, password) == 0) {
                response.sendRedirect(request.getHeader("referer"));
                return;
            }
            service.deleteContent(Integer.parseInt(article_no));

//        }else if("update".equals(command)){ // 글 수정
//            //file
//            String file = multi.getFilesystemName("file");
//            service.uploadFile(file,temp_article_no);
//            System.out.println("file upload check");
//            vo = new BoardVO(temp_article_no,temp_title,temp_content);
//            service.updateContent(vo);

        }else if("write".equals(command)) {   // 글작성 페이지로 이동
                dispatcher = context.getRequestDispatcher("/static/Board/boardForm.jsp");

        }else if("updateForm".equals(command)) { // 수정 페이지로 이동
            if(service.checkPassword(article_no,password)==0){
                out.print("<script>");
                out.print("alert('비밀번호가 일치하지 않습니다');");
                out.print("history.back();");
                out.print("</script>");
                response.sendRedirect(request.getHeader("referer"));
                return;
            }
            vo = service.getBoardContent(article_no);
            request.setAttribute("vo",vo);
            dispatcher = context.getRequestDispatcher("/static/Board/boardForm.jsp");

        }else if("updateCheck".equals(command)){ // 수정 비밀번호 체크 페이지
            request.setAttribute("article_no",article_no);
            dispatcher = context.getRequestDispatcher("/static/Board/checkForm.jsp");

        }else if("deleteCheck".equals(command)){ // 삭제 비밀번호 체크 페이지
            request.setAttribute("article_no",article_no);
            dispatcher = context.getRequestDispatcher("/static/Board/checkForm.jsp");
        }
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

        request.setAttribute("paging",paging);
        request.setAttribute("command",command);
        request.setAttribute("list",list);
        dispatcher.forward(request, response);
    }
}
