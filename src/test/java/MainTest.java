import Config.Config;
import Encryption.MD5;
import dbUtil.Select;
import gsmapp.SignupModel;
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

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.util.NodeQueryUtils.isVisible;


@ExtendWith(ApplicationExtension.class)
class MainTest {
    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("GSM Service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    void testLoginClient(FxRobot robot) {

        robot.clickOn("#username");
        robot.write("ghghghg");

        robot.clickOn("#password");
        robot.write("ghghghg");
        String hashh = MD5.getMd5("ghghghg");

        SignupModel.registerUser("ghghghg","ghghghg","ghghghg",hashh,"Client","");

        robot.clickOn("#role");

        robot.clickOn("Client");

        String user="ghghghg";

        robot.clickOn("#loginButton");
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM users WHERE username = " + "\'" + user + "\'"),"1");

        verifyThat("#Feedback",isVisible());
    }

    @Test
    void testLoginManager(FxRobot robot) {

        robot.clickOn("#username");
        robot.write("dfdfdf");

        robot.clickOn("#password");
        robot.write("dfdfdf");
        String hashh2 = MD5.getMd5("dfdfdf");

        SignupModel.registerUser("dfdfdf","dfdfdf","dfdfdf",hashh2,"Manager","1234");

        robot.clickOn("#role");

        robot.clickOn("Manager");

        String user="dfdfdf";

        robot.clickOn("#loginButton");
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM users WHERE username = " + "\'" + user + "\'"),"1");

        verifyThat("#pendingRequests",isVisible());
    }

    @Test
    void testWrongLogin(FxRobot robot) {
        robot.clickOn("#username");
        robot.write("abcdef");

        robot.clickOn("#password");
        robot.write("abcdef");

        robot.clickOn("#role");

        robot.clickOn("Client");

        String user="abcdef";

        robot.clickOn("#loginButton");

        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM users WHERE username = " + "\'" + user + "\'"),"0");

        FxAssert.verifyThat("#loginMessageLabel", LabeledMatchers.hasText("Wrong credentials"));

    }
}