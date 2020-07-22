package Board;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    private Connection con;
    private PreparedStatement pstmt;
    private DataSource dataFactory;

    public BoardDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BoardVO> listBoard() {
        List<BoardVO> list = new ArrayList<>();
        try {
            con = dataFactory.getConnection();
            String query = "SELECT * FROM article JOIN article_content ON article.article_no=article_content.article_no";
            pstmt = con.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String article_no = rs.getString("article_no");
                String writer_id = rs.getString("writer_id");
                String writer_name = rs.getString("writer_name");
                String title = rs.getString("title");
                String password = rs.getString("password");
                String regdate = rs.getString("regdate");
                String moddate = rs.getString("moddate");
                int read_cnt = rs.getInt("read_cnt");

                BoardVO vo = new BoardVO();
                vo.setArticle_no(article_no);
                vo.setWriter_id(writer_id);
                vo.setWriter_name(writer_name);
                vo.setTitle(title);
                vo.setPassword(password);
                vo.setRegdate(regdate);
                vo.setModdate(moddate);
                vo.setRead_cnt(read_cnt);

                list.add(vo);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public BoardVO searchById(String article_no) {
        BoardVO vo = new BoardVO();
        try {
            con = dataFactory.getConnection();
            String query = "select ARTICLE.TITLE,ARTICLE_CONTENT.CONTENT from article, article_content where article.article_no = ? AND article_content.article_no = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, article_no);
            pstmt.setString(2, article_no);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");

                vo.setTitle(title);
                vo.setContent(content);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    public void addBoard(BoardVO vo) {
        System.out.println(vo.getArticle_no());
        System.out.println(vo.getContent());
        try {
            con = dataFactory.getConnection();
            String query = "insert all " +
                    "into ARTICLE values (?,?,?,?,?,sysdate,sysdate,0)" +
                    "into ARTICLE_CONTENT values(?,?)" +
                    "select * from DUAL";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, vo.getArticle_no());
            pstmt.setString(2, vo.getWriter_id()); // 여기 MemberVO로
            pstmt.setString(3, vo.getWriter_name()); // 여기 MemberVO로
            pstmt.setString(4, vo.getTitle());
            pstmt.setString(5, vo.getPassword());

            pstmt.setString(6, vo.getArticle_no());
            pstmt.setString(7, vo.getContent());

            ResultSet rs = pstmt.executeQuery();

            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BoardVO> selectBoard(int page) {
        List<BoardVO> list = new ArrayList<>();

        int startNum = (page-1)*10+1;
        int endNum = page*10;
        try {
            con = dataFactory.getConnection();


            // sql = select * from ( select rownum as row_num,article_no from article) where row_num >= 11 AND row_num <=20 // 성능 문제 야기
            // sql = select * from ( select rownum as row_num,article_no from article where row_num >= 11) where row_num <= 20 // this best
            String query = "select * from ( select ARTICLE_NO as row_num,article.* from article where ARTICLE_NO >= ?) where ARTICLE_NO <= ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, startNum);
            pstmt.setInt(2, endNum);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String article_no = rs.getString("row_num");
                String writer_id = rs.getString("writer_id");
                String writer_name = rs.getString("writer_name");
                String title = rs.getString("title");
                String password = rs.getString("password");
                String regdate = rs.getString("regdate");
                String moddate = rs.getString("moddate");
                int read_cnt = rs.getInt("read_cnt");

                BoardVO vo = new BoardVO();
                vo.setArticle_no(article_no);
                vo.setWriter_id(writer_id);
                vo.setWriter_name(writer_name);
                vo.setTitle(title);
                vo.setPassword(password);
                vo.setRegdate(regdate);
                vo.setModdate(moddate);
                vo.setRead_cnt(read_cnt);

                list.add(vo);
            }


            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
//    public void updateOrder(OrderVO vo){
//        try{
//            con = dataFactory.getConnection();
//            String query =
//                    "update orderitems set prod_id=?, quantity=?, item_price=? where order_num = ? AND prod_id= ? ";
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1,vo.getProd_id());
//            pstmt.setInt(2, Integer.parseInt(vo.getQuantity()));
//            pstmt.setInt(3, Integer.parseInt(vo.getItem_price()));
//            pstmt.setInt(4, Integer.parseInt(vo.getOrder_num()));
//            pstmt.setString(5,vo.getProd_id());
//
//            pstmt.executeQuery();
//            pstmt.close();
//            con.close();
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    public void deleteOrder(String id){
//        try
//        {
//            con = dataFactory.getConnection();
//            String query = "delete from orderitems where order_num = ?";
//            pstmt = con.prepareStatement(query);
//            pstmt.setString(1, id);
//            pstmt.executeQuery();
//
//            pstmt.close();
//            con.close();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }


