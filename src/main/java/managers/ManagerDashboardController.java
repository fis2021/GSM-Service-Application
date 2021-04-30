package managers;

import Config.Config;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import dbUtil.dbConnect;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;

public class ManagerDashboardController implements Initializable {

    @FXML
    private TableView<RequestData> requesttable;

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

    private dbConnect dc;
    private ObservableList<RequestData> data;

    private String sql = "SELECT * FROM requests";

    public void initialize(URL url, ResourceBundle rb){
        this.dc = new dbConnect();
    }

    @FXML
    private void loadRequestData(ActionEvent event) throws SQLException {
        try{
            Connection conn = dbConnect.connect(Config.SQCONN);
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                this.data.add(new RequestData(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));

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

        this.requesttable.setItems(null);
        this.requesttable.setItems(this.data);
    }




}
