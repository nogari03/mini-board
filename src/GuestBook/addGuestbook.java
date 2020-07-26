package GuestBook;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/addGuestbook")
public class addGuestbook extends HttpServlet {
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
		GuestbookDAO guestbookDAO = new GuestbookDAO();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		File currentDirPath = new File("/Users/sylvia.p");
//		DiskFileItemFactory factory = new DiskFileItemFactory();
		GuestbookVO vo = new GuestbookVO();
		String uploadPath = request.getRealPath("upload");
		try {
			
			MultipartRequest multi = new MultipartRequest( // MultipartRequest 인스턴스 생성(cos.jar의 라이브러리)
					request, 
					uploadPath, // 파일을 저장할 디렉토리 지정
					1024 * 1024 * 3, // 첨부파일 최대 용량 설정(bite) / 10KB / 용량 초과 시 예외 발생
					"utf-8", // 인코딩 방식 지정
					new DefaultFileRenamePolicy() 
			);
			System.out.println("파일경로::  "  +uploadPath);// 중복 파일 처리(동일한 파일명이 업로드되면 뒤에 숫자 등을 붙여 중복 회피)
		
		String name = multi.getParameter("guest_name");
		String pass = multi.getParameter("password");
		String message = multi.getParameter("message");
		String fileName = multi.getFilesystemName("fileName");
		String orgfileName = multi.getOriginalFileName("orgfileName");
		System.out.println("파라미터 받은거!!"+multi.getFilesystemName("fileName")+multi.getOriginalFileName("orgfileName"));
		System.out.println("브이오에 넣을거!!" +fileName+"////"+orgfileName);
		GuestbookVO guestbookVO = new GuestbookVO("", name, pass, message, fileName);
		guestbookDAO.addGuestbook(guestbookVO);
		System.out.println("브이오에넣었어!!");
		} catch(Exception e) {
			e.getStackTrace();
		}
		System.out.println("jsp 가기 직전!!!!!");
		response.sendRedirect("/MiniProject/addGuestbookOk.jsp");

	}

}
