package Encryption;

import Exceptions.Empty_String;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5Test {

    @Test
    void TestMD5() {
        assertThrows(Empty_String.class, () ->{
            MD5.getMd5("");
        });
    }
}