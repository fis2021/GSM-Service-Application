package gsmapp;

import Config.Config;
import Encryption.MD5;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private TextField fnameInput;

    @FXML
    private TextField lnameInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField confirmPassInput;

    @FXML
    private ComboBox<option> comboboxInput;

    @FXML
    private TextField codeInput;

    @FXML
    private Button createAcc;

    @FXML
    private Label passNotMatchLabel;

    @FXML
    private Label usernameWarning;

    @FXML
    private Label passwordWarning;

    @FXML
    private Label roleWarning;

    @FXML
    private Label accessWarning;

    @FXML
    private Label firstNameWarning;

    @FXML
    private Label lastNameWarning;

    //@Override
    public void initialize(URL url, ResourceBundle rb){

        this.comboboxInput.setItems(FXCollections.observableArrayList(option.values()));
    }

    public void onSignup(ActionEvent event) {


        String firstname = fnameInput.getText();
        String lastname = lnameInput.getText();
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String confirmed_password = confirmPassInput.getText();
        String role = "";
        String code = codeInput.getText();
        if (comboboxInput.getValue()!=null) {
            role = comboboxInput.getValue().toString();
        }else
        {
            this.roleWarning.setText("*role is required");
            return;
        }
        String hashed_password = "";

        if(firstname.length()<1) {
            this.firstNameWarning.setText("*first name is required");
            return;
        }

        if(lastname.length()<1) {
            this.lastNameWarning.setText("*last name is required");
            return;
        }

        if(!password.equals(confirmed_password)) {
            this.passNotMatchLabel.setText("Passwords do not match!");
            return;
        }

        if(username.length()<4) {
            this.usernameWarning.setText("*must have at least 4 characters");
            return;
        }

        if(password.length()<6) {
            this.passwordWarning.setText("*must have at least 6 characters");
            return;
        }

        if(role.equals("Manager") && code.length()!=4) {
            this.accessWarning.setText("*must have 4 characters");
            return;
        }
        if(role.equals("Manager") && !code.equals(Config.ACESS_CODE)) {
            this.accessWarning.setText("*incorrect access code");
            return;
        }

        hashed_password = MD5.getMd5(password);

        if(role.equals("Manager") && code.equals(Config.ACESS_CODE)) {
            if(SignupModel.registerUser(firstname,lastname,username,hashed_password,role,code)=="0"){
                try{
                    Stage stage = (Stage)passNotMatchLabel.getScene().getWindow();
                    Parent viewLoginPage = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
                    Scene scene = new Scene(viewLoginPage);
                    stage.setScene(scene);
                    stage.show();

                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }else{
                this.usernameWarning.setText("Username already exists!");
            }

        }
        else if(role.equals("Client")) {
            if(SignupModel.registerUser(firstname,lastname,username,hashed_password,role,"")=="0"){
                try{
                    Stage stage = (Stage)passNotMatchLabel.getScene().getWindow();
                    Parent viewLoginPage = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
                    Scene scene = new Scene(viewLoginPage);
                    stage.setScene(scene);
                    stage.show();

                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }else{
                this.usernameWarning.setText("Username already exists!");
            }
        }

    }

    public void goBack(ActionEvent event){
        try{
            Stage stage = (Stage)passNotMatchLabel.getScene().getWindow();
            Parent viewLoginPage = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewLoginPage);
            stage.setScene(scene);
            stage.show();
        }catch (Exception localException) {

        }
    }

}
