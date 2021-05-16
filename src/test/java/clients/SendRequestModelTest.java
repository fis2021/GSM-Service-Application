package clients;

import Config.Config;
import dbUtil.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendRequestModelTest {

    @Test
    void sendRequestTest() {

        String username = "Ana Popescu";
        String brand = "LG";
        String model = "G201";
        String problem = "Broken home button";
        String interval = "F-S 12:30-19:23";
        String status = "Pending";
        String progress = "No progress yet.";

        SendRequestModel.sendRequest(username,brand,model,problem,interval,status,progress);


        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM requests WHERE username = " + "\'" + username + "\' " + " AND brand = " + "\'" + brand + "\' " + " AND model = " + "\'" + model + "\' " + " AND problem = " + "\'" + problem + "\' " + " AND interval = " + "\'" + interval + "\' " + " AND status = " + "\'" + status + "\' " + " AND progress = " + "\'" + progress + "\' " ),"1");

    }

}