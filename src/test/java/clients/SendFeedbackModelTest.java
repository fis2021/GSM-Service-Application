package clients;

import Config.Config;
import dbUtil.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendFeedbackModelTest {

    @Test
    void InsertFeedbackTest() {
        String username = "test";
        String review = "This is a review";
        Integer rating = 5;

        SendFeedbackModel.sendFeedback(username,review,rating);


        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM feedback WHERE review = " + "\'" + review + "\' " + " AND username = " + "\'" + username + "\' " + " AND rating = " + "\'" + rating + "\' "   ),"1");
    }
}