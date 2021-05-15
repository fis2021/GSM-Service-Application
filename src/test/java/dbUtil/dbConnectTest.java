package dbUtil;

import Encryption.MD5;
import Exceptions.Empty_String;
import Exceptions.Not_A_DB_File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class dbConnectTest {

    dbConnect test;

    @BeforeEach
    void setUp() throws Exception{
        test = new dbConnect();
    }

    @AfterEach
    void tearDown() throws Exception {
        test =null;
    }

    @Test
    void connect() {
        assertThrows(Not_A_DB_File.class, () ->{
            dbConnect.connect("something.json");
        });
    }

    @Test
    void close(){
        dbConnect.closeConnection();
    }

}