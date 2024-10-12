package mission1;

/**
 * 고경민
 */
public class Mission3 {
    static class Pager{
        long totalCount;
        long blockGasu = 10;
        long gasu = 10;

        public Pager(long totalCount) {
            this.totalCount = totalCount;
        }

        public String html(long pageIndex){
            StringBuilder sb = new StringBuilder();
            sb.append("<a href='#'>[처음]</a>\n")
                    .append("<a href='#'>[이전]</a>\n\n");

            long start = pageIndex/blockGasu * blockGasu + 1;
            for(long i=start; i<Math.min(start + blockGasu,totalCount/gasu+2); i++){
                sb.append("<a href='#'");
                if(i == pageIndex) sb.append(" class='on'");
                sb.append(">").append(i).append("</a>\n");
            }
            sb.append("\n")
                    .append("<a href='#'>[다음]</a>\n")
                    .append("<a href='#'>[마지막]</a>");
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        long totalCount = 127;
        long pageIndex = 12;

        Pager pager = new Pager(totalCount);
        System.out.println(pager.html(pageIndex));
    }
}
