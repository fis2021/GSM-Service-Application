package managers;

import Config.Config;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import dbUtil.dbConnect;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.ResourceBundle;

public class ManagerDashboardController implements Initializable {

    ///pending table
    @FXML
    private TableView<RequestData> pendingtable;

    @FXML
    private TableColumn<RequestData, Integer> idcolumn;

    @FXML
    private TableColumn<RequestData, String> usernamecolumn;

    @FXML
    private TableColumn<RequestData, String> brandcolumn;

    @FXML
    private TableColumn<RequestData, String> modelcolumn;

    @FXML
    private TableColumn<RequestData, String> problemcolumn;

    @FXML
    private TableColumn<RequestData, String> timecolumn;

    @FXML
    private TableColumn<RequestData, String> statuscolumn;

    @FXML
    private TableColumn<RequestData, String> progresscolumn;


    ///accepted table

    @FXML
    private TableView<RequestData> acceptedtable;

    @FXML
    private TableColumn<RequestData, Integer> idcolumn2;

    @FXML
    private TableColumn<RequestData, String> usernamecolumn2;

    @FXML
    private TableColumn<RequestData, String> brandcolumn2;

    @FXML
    private TableColumn<RequestData, String> modelcolumn2;

    @FXML
    private TableColumn<RequestData, String> problemcolumn2;

    @FXML
    private TableColumn<RequestData, String> timecolumn2;

    @FXML
    private TableColumn<RequestData, String> statuscolumn2;

    @FXML
    private TableColumn<RequestData, String> progresscolumn2;

    //refused table

    @FXML
    private TableView<RequestData> refusedtable;

    @FXML
    private TableColumn<RequestData, Integer> idcolumn3;

    @FXML
    private TableColumn<RequestData, String> usernamecolumn3;

    @FXML
    private TableColumn<RequestData, String> brandcolumn3;

    @FXML
    private TableColumn<RequestData, String> modelcolumn3;

    @FXML
    private TableColumn<RequestData, String> problemcolumn3;

    @FXML
    private TableColumn<RequestData, String> timecolumn3;

    @FXML
    private TableColumn<RequestData, String> statuscolumn3;

    @FXML
    private TableColumn<RequestData, String> progresscolumn3;

    @FXML
    private TextField acceptedid;

    @FXML
    private TextField apptime;

    @FXML
    private TextField acceptedid1;

    @FXML
    private TextField progressInput;

    @FXML
    private TabPane managerTabPane;

    //feedback table

    @FXML
    private TableView<FeedbackData> feedbacktable;

    @FXML
    private TableColumn<FeedbackData, Integer> feedRating;

    @FXML
    private TableColumn<FeedbackData, String> feedFeedback;

    @FXML
    private TableColumn<FeedbackData, String> feedUsername;

    @FXML
    private Label rating;
    //buttons

    @FXML
    private TextField requestid;

    private dbConnect dc;
    private ObservableList<RequestData> data;
    private ObservableList<RequestData> data2;
    private ObservableList<RequestData> data3;
    private ObservableList<FeedbackData> data4;

    private String sql = "SELECT * FROM requests WHERE status LIKE 'Pending'";
    private String sql2 = "SELECT * FROM requests WHERE status LIKE 'Accepted'";
    private String sql3 = "SELECT * FROM requests WHERE status LIKE 'Refused'";
    private String sql4 = "SELECT * FROM feedback";
    private String sqlRating = "SELECT AVG(rating) FROM feedback";

    public void initialize(URL url, ResourceBundle rb){
        this.dc = new dbConnect();
    }

    @FXML
    private void loadPendingData(ActionEvent event) throws SQLException {
        try{
            Connection conn = dbConnect.connect(Config.SQCONN);
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                this.data.add(new RequestData(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7
                ),rs.getString(8)));

            }

        }catch (SQLException e){
            System.err.println("Error"+ e);
        }

        this.idcolumn.setCellValueFactory(new PropertyValueFactory<RequestData,Integer>("ID"));
        this.usernamecolumn.setCellValueFactory(new PropertyValueFactory<RequestData,String>("username"));
        this.brandcolumn.setCellValueFactory(new PropertyValueFactory<RequestData,String>("brand"));
        this.modelcolumn.setCellValueFactory(new PropertyValueFactory<RequestData,String>("model"));
        this.problemcolumn.setCellValueFactory(new PropertyValueFactory<RequestData,String>("problem"));
        this.timecolumn.setCellValueFactory(new PropertyValueFactory<RequestData,String>("interval"));
        this.statuscolumn.setCellValueFactory(new PropertyValueFactory<RequestData,String>("status"));
        this.progresscolumn.setCellValueFactory(new PropertyValueFactory<RequestData,String>("progress"));


        this.pendingtable.setItems(null);
        this.pendingtable.setItems(this.data);
    }


    @FXML
    private void loadAcceptedData(ActionEvent event) throws SQLException {
        try{
            Connection conn = dbConnect.connect(Config.SQCONN);
            this.data2 = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sql2);
            while(rs.next()){
                this.data2.add(new RequestData(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7
                ),rs.getString(8)));

            }

        }catch (SQLException e){
            System.err.println("Error"+ e);
        }

        this.idcolumn2.setCellValueFactory(new PropertyValueFactory<RequestData,Integer>("ID"));
        this.usernamecolumn2.setCellValueFactory(new PropertyValueFactory<RequestData,String>("username"));
        this.brandcolumn2.setCellValueFactory(new PropertyValueFactory<RequestData,String>("brand"));
        this.modelcolumn2.setCellValueFactory(new PropertyValueFactory<RequestData,String>("model"));
        this.problemcolumn2.setCellValueFactory(new PropertyValueFactory<RequestData,String>("problem"));
        this.timecolumn2.setCellValueFactory(new PropertyValueFactory<RequestData,String>("interval"));
        this.statuscolumn2.setCellValueFactory(new PropertyValueFactory<RequestData,String>("status"));
        this.progresscolumn2.setCellValueFactory(new PropertyValueFactory<RequestData,String>("progress"));


        this.acceptedtable.setItems(null);
        this.acceptedtable.setItems(this.data2);
    }

    @FXML
    private void loadRefusedData(ActionEvent event) throws SQLException {
        try{
            Connection conn = dbConnect.connect(Config.SQCONN);
            this.data3 = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sql3);
            while(rs.next()){
                this.data3.add(new RequestData(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7
                ),rs.getString(8)));

            }

        }catch (SQLException e){
            System.err.println("Error"+ e);
        }

        this.idcolumn3.setCellValueFactory(new PropertyValueFactory<RequestData,Integer>("ID"));
        this.usernamecolumn3.setCellValueFactory(new PropertyValueFactory<RequestData,String>("username"));
        this.brandcolumn3.setCellValueFactory(new PropertyValueFactory<RequestData,String>("brand"));
        this.modelcolumn3.setCellValueFactory(new PropertyValueFactory<RequestData,String>("model"));
        this.problemcolumn3.setCellValueFactory(new PropertyValueFactory<RequestData,String>("problem"));
        this.timecolumn3.setCellValueFactory(new PropertyValueFactory<RequestData,String>("interval"));
        this.statuscolumn3.setCellValueFactory(new PropertyValueFactory<RequestData,String>("status"));
        this.progresscolumn3.setCellValueFactory(new PropertyValueFactory<RequestData,String>("progress"));


        this.refusedtable.setItems(null);
        this.refusedtable.setItems(this.data3);
    }

    @FXML
    private void acceptRequest(ActionEvent event) throws SQLException {
        Connection conn = dbConnect.connect(Config.SQCONN);
        PreparedStatement ps = null;
        try{
            String sqlUpdate = "UPDATE requests SET status = ? WHERE id =  ?";
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1,"Accepted");
            ps.setString(2,this.requestid.getText());
            ps.execute();
            loadPendingData(event);
            loadAcceptedData(event);
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }
    }

    @FXML
    private void refuseRequest(ActionEvent event) throws SQLException {
        Connection conn = dbConnect.connect(Config.SQCONN);
        PreparedStatement ps = null;
        try{
            String sqlUpdate2 = "UPDATE requests SET status = ? WHERE id =  ?";
            ps = conn.prepareStatement(sqlUpdate2);
            ps.setString(1,"Refused");
            ps.setString(2,this.requestid.getText());
            ps.execute();
            loadPendingData(event);
            loadRefusedData(event);
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }
    }

    @FXML
    private void updateTimeSlot(ActionEvent event) throws SQLException {
        Connection conn = dbConnect.connect(Config.SQCONN);
        PreparedStatement ps = null;
        try{
            String sqlUpdate3 = "UPDATE requests SET interval = ? WHERE id =  ?";
            ps = conn.prepareStatement(sqlUpdate3);
            ps.setString(1,this.apptime.getText());
            ps.setString(2,this.acceptedid.getText());
            ps.execute();
            loadAcceptedData(event);
            this.apptime.setText("");
            this.acceptedid.setText("");
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }
    }

    @FXML
    private void updateProgress(ActionEvent event) throws SQLException {
        Connection conn = dbConnect.connect(Config.SQCONN);
        PreparedStatement ps = null;
        try{
            String sqlUpdate4 = "UPDATE requests SET progress = ? WHERE id =  ?";
            ps = conn.prepareStatement(sqlUpdate4);
            ps.setString(1,this.progressInput.getText());
            ps.setString(2,this.acceptedid1.getText());
            ps.execute();
            loadAcceptedData(event);
            this.progressInput.setText("");
            this.acceptedid1.setText("");
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }
    }

    @FXML
    private void logout2(ActionEvent event) {
        try{
            Stage stage = (Stage)managerTabPane.getScene().getWindow();
            Parent viewLoginPage = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewLoginPage);
            stage.setScene(scene);
            stage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void loadFeedback(ActionEvent event) throws SQLException {
        try{
            Connection conn = dbConnect.connect(Config.SQCONN);
            this.data4 = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sql4);
            while(rs.next()){
                this.data4.add(new FeedbackData(rs.getString(1),rs.getString(2),rs.getInt(3)));

            }

        }catch (SQLException e){
            System.err.println("Error"+ e);
        }

        this.feedUsername.setCellValueFactory(new PropertyValueFactory<FeedbackData,String>("username"));
        this.feedFeedback.setCellValueFactory(new PropertyValueFactory<FeedbackData,String>("review"));
        this.feedRating.setCellValueFactory(new PropertyValueFactory<FeedbackData,Integer>("rating"));

        this.feedbacktable.setItems(null);
        this.feedbacktable.setItems(this.data4);
    }

    private double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
    @FXML
    private void showRating(ActionEvent event) throws SQLException {
        try{
            PreparedStatement pst;
            ResultSet rs;
            Connection conn = dbConnect.connect(Config.SQCONN);
            pst = conn.prepareStatement(sqlRating);
            rs = pst.executeQuery();
            if(rs.next()){
                String avg = rs.getString("avg(rating)");
                double i = Float.parseFloat(avg);
                double z = roundTwoDecimals(i);
                String a = Double.toString(z);
                this.rating.setText(a);
                dbConnect.closeConnection();
            }
        }catch (SQLException e){
            System.err.println("Error"+ e);
        }
    }
}
