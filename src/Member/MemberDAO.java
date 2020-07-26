package Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from member";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("member_id");
				String pwd = rs.getString("password");
				String name = rs.getString("name");
				String regdate = rs.getString("regdate");
				MemberVO memberVO = new MemberVO(id, pwd, name, regdate);
				membersList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}

	public void addMember(MemberVO m) {
		try {
			conn = dataFactory.getConnection();
			System.out.println("가입다오왔니?");
			String id = m.getMember_id();
			String pwd = m.getPassword();
			String name = m.getName();
			String query = "INSERT INTO member(member_id, password, name) VALUES(?, ?, ?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			System.out.println(id + pwd + name);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int delMember(MemberVO m) {
		int rst = 0;
		try {
		conn = dataFactory.getConnection();
			String query = "select * from member where member_id=? and password=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMember_id());
			pstmt.setString(2, m.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = 1;
				System.out.println("아디비번확인완료!");
			String query2 = "delete from member where member_id=? and password=?";
			System.out.println(query2);
			pstmt = conn.prepareStatement(query2);
			pstmt.setString(1, m.getMember_id());
			pstmt.setString(2, m.getPassword() );
			System.out.println("에스큐지운다!!!!!!");
			pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("rst: "+rst);
		return rst;
	}
	
	public String logInCheck(MemberVO m) {
		String rst="";
		try {
			conn = dataFactory.getConnection();
				String query = "select * from member where member_id=? and password=?";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, m.getMember_id());
				pstmt.setString(2, m.getPassword());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					MemberVO vo = new MemberVO();
					vo.setName(rs.getString("name"));
					rst = vo.getName();
					System.out.println(rst);
					System.out.println("아디비번확인완료!");
				}
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("loginCheck rst: "+rst);
			return rst;
		}

	public void editPwd(MemberVO m, String newPassword) {
		String password = m.getPassword();
		String nPwd = newPassword;
		try {
			conn = dataFactory.getConnection();
			String query = "update member set password=? where password=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nPwd);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int idCheck(String id) {
		int rst = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from member where member_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = 1;
				System.out.println("중복이야야아");
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	public int pwdCheck(String password) {
		int rst = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from member where password=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = 1;
				System.out.println("비번확인완료!");
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

}
