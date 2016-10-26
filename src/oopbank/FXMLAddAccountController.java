package oopbank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLAddAccountController{
    
    @FXML
    Button backBtn;
    
    @FXML
    public void clickedBack(){
        Stage tempStage = (Stage)backBtn.getScene().getWindow();
        tempStage.close();
    }
    
    @FXML
    public void initialize() {
      
    }    
    
}
