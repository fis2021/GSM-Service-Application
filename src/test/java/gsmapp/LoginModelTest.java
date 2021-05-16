package gsmapp;

import Encryption.MD5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginModelTest {

    @Test
    void isLoginTest() throws Exception {
        String username = "wewewe";
        String pass= "wewewe";
        String role = "Client";
        String hashed_password = MD5.getMd5(pass);

        SignupModel.registerUser("wewewe","wewewe","wewewe",hashed_password,"Client","");

        LoginModel loginModel = new LoginModel();

        Assertions.assertTrue(loginModel.isLogin(username,hashed_password,role));
    }

    @Test
    void isLoginTestWrong() throws Exception {
        String username = "asdfghjkl";
        String pass= "asdfghjkl";
        String role = "Manager";
        String hashed_password = MD5.getMd5(pass);

        LoginModel loginModel = new LoginModel();

        Assertions.assertFalse(loginModel.isLogin(username,hashed_password,role));
    }
}