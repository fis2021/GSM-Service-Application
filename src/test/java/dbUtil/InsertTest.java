package dbUtil;

import Exceptions.Empty_String;
import Exceptions.Not_A_DB_File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertTest {

    Insert test;

    @BeforeEach
    void setUp() {
        test =new Insert();
    }

    @AfterEach
    void tearDown() {
        test=null;
    }

    @Test
    void insertTest() {
        assertThrows(Empty_String.class, () ->{
            Insert.Insert("test.db","","","");
        });
    }
}