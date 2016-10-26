package oopbank;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLCustomerInfoController{
    
    @FXML
    public void clickedAddAccount() throws IOException{
        Stage accountStage = new Stage();
        Scene accountScene = new Scene(FXMLLoader.load(getClass()
                .getResource("FXMLAddAccount.fxml")));
       
        accountStage.setScene(accountScene);
        accountStage.show();
        
    }
    
    @FXML
    public void initialize() {
        
    }    
    
}
