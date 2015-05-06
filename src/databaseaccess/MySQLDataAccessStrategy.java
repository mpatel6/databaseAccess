package databaseaccess;

import java.sql.*;
import java.util.*;

public class MySQLDataAccessStrategy {

    Connection conn;

    public MySQLDataAccessStrategy() {
    }

    public void openConnection(String driverClassName, String url, String username, String password) {

        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("SQL Exception11");

        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find class11");
        }

    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

    public List<Map<String, Object>> findRecords(String tblName) {

        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        String sql = "SELECT * FROM " + tblName;
        System.out.println(sql);
        List<Map<String, Object>> dataList = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();

            int numberOfColumns = rsmd.getColumnCount();

            Map<String, Object> record;

            while (rs.next()) {

                record = new HashMap();

                for (int i = 1; i <= numberOfColumns; i++) {

                    record.put(Integer.toString(i), rs.getObject(i));
                }

                dataList.add(record);
            }
        } catch (SQLException e) {
            System.out.println("SQL exception from find records");
        }

        return dataList;

    }

    public List<Map<String, Object>> finRecordById(String id) {
    return null;
    }

//    public static void main(String[] args) {
//
//        MySQLDataAccessStrategy db = new MySQLDataAccessStrategy();
//
//        List<Map<String, Object>> dataList = new ArrayList<>();
//
//        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book_db", "root", "admin");
//        dataList = db.findRecords("book");
//        for (Map e : dataList) {
//            System.out.println(e);
//        }
//    }

}
