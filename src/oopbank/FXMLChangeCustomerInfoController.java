/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.util.HashSet;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLChangeCustomerInfoController {

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;
    
    @FXML private Label lblSSN;
    
    @FXML private Button btnSaveChanges, btnClose;

    @FXML
    private void clickedSaveChanges() 
    {
        //OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).setFirstName(txtFieldFirstName.getText());
        //Alla metoder som anropas från banklogic verkar krascha programmet
        
        System.out.println("Test");
        
        Stage tempStage = (Stage) btnSaveChanges.getScene().getWindow();
        tempStage.close();
    }
    
    @FXML
    private void btnCloseClicked()
    {
        Stage tempStage = (Stage) btnClose.getScene().getWindow();
        tempStage.close();
    }

    @FXML
    public void initialize() {
        lblSSN.setText(String.valueOf(OopBank.banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getpNr()));
    }

}
