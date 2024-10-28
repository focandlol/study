package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberService {
    public static int dbInsert(JSONArray jsonArray){
        System.out.println("memberService");
        String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://3.37.220.254:3306/wifi";
        String dbUserId = "root";
        String dbPassword = "123";

        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);

            String sql = " insert into wifi ( x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm) " +
                    "values (?, ?, ?);";
            pstmt = connection.prepareStatement(sql);

            for (int i = 0; i < jsonArray.size(); i++) {
                System.out.println("memberService1");
                JSONObject data = (JSONObject) jsonArray.get(i);
                System.out.println("memberService2");
                pstmt.setString(1, (String) data.get("X_SWIFI_MGR_NO"));
                pstmt.setString(2, (String) data.get("X_SWIFI_WRDOFC"));
                pstmt.setString(3, (String) data.get("X_SWIFI_MAIN_NM"));
                System.out.println("memberService3");
                pstmt.addBatch();
                pstmt.clearParameters();

                if ((i + 1) % 1000 == 0) {
                    int[] result = pstmt.executeBatch();
                    count += result.length;    //배치한 완료 개수
                    connection.commit();
                }
            }

            int[] result = pstmt.executeBatch();
            count += result.length;    //배치한 완료 개수
            connection.commit();
//            int affected = pstmt.executeUpdate();
//
//            if(affected > 0){
//                System.out.println("Inserted rows successfully");
//            }else{
//                System.out.println("Inserted rows failed");
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                if(pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("memberService4");
        return count;
    }
}
