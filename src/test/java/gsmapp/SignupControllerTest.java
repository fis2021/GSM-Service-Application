package gsmapp;

import Config.Config;
import dbUtil.Select;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

@ExtendWith(ApplicationExtension.class)
class SignupControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("signup.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("GSM Service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    void testRegisterClient(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("Maria");

        robot.clickOn("#lnameInput");
        robot.write("Mtsc");

        robot.clickOn("#usernameInput");
        robot.write("Mtsc");

        robot.clickOn("#passwordInput");
        robot.write("mariamaria");

        robot.clickOn("#confirmPassInput");
        robot.write("mariamaria");

        robot.clickOn("#comboboxInput");
        robot.clickOn("Client");

        robot.clickOn("#createAcc");
        String user = "Mtsc";
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM users WHERE username = " + "\'" + user + "\'"),"1");

    }

    @Test
    void testRegisterManager(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("Maria");

        robot.clickOn("#lnameInput");
        robot.write("Manager");

        robot.clickOn("#usernameInput");
        robot.write("MtscManager");

        robot.clickOn("#passwordInput");
        robot.write("mariamaria");

        robot.clickOn("#confirmPassInput");
        robot.write("mariamaria");

        robot.clickOn("#comboboxInput");
        robot.clickOn("Manager");


        robot.clickOn("#codeInput");
        robot.write("1234");

        robot.clickOn("#createAcc");
        String user2 = "MtscManager";
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM users WHERE username = " + "\'" + user2 + "\'"),"1");

    }

    @Test
    void testWrongPassword(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("Mariaa");

        robot.clickOn("#lnameInput");
        robot.write("Aairam");

        robot.clickOn("#usernameInput");
        robot.write("mariaa");

        robot.clickOn("#passwordInput");
        robot.write("mariamaria");

        robot.clickOn("#confirmPassInput");
        robot.write("mariamaria1");

        robot.clickOn("#comboboxInput");
        robot.clickOn("Manager");

        robot.clickOn("#codeInput");
        robot.write("1234");

        robot.clickOn("#createAcc");

        FxAssert.verifyThat("#passNotMatchLabel", LabeledMatchers.hasText("Passwords do not match!"));


    }

    @Test
    void testUsernameAlreadyInDatabase(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("Mariaaa");

        robot.clickOn("#lnameInput");
        robot.write("Aaairam");

        robot.clickOn("#usernameInput");
        robot.write("mariaaa");

        robot.clickOn("#passwordInput");
        robot.write("mariamaria");

        robot.clickOn("#confirmPassInput");
        robot.write("mariamaria");

        robot.clickOn("#comboboxInput");
        robot.clickOn("Manager");

        robot.clickOn("#codeInput");
        robot.write("1234");

        robot.clickOn("#createAcc");

        FxAssert.verifyThat("#usernameWarning", LabeledMatchers.hasText("Username already exists!"));

    }

    @Test
    void testIncorrectAccessCode(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("Denisa");

        robot.clickOn("#lnameInput");
        robot.write("Lunga");

        robot.clickOn("#usernameInput");
        robot.write("DenisaLunga");

        robot.clickOn("#passwordInput");
        robot.write("denilunga");

        robot.clickOn("#confirmPassInput");
        robot.write("denilunga");

        robot.clickOn("#comboboxInput");
        robot.clickOn("Manager");

        robot.clickOn("#codeInput");
        robot.write("1235");

        robot.clickOn("#createAcc");

        FxAssert.verifyThat("#accessWarning", LabeledMatchers.hasText("*incorrect access code"));

    }

    @Test
    void testUsernameTooShort(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("Denisa");

        robot.clickOn("#lnameInput");
        robot.write("Lunga");

        robot.clickOn("#usernameInput");
        robot.write("De");

        robot.clickOn("#passwordInput");
        robot.write("denilunga");

        robot.clickOn("#confirmPassInput");
        robot.write("denilunga");

        robot.clickOn("#comboboxInput");
        robot.clickOn("Manager");

        robot.clickOn("#codeInput");
        robot.write("1234");

        robot.clickOn("#createAcc");

        FxAssert.verifyThat("#usernameWarning", LabeledMatchers.hasText("*must have at least 4 characters"));

    }

    @Test
    void testFirstNameTooShort(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("");

        robot.clickOn("#lnameInput");
        robot.write("Lunga");

        robot.clickOn("#usernameInput");
        robot.write("Denisaaa");

        robot.clickOn("#passwordInput");
        robot.write("denilunga");

        robot.clickOn("#confirmPassInput");
        robot.write("denilunga");

        robot.clickOn("#comboboxInput");
        robot.clickOn("Manager");

        robot.clickOn("#codeInput");
        robot.write("1234");

        robot.clickOn("#createAcc");

        FxAssert.verifyThat("#firstNameWarning", LabeledMatchers.hasText("*first name is required"));

    }

    @Test
    void testLastNameTooShort(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("Denisa");

        robot.clickOn("#lnameInput");
        robot.write("");

        robot.clickOn("#usernameInput");
        robot.write("Denisaaa");

        robot.clickOn("#passwordInput");
        robot.write("denilunga");

        robot.clickOn("#confirmPassInput");
        robot.write("denilunga");

        robot.clickOn("#comboboxInput");
        robot.clickOn("Manager");

        robot.clickOn("#codeInput");
        robot.write("1234");

        robot.clickOn("#createAcc");

        FxAssert.verifyThat("#lastNameWarning", LabeledMatchers.hasText("*last name is required"));

    }

    @Test
    void testPasswordTooShort(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("Denisa");

        robot.clickOn("#lnameInput");
        robot.write("Lunga");

        robot.clickOn("#usernameInput");
        robot.write("Denisaaa");

        robot.clickOn("#passwordInput");
        robot.write("deni");

        robot.clickOn("#confirmPassInput");
        robot.write("deni");

        robot.clickOn("#comboboxInput");
        robot.clickOn("Manager");

        robot.clickOn("#codeInput");
        robot.write("1234");

        robot.clickOn("#createAcc");

        FxAssert.verifyThat("#passwordWarning", LabeledMatchers.hasText("*must have at least 6 characters"));

    }

    @Test
    void testRoleNotSelected(FxRobot robot) {

        robot.clickOn("#fnameInput");
        robot.write("Denisa");

        robot.clickOn("#lnameInput");
        robot.write("Lunga");

        robot.clickOn("#usernameInput");
        robot.write("Denisaaa");

        robot.clickOn("#passwordInput");
        robot.write("denilunga");

        robot.clickOn("#confirmPassInput");
        robot.write("denilunga");

        robot.clickOn("#codeInput");
        robot.write("1234");

        robot.clickOn("#createAcc");

        FxAssert.verifyThat("#roleWarning", LabeledMatchers.hasText("*role is required"));

    }
}