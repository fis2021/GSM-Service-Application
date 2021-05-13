package clients;

import Config.Config;
import dbUtil.Insert;
import dbUtil.Select;
import javafx.event.ActionEvent;

public class SendFeedbackModel{
    private static String dbPath = "gsm.sqlite";
    private static String tableName = "feedback";
    private static String parameterList = "";
    private static String valueList = "";

    public SendFeedbackModel() {

    }


    public static void sendFeedback(String username, String review,Integer rating) {

        parameterList = "username, review, rating";
        valueList = "\'" + username + "\'" + ", " + "\'" + review + "\'" + ", " + "\'" + rating + "\'";

        Insert.Insert(dbPath,tableName,parameterList,valueList);

    }
}
