package Board;

import java.util.List;

public class BoardService {

    private BoardDAO dao = new BoardDAO();

    public List<BoardVO> printBoardlist() {
        List<BoardVO> list = dao.listBoard();
        return list;
    }

    public void addBoard(BoardVO vo){
        dao.addBoard(vo);
    }

    public BoardVO searchById(String id){
        BoardVO vo= dao.searchById(id);
        return vo;
    }

    public List<BoardVO> selectBoard(int pageNo){
        List<BoardVO> list = dao.selectBoard(pageNo);
        return list;
    }
}
