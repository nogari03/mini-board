package Board;

public class BoardVO {

    private String article_no;
    private String writer_id;
    private String writer_name;
    private String title;
    private String password;
    private String regdate;
    private String moddate;
    private int read_cnt;

    private String content;



    public BoardVO() {
    }

    public BoardVO(String article_no, String writer_id, String writer_name, String title, String password, String content) {
        this.article_no = article_no;
        this.writer_id = writer_id;
        this.writer_name = writer_name;
        this.title = title;
        this.password = password;
        this.content = content;
    }

    public String getArticle_no() {
        return article_no;
    }

    public void setArticle_no(String article_no) {
        this.article_no = article_no;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public String getWriter_name() {
        return writer_name;
    }

    public void setWriter_name(String writer_name) {
        this.writer_name = writer_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getModdate() {
        return moddate;
    }

    public void setModdate(String moddate) {
        this.moddate = moddate;
    }

    public int getRead_cnt() {
        return read_cnt;
    }

    public void setRead_cnt(int read_cnt) {
        this.read_cnt = read_cnt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "article_no='" + article_no + '\'' +
                ", writer_id='" + writer_id + '\'' +
                ", writer_name='" + writer_name + '\'' +
                ", title='" + title + '\'' +
                ", password='" + password + '\'' +
                ", regdate='" + regdate + '\'' +
                ", moddate='" + moddate + '\'' +
                ", read_cnt=" + read_cnt +
                ", content='" + content + '\'' +
                '}';
    }
}