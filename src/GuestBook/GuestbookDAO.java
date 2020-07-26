package GuestBook;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GuestbookDAO {
	public static final int MESSAGE_NOUNT_PER_PAGE = 3;
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;


	 public GuestbookVO checkGuest(String password) {
	      GuestbookVO guestInfo = null;
	      
	      try {
	         conn = dataFactory.getConnection();
	         String query = "select * from GUESTBOOK_MESSAGE where password = ?";
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, password);
	         System.out.println(query);
	         ResultSet rs = pstmt.executeQuery();
	         rs.next();
	         String message_id = rs.getString("message_id");
	         String guest_name = rs.getString("guest_name");
	         String password2 = rs.getString("password");
	         String message = rs.getString("message");
	         guestInfo = new GuestbookVO(message_id, guest_name, password2, message);
	         pstmt.close();
	         conn.close();
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }
	      
	      return guestInfo;
	   }
	   
	 public void editGuest(GuestbookVO guestbookVO) {
         System.out.println("2******************");

         try{
            conn = dataFactory.getConnection();
            String guest_name = guestbookVO.getGuest_name();
            String message = guestbookVO.getMessage();
            String message_id = guestbookVO.getMessage_id();
            String fileName = guestbookVO.getFileName();
            
            String query = "update GUESTBOOK_MESSAGE set guest_name=?, message=?, fileName=? where message_id=?";
            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, guest_name);
            System.out.println(guest_name);
            pstmt.setString(2, message);
            System.out.println(message);
            pstmt.setString(3, fileName);
            pstmt.setString(4, message_id);
            
            System.out.println(message_id);
            System.out.println("1111111111111******************");
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
         }catch(SQLException e) {
            e.printStackTrace();
         }   
      }


	public GuestbookDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int delGuestbook(String password) {
	      int result = 0;
	      try {
	         conn = dataFactory.getConnection();
	         String query = "select * from GUESTBOOK_MESSAGE where password=?";
	         System.out.println(query);
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, password);
	         ResultSet rs = pstmt.executeQuery();
	         System.out.println("del  조회중");
	      while(rs.next()) {
	            result=1;
	            System.out.println("삭제 진행");
	            String query1 = "delete from GUESTBOOK_MESSAGE where password=?";
	            pstmt = conn.prepareStatement(query1);
	            pstmt.setString(1, password);
	            pstmt.executeUpdate();
	      }
	         pstmt.close();
	         conn.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return result;
	   }


	public void addGuestbook(GuestbookVO m) {
		try {
			conn = dataFactory.getConnection();
			String guest_name = m.getGuest_name();
			String password = m.getPassword();
			String message = m.getMessage();
			String fileName = m.getFileName();
			String query = "INSERT INTO GUESTBOOK_MESSAGE(Message_id, guest_name, password, message, filename) VALUES(guestbook_seq.nextval, ?, ?, ?, ?)";
			System.out.println(m.toString());

			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guest_name);
			pstmt.setString(2, password);
			pstmt.setString(3, message);
			pstmt.setString(4, fileName);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<GuestbookVO> selectAllpage(G_paging gp) {
		int page = gp.getPage();
		System.out.println("UIpage:   "+   page);
	  int startNum = gp.getStartNum();
	   int endNum = gp.getEndNum();
		String query = "SELECT * from (select * from (select ROWNUM as row_num, guestbook_message.* from guestbook_message) where row_num >=? )where row_num <=?";
		List<GuestbookVO> list = new ArrayList<GuestbookVO>();
		try {System.out.println("try start!!!!!!!!!!!!"+startNum+"/////"+endNum);
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(query);
			while (rs.next()) {
				System.out.println("while start!!!!!!!!!!!!");
				GuestbookVO vo = new GuestbookVO();
				vo.setMessage_id(rs.getString("message_id"));
				vo.setGuest_name(rs.getString("guest_name"));
				vo.setPassword(rs.getString("password"));
				vo.setMessage(rs.getString("message"));
				vo.setFileName(rs.getString("filename"));
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list.size()+":        다오에서확인하는리스트 사이즈");
		return list;
	}
	public int getAllCount() {
		String query = "SELECT COUNT(*) as count FROM guestbook_message";	
		int count = 0;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if (rs.next()) {
				count=rs.getInt("count");
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("확인해보자 카운트 수:   "+count);
		return count;
	}
}
	
	
	
	
	
	
	
	

