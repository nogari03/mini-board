package Board;

import java.util.List;

public class BoardService {

    private BoardDAO dao = new BoardDAO();


    public void insertBoard(BoardVO vo){
        dao.insertBoard(vo);
    }

    public BoardVO getBoardContent(String article_no){
        BoardVO vo= dao.getBoardContent(article_no);
        return vo;
    }

    public List<BoardVO> getBoardList() {
        List<BoardVO> list = dao.getBoardList();
        return list;
    } // no use

    public List<BoardVO> getBoardListWithPaging(int pageNo){
        List<BoardVO> list = dao.getBoardListWithPaging(pageNo);
        return list;
    }
    public void updateBoardCnt(String article_no){
        dao.updateBoardCnt(article_no);
    }

    public int getBoardCnt(){
        int result = dao.getBoardCnt();
        return result;
    }

    public void deleteContent(int article_no){
        dao.deleteContent(article_no);
    }
    public void updateContent(BoardVO vo){
        dao.updateContent(vo);
    }
    public int checkPassword(String article_no,String input){
        String dbPassword = dao.getPassword(article_no);
        if(dbPassword.equals(input)){
            return 1;
        }
        return 0;
    }
}
