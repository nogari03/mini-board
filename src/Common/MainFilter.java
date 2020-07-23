//package Common;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class MainFilter implements Filter {
//
//    private String encoding;
//
//    @Override
//    public void init(FilterConfig config) throws ServletException {
//        encoding = config.getInitParameter("encoding");
//
//        if(encoding==null){
//            encoding="utf-8";
//        }
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        request.setCharacterEncoding(encoding);
//        response.setCharacterEncoding(encoding);
//        response.setContentType("text/html;charset=utf-8");
//
//        HttpServletRequest httpRequest= (HttpServletRequest) request;
//        HttpSession session = httpRequest.getSession(false);
//
//        boolean login = false;
//
//        if(session!=null){
//            if(session.getAttribute("member")!=null){
//                login=true;
//            }
//        }
//
//        if(login){
//            chain.doFilter(request,response);
//        }else{
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/static/main.jsp");
//            dispatcher.forward(request,response);
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
