import Config.Config;
import dbUtil.Select;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

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
    void testLogin(FxRobot robot) {
        robot.clickOn("#username");
        robot.write("aaaaaa");

        robot.clickOn("#password");
        robot.write("aaaaaa");

        robot.clickOn("#role");

        robot.clickOn("Client");

        String user="aaaaaa";

        robot.clickOn("#loginButton");
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM users WHERE username = " + "\'" + user + "\'"),"1");

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

        verifyThat("#loginMessageLabel", isVisible());
    }
}