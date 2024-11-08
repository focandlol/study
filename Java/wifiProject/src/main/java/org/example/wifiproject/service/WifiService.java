package org.example.wifiproject.service;

import org.example.wifiproject.dto.PositionDto;
import org.example.wifiproject.dto.WifiDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.wifiproject.db.Db.close;
import static org.example.wifiproject.db.Db.getConnection;
import static org.example.wifiproject.service.HistoryService.addHistory;

public class WifiService {
    public static int dbInsert(JSONArray jsonArray){
        Connection connection = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql = " insert into wifi "
                    + " ( x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, "
                    + " x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, "
                    + " x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm) "
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
            pstmt = connection.prepareStatement(sql);

            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject data = (JSONObject) jsonArray.get(i);
                System.out.println(data.get("LAT"));
                pstmt.setString(1, (String) data.get("X_SWIFI_MGR_NO"));
                pstmt.setString(2, (String) data.get("X_SWIFI_WRDOFC"));
                pstmt.setString(3, (String) data.get("X_SWIFI_MAIN_NM"));
                pstmt.setString(4, (String) data.get("X_SWIFI_ADRES1"));
                pstmt.setString(5, (String) data.get("X_SWIFI_ADRES2"));
                pstmt.setString(6, (String) data.get("X_SWIFI_INSTL_FLOOR"));
                pstmt.setString(7, (String) data.get("X_SWIFI_INSTL_TY"));
                pstmt.setString(8, (String) data.get("X_SWIFI_INSTL_MBY"));
                pstmt.setString(9, (String) data.get("X_SWIFI_SVC_SE"));
                pstmt.setString(10, (String) data.get("X_SWIFI_CMCWR"));
                pstmt.setString(11, (String) data.get("X_SWIFI_CNSTC_YEAR"));
                pstmt.setString(12, (String) data.get("X_SWIFI_INOUT_DOOR"));
                pstmt.setString(13, (String) data.get("X_SWIFI_REMARS3"));
                pstmt.setString(14, (String) data.get("LAT"));
                pstmt.setString(15, (String) data.get("LNT"));
                pstmt.setString(16, (String) data.get("WORK_DTTM"));
                pstmt.addBatch();
                pstmt.clearParameters();

                if ((i + 1) % 1000 == 0) {
                    int[] result = pstmt.executeBatch();
                    count += result.length;
                    connection.commit();
                }
            }
            int[] result = pstmt.executeBatch();
            count += result.length;
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            close(null,pstmt,connection);
        }
        System.out.println("memberService4");
        return count;
    }

    public List<WifiDto> getNearestWifiList(PositionDto positionDto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<WifiDto> wifiDtoList = new ArrayList<WifiDto>();
        try{
            connection = getConnection();

            String sql = "SELECT *, " +
                    "ROUND(6371 * ACOS(COS(RADIANS(?)) * COS(RADIANS(lat)) * COS(RADIANS(lnt) - RADIANS(?)) + SIN(RADIANS(?)) * SIN(RADIANS(lat))),4) AS distance " +
                    "FROM " +
                    "    wifi " +
                    "ORDER BY " +
                    "    distance " +
                    "LIMIT 20;";
            pstmt = connection.prepareStatement(sql);
            pstmt.setDouble(1, positionDto.getLat());
            pstmt.setDouble(2, positionDto.getLnt());
            pstmt.setDouble(3, positionDto.getLat());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                WifiDto wifiDto = WifiDto.builder()
                        .wifiId(rs.getInt("wifi_id"))
                        .xSwifiMgrNo(rs.getString("x_swifi_mgr_no"))
                        .xSwifiWrdofc(rs.getString("x_swifi_wrdofc"))
                        .xSwifiMainNm(rs.getString("x_swifi_main_nm"))
                        .xSwifiAdres1(rs.getString("x_swifi_adres1"))
                        .xSwifiAdres2(rs.getString("x_swifi_adres2"))
                        .xSwifiInstlFloor(rs.getString("x_swifi_instl_floor"))
                        .xSwifiInstlTy(rs.getString("x_swifi_instl_ty"))
                        .xSwifiInstlMby(rs.getString("x_swifi_instl_mby"))
                        .xSwifiSvcSe(rs.getString("x_swifi_svc_se"))
                        .xSwifiCmcwr(rs.getString("x_swifi_cmcwr"))
                        .xSwifiCnstcYear(rs.getString("x_swifi_cnstc_year"))
                        .xSwifiInoutDoor(rs.getString("x_swifi_inout_door"))
                        .xSwifiRemars3(rs.getString("x_swifi_remars3"))
                        .lat(rs.getString("lat"))
                        .lnt(rs.getString("lnt"))
                        .workDttm(String.valueOf(rs.getTimestamp("work_dttm").toLocalDateTime()))
                        .distance(rs.getDouble("distance"))
                        .build();
                wifiDtoList.add(wifiDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            close(rs,pstmt,connection);
        }
        addHistory(positionDto);
        return wifiDtoList;
    }
    public WifiDto getDetailWifi(String id, String distance) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        WifiDto wifiDto = null;
        try{
            connection = getConnection();

            String sql = "select * from wifi where wifi_id=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                    wifiDto = WifiDto.builder()
                        .wifiId(rs.getInt("wifi_id"))
                        .xSwifiMgrNo(rs.getString("x_swifi_mgr_no"))
                        .xSwifiWrdofc(rs.getString("x_swifi_wrdofc"))
                        .xSwifiMainNm(rs.getString("x_swifi_main_nm"))
                        .xSwifiAdres1(rs.getString("x_swifi_adres1"))
                        .xSwifiAdres2(rs.getString("x_swifi_adres2"))
                        .xSwifiInstlFloor(rs.getString("x_swifi_instl_floor"))
                        .xSwifiInstlTy(rs.getString("x_swifi_instl_ty"))
                        .xSwifiInstlMby(rs.getString("x_swifi_instl_mby"))
                        .xSwifiSvcSe(rs.getString("x_swifi_svc_se"))
                        .xSwifiCmcwr(rs.getString("x_swifi_cmcwr"))
                        .xSwifiCnstcYear(rs.getString("x_swifi_cnstc_year"))
                        .xSwifiInoutDoor(rs.getString("x_swifi_inout_door"))
                        .xSwifiRemars3(rs.getString("x_swifi_remars3"))
                        .lat(rs.getString("lat"))
                        .lnt(rs.getString("lnt"))
                        .workDttm(String.valueOf(rs.getTimestamp("work_dttm").toLocalDateTime()))
                        .distance(Double.parseDouble(distance))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            close(rs,pstmt,connection);
        }
        return wifiDto;
    }
}
