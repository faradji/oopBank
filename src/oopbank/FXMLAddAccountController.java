package oopbank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLAddAccountController{
    
    @FXML
    Button btnAddAccount;
    
    @FXML
    Button btnBackAddAccount;
    
    @FXML
    public void clickedAddAccount(){
        System.out.println("Added account");
    }
    
    @FXML
    public void clickedBackAddAccount(){
        Stage tempStage = (Stage)btnBackAddAccount.getScene().getWindow();
        tempStage.close();
    }
    
    @FXML
    public void initialize() {
    
    }    
    
}
