package clients;

import Config.Config;
import dbUtil.Insert;
import dbUtil.Select;
import javafx.event.ActionEvent;

public class SendRequestModel{
    private static String dbPath = "gsm.sqlite";
    private static String tableName = "requests";
    private static String parameterList = "";
    private static String valueList = "";

    public SendRequestModel() {

    }


    public static void sendRequest(String username, String brand,String model, String problem,
                                   String interval, String status, String progress) {

        parameterList = "username, brand, model, problem, interval, status, progress";
        valueList = "\'" + username + "\'" + ", " + "\'" + brand + "\'" + ", " + "\'" + model + "\'" + ", " + "\'" + problem + "\'" + ", " +
                "\'" + interval + "\'" + ", " + "\'" + status + "\'," + "\'" + progress + "\'";

        Insert.Insert(dbPath,tableName,parameterList,valueList);

    }
}
