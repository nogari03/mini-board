package GuestBook;

import Board.BoardVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GuestBookDAO {

        private Connection con;
        private PreparedStatement pstmt;
        private DataSource dataFactory;

        private static final String GUESTBOOK_INSERT = "insert into guestbook_message values (guest_seq.nextval, ?, ?, ?)";
        private static final String GUESTBOOK_SELECT_PAGING = "";
        private static final String GUESTBOOK_UPDATE = "update guestbook_message set guest_name=?,password=?,message=? where message_id=?";
        private static final String GUESTBOOK_DELETE = "delete * from guestbook_message where message_id=?";

        public GuestBookDAO() {
            try {
                Context ctx = new InitialContext();
                Context envContext = (Context) ctx.lookup("java:/comp/env");
                dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void insertGuestBook(GuestBookVO vo) {
            System.out.println("==> Insert GuestBook");
            try {
                con = dataFactory.getConnection();
                pstmt = con.prepareStatement(GUESTBOOK_INSERT);
                pstmt.setString(1, vo.getGuest_name());
                pstmt.setString(2, vo.getPassword());
                pstmt.setString(3, vo.getMessage());

                ResultSet rs = pstmt.executeQuery();

                rs.close();
                pstmt.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
