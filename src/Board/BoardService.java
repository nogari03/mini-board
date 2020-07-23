package Board;

import java.util.List;

public interface BoardService {

    List<BoardVO> getBoardListWithPaging(int currentPage);

    void insertBoard(BoardVO vo);
    BoardVO getBoardContent(String article_no);
    void deleteContent(int parseInt);
    void updateContent(BoardVO vo);

    int getBoardCnt();
    void updateBoardCnt(String article_no);

    int checkPassword(String article_no, String password);
    int uploadFile(String file,String article_no);
}
