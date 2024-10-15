package mission1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 고경민
 */
public class Mission1 {
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("<style>")
                .append("table { border-collapse: collapse; width: 100%; }")
                .append("th, td { border: solid 1px #000; }")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<h1>자바 환경정보</h1>")
                .append("<table>")
                .append("<tr>")
                .append("<th>키</th>")
                .append("<th>값</th>")
                .append("</tr>");
        for (Object key : System.getProperties().keySet()) {
            sb.append("<tr>")
                    .append("<td>").append(key).append("</td>")
                    .append("<td>").append(System.getProperty((String) key)).append("</td>")
                    .append("</tr>");
        }
        sb.append("</table>")
                .append("</body>")
                .append("</html>");

        try {
            File file = new File("C:\\Users\\1\\Desktop\\pr.html");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}