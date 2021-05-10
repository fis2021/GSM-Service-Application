package clients;

import Config.Config;
import Encryption.MD5;
import dbUtil.dbConnect;
import clients.SendRequestModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import managers.RequestData;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientDashboardController implements Initializable {

    @FXML
    private TextField modelInput;

    @FXML
    private TextField brandInput;

    @FXML
    private TextField timeslotInput;

    @FXML
    private TextArea problemInput;

    @FXML
    private Label requestWarningLabel;

    @FXML
    private Label thankYouLabel;

    public void initialize(URL url, ResourceBundle rb){
    }

    public void sendRequest(ActionEvent event) {


        String brand = brandInput.getText();
        String model = modelInput.getText();
        String username = Config.loggedUsername;
        String problem = problemInput.getText();
        String interval = timeslotInput.getText();
        String status = "Pending";
        String progress = "No progress yet.";

        if(brand.length()<1 || model.length()<1 || problem.length()<1 || interval.length()<1) {
            this.requestWarningLabel.setText("*all fields are required!");
            return;
        }
        else
        {
            this.thankYouLabel.setText("Thank you for choosing us!");
            this.requestWarningLabel.setText("");
        }

        SendRequestModel.sendRequest(username,brand,model,problem,interval,status,progress);


    }

}
