package gsmapp;

import Encryption.MD5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupModelTest {

    @Test
    void SignUpTestAlreadyExists() {
        String firstname = "Ioana";
        String lastname = "Popovici";
        String username = "PopIoanaa";
        String password = "ioanapop";
        String role = "Manager";
        String accsesscode = "1234";

        String hashed_password = MD5.getMd5(password);
        SignupModel.registerUser(firstname,lastname,username,hashed_password,role,accsesscode);
        assertEquals(SignupModel.registerUser(firstname,lastname,username,hashed_password,role,accsesscode),"1");
    }
}