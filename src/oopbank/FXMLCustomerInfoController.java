package oopbank;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLCustomerInfoController{
    @FXML
    Button btnAccountInformation;
    
    @FXML
    private void clickedAddAccount() throws IOException{
        Stage accountStage = new Stage();
        Scene accountScene = new Scene(FXMLLoader.load(getClass()
                .getResource("FXMLAddAccount.fxml")));
       
        accountStage.setScene(accountScene);
        accountStage.initModality(Modality.APPLICATION_MODAL);
        accountStage.show();       
    }
    
    @FXML
    private void clickedAccountInformation() throws IOException{
        Stage accountInformationStage = 
                (Stage)btnAccountInformation.getScene().getWindow();    
        Scene accountInformationScene = new Scene(FXMLLoader.load(getClass()
                .getResource("FXMLAccountInfo.fxml")));
        
        accountInformationStage.setScene(accountInformationScene);                
    }
    
    @FXML
    public void initialize() {
        
    }    
    
}
