package dbUtil;

import Config.Config;

import java.awt.*;
import java.sql.Statement;
import java.sql.*;

public class Select {

    public synchronized static String CheckEntry(String nameDB, String sql_command) {
       Connection conn = dbConnect.connect(Config.SQCONN);

        ResultSet rs = null;
        String result = "";
        try {
            PreparedStatement pstmt  = conn.prepareStatement(sql_command);
            rs = pstmt.executeQuery();

            if(rs.next()) {

                return "1";
            }
            else
                return "0";


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.closeConnection();
            try {

                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }



}