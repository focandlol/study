package mission1;

class Pager {
    final int contentsPerPage = 10;
    final int maxIdx = 10;
    long totalCount;
    long totalPage;
    String pageNav;

    Pager(long totalCount) {
        this.totalCount = totalCount;
    }

    public String html(long pageIndex) {
        StringBuilder strPageNav = new StringBuilder();
        this.totalPage = totalCount / contentsPerPage;
        if (totalCount % contentsPerPage != 0) {
            this.totalPage++;
        }

        strPageNav.append("<a href='#'>[처음]</a>\n");
        strPageNav.append("<a href='#'>[이전]</a>\n\n");

        for (int i = (int)(pageIndex - 1) / maxIdx * maxIdx + 1; i <= Math.min(((int)(pageIndex - 1) / maxIdx + 1) * maxIdx, this.totalPage); i++) {
            strPageNav.append("<a href='#'");
            if (i == pageIndex) {
                strPageNav.append(" class='on'");
            }
            strPageNav.append(">").append(i).append("</a>\n");

        }

        strPageNav.append("\n<a href='#'>[다음]</a>\n");
        strPageNav.append("<a href='#'>[마지막]</a>");
        this.pageNav = strPageNav.toString();
        return this.pageNav;
    }
}
public class aa {
    public static void main(String[] args) {
        long totalCount = 127;
        long pageIndex = 30;

        Pager pager = new Pager(totalCount);
        System.out.println(pager.html(pageIndex));
    }
}