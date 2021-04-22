package gsmapp;

import Config.Config;
import Encryption.MD5;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
        String code = codeInput.getText();
        String role = comboboxInput.getValue().toString();
        String hashed_password = "";

        if(!password.equals(confirmed_password)) {
            //print
            return;
        }
        hashed_password = MD5.getMd5(password);


        if(role.equals("Manager") && code.equals(Config.ACESS_CODE)) {
            SignupModel.registerUser(firstname,lastname,username,hashed_password,role,code);
        }
        else if(role.equals("Client")) {
            SignupModel.registerUser(firstname,lastname,username,hashed_password,role,"");
        }

    }

}
