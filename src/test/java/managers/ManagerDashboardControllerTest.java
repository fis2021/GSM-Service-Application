package managers;

import Config.Config;
import clients.SendRequestModel;
import dbUtil.Select;
import dbUtil.dbConnect;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

@ExtendWith(ApplicationExtension.class)
class ManagerDashboardControllerTest {

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("managerDashboard.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("GSM Service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Test
    void testManagerViewRating(FxRobot robot) {

        robot.clickOn("#managerFeedback");
        robot.clickOn("#managerShowRating");

        assertThat("#managerRating").isNotEmpty();
    }

    @Test
    void testLogOutButton(FxRobot robot) {
        robot.clickOn("#managerFeedback");

        robot.clickOn("#managerLogout");

        verifyThat("#username",isVisible());

    }

    @Test
    void AcceptRequestTest(FxRobot robot) {
        String max="";
        String max2="";
        String sqlMax = "SELECT MAX(id) FROM requests WHERE status = 'Pending'";

        String username = "Test Test";
        String brand = "BrandTest";
        String model = "ModelTest";
        String problem = "ProblemTest";
        String interval = "IntervalTest";
        String status = "Pending";
        String progress = "No progress yet.";

        SendRequestModel.sendRequest(username,brand,model,problem,interval,status,progress);

        try{
            PreparedStatement pst;
            ResultSet rs;
            Connection conn = dbConnect.connect(Config.SQCONN);
            pst = conn.prepareStatement(sqlMax);
            rs = pst.executeQuery();
            if(rs.next()){
                max = rs.getString("max(id)");
                dbConnect.closeConnection();
            }
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }
//        System.out.println(max);

        robot.clickOn("#loadPendingRequests");
        robot.clickOn("#pendingRequestId");
        robot.write(max);
        robot.clickOn("#acceptRequest");

        robot.clickOn("#loadPendingRequests");
        try{
            PreparedStatement pst;
            ResultSet rs;
            Connection conn = dbConnect.connect(Config.SQCONN);
            pst = conn.prepareStatement(sqlMax);
            rs = pst.executeQuery();
            if(rs.next()){
                max2 = rs.getString("max(id)");
                dbConnect.closeConnection();
            }
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }
//        System.out.println(max2);

        assertNotEquals(max,max2);

    }

    @Test
    void RefuseRequestTest(FxRobot robot) {
        String max="";
        String max2="";
        String sqlMax = "SELECT MAX(id) FROM requests WHERE status = 'Pending'";

        String username = "Test Test2";
        String brand = "BrandTest2";
        String model = "ModelTest2";
        String problem = "ProblemTest2";
        String interval = "IntervalTest2";
        String status = "Pending";
        String progress = "No progress yet.";

        SendRequestModel.sendRequest(username,brand,model,problem,interval,status,progress);

        try{
            PreparedStatement pst;
            ResultSet rs;
            Connection conn = dbConnect.connect(Config.SQCONN);
            pst = conn.prepareStatement(sqlMax);
            rs = pst.executeQuery();
            if(rs.next()){
                max = rs.getString("max(id)");
                dbConnect.closeConnection();
            }
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }
//        System.out.println(max);

        robot.clickOn("#loadPendingRequests");
        robot.clickOn("#pendingRequestId");
        robot.write(max);
        robot.clickOn("#refuseRequest");

        robot.clickOn("#loadPendingRequests");
        try{
            PreparedStatement pst;
            ResultSet rs;
            Connection conn = dbConnect.connect(Config.SQCONN);
            pst = conn.prepareStatement(sqlMax);
            rs = pst.executeQuery();
            if(rs.next()){
                max2 = rs.getString("max(id)");
                dbConnect.closeConnection();
            }
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }
//        System.out.println(max2);

        assertNotEquals(max,max2);

    }

    @Test
    void UpdateTimeslotTest(FxRobot robot) {
        String max="";
        String sqlMax = "SELECT MAX(id) FROM requests WHERE status = 'Accepted'";
        String sqlVerify = "SELECT * FROM requests WHERE status = 'Accepted' AND interval = 'IntervalTest3'";

        String username = "Test Test3";
        String brand = "BrandTest3";
        String model = "ModelTest3";
        String problem = "ProblemTest3";
        String interval = "IntervalTest3";
        String status = "Accepted";
        String progress = "No progress yet.";

        SendRequestModel.sendRequest(username,brand,model,problem,interval,status,progress);

        try{
            PreparedStatement pst;
            ResultSet rs;
            Connection conn = dbConnect.connect(Config.SQCONN);
            pst = conn.prepareStatement(sqlMax);
            rs = pst.executeQuery();
            if(rs.next()){
                max = rs.getString("max(id)");
                dbConnect.closeConnection();
            }
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }

        robot.clickOn("#acceptedRequests");
        robot.clickOn("#loadAccepted");
        robot.clickOn("#acceptedid");
        robot.write(max);
        robot.clickOn("#apptime");
        robot.write("This is a test interval timeee");
        robot.clickOn("#updateTimeslot");

        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM requests WHERE status = 'Accepted' AND interval = 'This is a test interval time'"),"1");

    }

    @Test
    void UpdateProgressTest(FxRobot robot) {
        String max="";
        String sqlMax = "SELECT MAX(id) FROM requests WHERE status = 'Accepted'";
        String sqlVerify = "SELECT * FROM requests WHERE status = 'Accepted' AND progress = 'IntervalTest3'";

        String username = "Test Test4";
        String brand = "BrandTest4";
        String model = "ModelTest4";
        String problem = "ProblemTest4";
        String interval = "IntervalTest4";
        String status = "Accepted";
        String progress = "No progress yet.";

        SendRequestModel.sendRequest(username,brand,model,problem,interval,status,progress);

        try{
            PreparedStatement pst;
            ResultSet rs;
            Connection conn = dbConnect.connect(Config.SQCONN);
            pst = conn.prepareStatement(sqlMax);
            rs = pst.executeQuery();
            if(rs.next()){
                max = rs.getString("max(id)");
                dbConnect.closeConnection();
            }
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }

        robot.clickOn("#acceptedRequests");
        robot.clickOn("#loadAccepted");
        robot.clickOn("#acceptedid1");
        robot.write(max);
        robot.clickOn("#progressInput");
        robot.write("We are working on it!");
        robot.clickOn("#updateProgress");

        Assertions.assertEquals(Select.CheckEntry(Config.SQCONN,"SELECT * FROM requests WHERE status = 'Accepted' AND progress = 'We are working on it!'"),"1");

    }



}