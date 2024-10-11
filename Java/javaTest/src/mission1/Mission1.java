package mission1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Mission1 {
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>자바 환경정보</title>");
        sb.append("<style>");
        sb.append("table { border-collapse: collapse; width: 100%; }");
        sb.append("th, td { border: solid 1px #000;}");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<table>");
        sb.append("<tr>");
        sb.append("<th>키</th>");
        sb.append("<th>값</th>");
        sb.append("</tr>");

        for (Object key : System.getProperties().keySet()) {
            sb.append("<tr>");
            sb.append("<td>").append(key).append("</td>");
            sb.append("<td>").append(System.getProperty((String) key)).append("</td>");
            sb.append("</tr>");
        }

        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");

        try {
            File file = new File("C:\\Users\\1\\Desktop\\property.html");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
