package dbUtil;

import Config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectTest {

    @Test
    void testCheckEntry() {
        String user="NuEinBazaDeDate";
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM users WHERE username = " + "\'" + user + "\'"),"0");


        String opt = "Client";
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM users WHERE role = " + "\'" + opt + "\'"),"1");

        String opt2 = "Manager";
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM users WHERE role = " + "\'" + opt2 + "\'"),"1");
    }
}