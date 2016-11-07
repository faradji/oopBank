package oopbank;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLAddAccountController {
    
    private BankLogic banklogic= BankLogic.getInstance();
    @FXML
    Button btnAddAccount;

    @FXML
    Button btnCloseAddAccount;

    @FXML
    RadioButton btnRadioSavings, btnRadioCredit;

    @FXML
    TextField depositfield;

    @FXML
    Label errormessage;

    @FXML
    public void clickedAddAccount() {
        //hämta plats i customer array som den valda kunden finns, sätt det lika med en lokal variabel
        //som vi sedan kan använda
        Customer selectedcustomer = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice);
        if (btnRadioSavings.isSelected() == true) {
            double value = 0;
  
            try {
                value = Double.parseDouble(depositfield.getText());
                if (value >= 0 && depositfield.getText() != null) {
                    int newaccountno = banklogic.addSavingsAccount(selectedcustomer.getpNr(), value);
                    SavingsAccount tempsavingsaccount;
                    for (int i = 0; i < banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().size(); i++) {
                        if (banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(i).getAccountNo() == newaccountno) {
                            tempsavingsaccount = (SavingsAccount)banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(i);
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Account created");
                            alert.setHeaderText("A savings account has been created");
                            alert.setContentText("Balance: " + tempsavingsaccount.getBalance()+ " SEK\nInterest: " + tempsavingsaccount.getInterest()
                            + " %\nAccount No: " + newaccountno);

                            alert.showAndWait();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                errormessage.setText("Not a valid sum, try again.");
            }
        }
        if (btnRadioCredit.isSelected() == true) {
            int newaccountno = banklogic.addCreditAccount(selectedcustomer.getpNr());
            CreditAccount tempcreditaccount;
                    for (int i = 0; i < banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().size(); i++) {
                        if (banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(i).getAccountNo() == newaccountno) {
                            tempcreditaccount = (CreditAccount)banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(i);
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Account created");
                            alert.setHeaderText("A credit account has been created");
                            alert.setContentText("Balance: " + tempcreditaccount.getBalance()+ " SEK\nInterest: " + tempcreditaccount.getInterest()
                            + " %\nAccount No: " + newaccountno  + "\nCredit limit: " + tempcreditaccount.getLimit());

                            alert.showAndWait();
                            break;
                        }
                    }
        }
    }

    @FXML
    public void clickedCloseAddAccount() {
        Stage tempStage = (Stage) btnCloseAddAccount.getScene().getWindow();
        
        tempStage.close();
    }

    @FXML
    public void handleRadioSavings(ActionEvent event
    ) {
        if (btnRadioSavings.isSelected()) {
            btnRadioCredit.setSelected(false);
        }
        if (btnRadioSavings.isSelected() == false) {
            btnRadioSavings.setSelected(true);
        }

    }

    @FXML
    public void handleRadioCredit(ActionEvent event
    ) {
        if (btnRadioCredit.isSelected()) {
            btnRadioSavings.setSelected(false);

        }
        if (btnRadioCredit.isSelected() == false) {
            btnRadioCredit.setSelected(true);
        }
    }

    @FXML
    public void initialize() {

    }

}
