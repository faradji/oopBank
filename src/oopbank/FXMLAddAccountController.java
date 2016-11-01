package oopbank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class FXMLAddAccountController{
    
    @FXML
    Button btnAddAccount;
    
    @FXML
    Button btnCloseAddAccount;
    
    @FXML
    RadioButton btnRadioSavings, btnRadioCredit;
    
    @FXML
    public void clickedAddAccount(){
        //hämta plats i customer array som den valda kunden finns, sätt det lika med en lokal variabel
        //som vi sedan kan använda
        Customer selectedcustomer = BankLogic.getCustomerList().get(FXMLStartController.lvCustomerChoice);
        selectedcustomer.getpNr();
        OopBank.banklogic.addSavingsAccount(983080298L, 200);
        
    }
    
    @FXML
    public void clickedCloseAddAccount(){
        Stage tempStage = (Stage)btnCloseAddAccount.getScene().getWindow();
        tempStage.close();
    }
    
    @FXML
    public void handleRadioSavings(ActionEvent event)
    {
        if(btnRadioSavings.isSelected())
        {
            btnRadioCredit.setSelected(false);
        }
        if(btnRadioSavings.isSelected() == false)
        {
            btnRadioSavings.setSelected(true);
        }
        
    }
    
    @FXML
    public void handleRadioCredit(ActionEvent event)
    {
        if(btnRadioCredit.isSelected())
        {
            btnRadioSavings.setSelected(false);
            
        }
         if(btnRadioCredit.isSelected() == false)
        {
            btnRadioCredit.setSelected(true);
        }
    }
    @FXML
    public void initialize() {
    
    }    
    
}