package gsmapp;

import Config.Config;
import dbUtil.Insert;
import dbUtil.Select;
import javafx.event.ActionEvent;

public class SignupModel {
    private static String dbPath = "gsm.sqlite";
    private static String tableName = "users";
    private static String parameterList = "";
    private static String valueList = "";

    public SignupModel() {

    }


    public static void registerUser(String firstname,String lastname, String username,
                             String password,String role, String accsesscode) {

        if(accsesscode.equals(Config.ACESS_CODE)) {
            parameterList = "firstName, lastName, username, password, role, accesscode";
            valueList = "\'" + firstname + "\'" + ", " + "\'" + lastname + "\'" + ", " + "\'" + username + "\'" + ", " +
                    "\'" + password + "\'" + ", " + "\'" + role + "\'," + "\'" + accsesscode + "\'";
        }
        else {
            parameterList = "firstName, lastName, username, password, role, accesscode";
            valueList = "\'" + firstname + "\'" + ", " + "\'" + lastname + "\'" + ", " + "\'" + username + "\'" + ", " +
                    "\'" + password + "\'" + ", " + "\'" + role + "\'," + "\'" + "" + "\'";
        }
        System.out.println(valueList);

        String sql_check_username = "SELECT * FROM users WHERE username = " + "\'" + username + "\'";
        String checkgetUsername = Select.CheckEntry(dbPath, sql_check_username);

        if(checkgetUsername == "0") {
            Insert.Insert(dbPath,tableName,parameterList,valueList);
        }
        else {
            //afisezi in interfata
            System.out.println("CONTUL EXISTA DEJA");
        }

    }
}
