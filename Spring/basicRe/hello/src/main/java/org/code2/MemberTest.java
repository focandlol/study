package org.code2;

import java.sql.*;
import java.util.Scanner;

public class MemberTest {

    public static void dbSelect(){
        String url = "jdbc:mariadb://3.37.220.254:3306/testdb3";
        String dbUserId = "root";
        String dbPassword = "123";

        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 1;
        try{
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);

            String sql = " select userId, name, password from member where userId = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String userId = rs.getString("userId");
                String name = rs.getString("name");
                String password = rs.getString("password");

                System.out.println(userId+", "+name+", "+password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


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
    }

    public static void dbInsert(String nameVal, String emailVal, String passwordVal){
        String url = "jdbc:mariadb://3.37.220.254:3306/testdb3";
        String dbUserId = "root";
        String dbPassword = "123";

        Connection connection = null;
        PreparedStatement pstmt = null;
        try{
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);

            String sql = " insert into member (name, email, password) " +
                    "values (?, ?, ?);";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,nameVal);
            pstmt.setString(2,emailVal);
            pstmt.setString(3,passwordVal);
            int affected = pstmt.executeUpdate();

            if(affected > 0){
                System.out.println("Inserted rows successfully");
            }else{
                System.out.println("Inserted rows failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
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
    }

    public static void dbUpdate(){
        String url = "jdbc:mariadb://3.37.220.254:3306/testdb3";
        String dbUserId = "root";
        String dbPassword = "123";

        Connection connection = null;
        PreparedStatement pstmt = null;
        int idVal = 1;
        try{
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);

            String sql = " update member set password = 9999 where userId = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,idVal);
            int affected = pstmt.executeUpdate();

            if(affected > 0){
                System.out.println("updated rows successfully");
            }else{
                System.out.println("updated rows failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
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
    }

    public static void dbDelete(){
        String url = "jdbc:mariadb://3.37.220.254:3306/testdb3";
        String dbUserId = "root";
        String dbPassword = "123";

        Connection connection = null;
        PreparedStatement pstmt = null;
        String name = "focandlol";
        try{
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);

            String sql = " delete from member where name = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,name);
            int affected = pstmt.executeUpdate();

            if(affected > 0){
                System.out.println("deleted rows successfully");
            }else{
                System.out.println("deleted rows failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
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
    }
    public static void main(String[] args) {
        dbSelect();
        Scanner sc = new Scanner(System.in);
        System.out.print("이름 입력");
        String name = sc.next();
        System.out.print("이메일 입력");
        String email = sc.next();
        System.out.print("패스워드 입력");
        String password = sc.next();
        dbInsert(name,email,password);
        //dbUpdate();
        //dbDelete();
    }
}
