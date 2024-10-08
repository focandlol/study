package mission1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Mission1 {
    public static void main(String[] args){
        try{
            File file = new File("C:\\Users\\1\\Desktop\\property.html");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<title>자바 환경정보</title>");
            writer.write("<style>");
            writer.write("table { border-collapse: collapse; width: 0%;}");
            writer.write("th, td { border: solid 1px #000;}");
            writer.write("</style>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<table>");
            writer.write("<tr>");
            writer.write("<th>키</th>");
            writer.write("<th></th>");
            writer.write("</tr>");

            for (Object key : System.getProperties().keySet()) {
                writer.write("<tr>");
                writer.write("<td>" + key + "</td>");
                writer.write("<td>" + System.getProperty((String) key) + "</td>");
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("</body>");
            writer.write("</html>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
