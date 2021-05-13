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

    ///requests table
    @FXML
    private TableView<RequestData> requeststable;

    @FXML
    private TableColumn<RequestData, Integer> idcolumn4;

    @FXML
    private TableColumn<RequestData, String> usernamecolumn4;

    @FXML
    private TableColumn<RequestData, String> brandcolumn4;

    @FXML
    private TableColumn<RequestData, String> modelcolumn4;

    @FXML
    private TableColumn<RequestData, String> problemcolumn4;

    @FXML
    private TableColumn<RequestData, String> timecolumn4;

    @FXML
    private TableColumn<RequestData, String> statuscolumn4;

    @FXML
    private TableColumn<RequestData, String> progresscolumn4;

    @FXML
    private RadioButton rbone;

    @FXML
    private RadioButton rbtwo;

    @FXML
    private RadioButton rbthree;

    @FXML
    private RadioButton rbfour;

    @FXML
    private RadioButton rbfive;

    @FXML
    private Label ratingNumber;

    @FXML
    private Label thankyouFeedback;

    @FXML
    private Label feedbackWarning;

    @FXML
    private TextArea feedbackText;

    @FXML
    private TabPane clientTabPane;

    private dbConnect dc;
    private ObservableList<RequestData> data4;
    private String sql4 = "SELECT * FROM requests WHERE username=\'"+Config.loggedUsername+"\'";

    public void initialize(URL url, ResourceBundle rb){
        this.dc = new dbConnect();
    }

    @FXML
    private void loadRequestsData(ActionEvent event) throws SQLException {
        try{
            Connection conn = dbConnect.connect(Config.SQCONN);
            this.data4 = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sql4);
            while(rs.next()){
                this.data4.add(new RequestData(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7
                ),rs.getString(8)));

            }

        }catch (SQLException e){
            System.err.println("Error"+ e);
        }

        this.idcolumn4.setCellValueFactory(new PropertyValueFactory<RequestData,Integer>("ID"));
        this.usernamecolumn4.setCellValueFactory(new PropertyValueFactory<RequestData,String>("username"));
        this.brandcolumn4.setCellValueFactory(new PropertyValueFactory<RequestData,String>("brand"));
        this.modelcolumn4.setCellValueFactory(new PropertyValueFactory<RequestData,String>("model"));
        this.problemcolumn4.setCellValueFactory(new PropertyValueFactory<RequestData,String>("problem"));
        this.timecolumn4.setCellValueFactory(new PropertyValueFactory<RequestData,String>("interval"));
        this.statuscolumn4.setCellValueFactory(new PropertyValueFactory<RequestData,String>("status"));
        this.progresscolumn4.setCellValueFactory(new PropertyValueFactory<RequestData,String>("progress"));


        this.requeststable.setItems(null);
        this.requeststable.setItems(this.data4);
    }

    public void sendRequest(ActionEvent event) throws SQLException {


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
        loadRequestsData(event);

    }

    @FXML
    private void logout(ActionEvent event) {
        try{
            Stage stage = (Stage)clientTabPane.getScene().getWindow();
            Parent viewLoginPage = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewLoginPage);
            stage.setScene(scene);
            stage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void sendFeedback2(ActionEvent event) throws SQLException {

        String feedback = feedbackText.getText();
        String username = Config.loggedUsername;
        Integer givenRating = 0;
        if(rbone.isSelected()==true)
            givenRating = 1;
        if(rbtwo.isSelected()==true)
            givenRating = 2;
        if(rbthree.isSelected()==true)
            givenRating = 3;
        if(rbfour.isSelected()==true)
            givenRating = 4;
        if(rbfive.isSelected()==true)
            givenRating = 5;

        if (givenRating == 0 || feedback == "")
            this.feedbackWarning.setText("*rating required");
        else{
            this.thankyouFeedback.setText("Thank you for your feedback!");
            this.feedbackWarning.setText("");
            this.feedbackText.setText("");
            SendFeedbackModel.sendFeedback(username,feedback,givenRating);
        }
    }

    @FXML
    private void showServiceRating(ActionEvent event) {
        System.out.println("aaa");
    }

}
