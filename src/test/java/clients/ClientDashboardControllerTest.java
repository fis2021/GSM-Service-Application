package clients;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

@ExtendWith(ApplicationExtension.class)
class ClientDashboardControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientDashboard.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("GSM Service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    void testLoadRequestData(FxRobot robot) {
    robot.clickOn("#loadRequestData");
    }

    @Test
    void testSendRequest(FxRobot robot) {
        robot.clickOn("#sendRequest");

        robot.clickOn("#brandInput");
        robot.write("iPhone");

        robot.clickOn("#modelInput");
        robot.write("11 Pro Max");

        robot.clickOn("#problemInput");
        robot.write("Broken screen");

        robot.clickOn("#timeslotInput");
        robot.write("M-W 12:00-14:00");

        robot.clickOn("#sendRequestButton");

        String brand = "iPhone";
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM requests WHERE brand = " + "\'" + brand + "\'"),"1");

        String model = "11 Pro Max";
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM requests WHERE model = " + "\'" + model + "\'"),"1");

        String problem = "Broken screen";
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM requests WHERE problem = " + "\'" + problem + "\'"),"1");

        String timeslot = "M-W 12:00-14:00";
        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM requests WHERE interval = " + "\'" + timeslot + "\'"),"1");

        verifyThat("#thankYouLabel", isVisible());
    }

    @Test
    void notAllFieldsAreFilled(FxRobot robot) {
        robot.clickOn("#sendRequest");

        robot.clickOn("#brandInput");
        robot.write("Xiaomi");

        robot.clickOn("#problemInput");
        robot.write("Broken volume button");

        robot.clickOn("#timeslotInput");
        robot.write("T-S 13:00-16:00");

        robot.clickOn("#sendRequestButton");

        verifyThat("#requestWarningLabel", isVisible());
    }
}