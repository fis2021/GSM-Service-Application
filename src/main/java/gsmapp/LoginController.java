package gsmapp;

import Encryption.MD5;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import managers.ManagerController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Button signupButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label dbstatus;

    @FXML
    private ComboBox<option> combobox;

    @FXML
    private Button loginButton;

    //@Override
    public void initialize(URL url, ResourceBundle rb){
        if(this.loginModel.isDatabaseConnected()){
            this.dbstatus.setText("Connected");
        }else{
            this.dbstatus.setText("Not connected");
        }
        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }



    @FXML
    public void Login(ActionEvent event){
        String hashed_password = MD5.getMd5(this.password.getText());
        try{
            if(this.loginModel.isLogin(this.username.getText(), hashed_password, ((option)this.combobox.getValue()).toString())){
                switch (((option)this.combobox.getValue()).toString()) {
                    case "Manager":
                        managerLogin();
                        break;
                    case "Client":
                        clientLogin();
                        break;
                }
            }
            else{
                this.loginMessageLabel.setText("Wrong credentials");
            }

        }catch (Exception localException) {

        }
    }

    public void clientLogin(){
        try{
            Stage stage = (Stage)loginMessageLabel.getScene().getWindow();
            Parent viewClientPage = FXMLLoader.load(getClass().getClassLoader().getResource("clientDashboard.fxml"));
            Scene scene = new Scene(viewClientPage);
            stage.setScene(scene);
            stage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void managerLogin(){
        try{
            Stage stage = (Stage)loginMessageLabel.getScene().getWindow();
            Parent viewManagerDashboard = FXMLLoader.load(getClass().getClassLoader().getResource("managerDashboard.fxml"));
            Scene scene = new Scene(viewManagerDashboard);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void Signup(ActionEvent event){
        try{
            Stage stage = (Stage)loginMessageLabel.getScene().getWindow();
            Parent viewSignupPage = FXMLLoader.load(getClass().getClassLoader().getResource("signup.fxml"));
            Scene scene = new Scene(viewSignupPage);
            stage.setScene(scene);
            stage.show();
        }catch (Exception localException) {

        }
    }
}
