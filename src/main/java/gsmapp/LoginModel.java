package gsmapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.Config;
import dbUtil.Select;
import dbUtil.dbConnect;
//import dbUtil.dbConnection;

public class LoginModel {
    Connection connection;

    public LoginModel(){

        this.connection = dbConnect.connect(Config.SQCONN);

        if (this.connection == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public boolean isLogin(String user, String pass, String opt)throws Exception{


        String sql = "SELECT * FROM login where username = ? and password = ? and division = ?";
        String sql_check_username = "SELECT * FROM users WHERE username = " + "\'" + user + "\'";
        String sql_check_password = "SELECT * FROM users WHERE password = " + "\'" + pass + "\'";
        String sql_check_role =  "SELECT * FROM users WHERE role = " + "\'" + opt + "\'";
        String checkgetUsername = Select.CheckEntry(Config.SQCONN, sql_check_username);
        String checkgetPassword = Select.CheckEntry(Config.SQCONN, sql_check_password);
        String checkgetRole = Select.CheckEntry(Config.SQCONN, sql_check_role);

        if(checkgetUsername == "1" && checkgetPassword == "1" && checkgetRole == "1") {
            return true;
        }
        else {
            return false;
        }


    }
}
