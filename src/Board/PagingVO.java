package Board;

public class PagingVO {
    private int page = 1;
    private int totalCount;
    private int beginPage;
    private int endPage;
    private int displayRow = 10;
    private int disPlayPage = 10;
    boolean prev;
    boolean next;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getDisplayRow() {
        return displayRow;
    }

    public void setDisplayRow(int displayRow) {
        this.displayRow = displayRow;
    }

    public int getDisPlayPage() {
        return disPlayPage;
    }

    public void setDisPlayPage(int disPlayPage) {
        this.disPlayPage = disPlayPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public PagingVO(){
        endPage = ((int)Math.ceil(page/(double)disPlayPage))*disPlayPage;
        System.out.println("endPage:"+ endPage);

        beginPage = endPage - (disPlayPage - 1);
        System.out.println("beginPage:"+ beginPage);

        int totalPage = (int)Math.ceil(totalCount/(double)displayRow);

        if(totalPage<endPage){
            endPage = totalPage;
            next = false;
        }else{
            next = true;
        }
        prev = (beginPage==1)? false: true;
        System.out.println("endPage :"+ endPage);
        System.out.println("totalPage : "+ totalPage);
    }

    @Override
    public String toString() {
        return "PagingVO{" +
                "page=" + page +
                ", totalCount=" + totalCount +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", displayRow=" + displayRow +
                ", disPlayPage=" + disPlayPage +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}
