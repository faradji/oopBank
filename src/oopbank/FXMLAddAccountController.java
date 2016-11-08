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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FXMLAddAccountController {

    private BankLogic banklogic = BankLogic.getInstance();
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
    //Hämtar information från föregående scen angående vilken kund som är vald, kontrollerar
    //sedan vilken typ av konto som är valt och skapar därefter ett konto av rätt typ
    public void clickedAddAccount() {
        Customer selectedcustomer = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice);
        if (btnRadioSavings.isSelected() == true) {
            double value = 0;
            //try catch som ser till att värdet i fältet man kan ange saker i är en double
            try {
                value = Double.parseDouble(depositfield.getText());
                if (value >= 0 && depositfield.getText() != null) {
                    int newaccountno = banklogic.addSavingsAccount(selectedcustomer.getpNr(), value);
                    SavingsAccount tempsavingsaccount;
                    for (int i = 0; i < banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().size(); i++) {
                        if (banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(i).getAccountNo() == newaccountno) {
                            tempsavingsaccount = (SavingsAccount) banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(i);
                            FXMLCustomerInfoController.obsAccountList.add(tempsavingsaccount);
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Account created");
                            alert.setHeaderText("A savings account has been created");
                            alert.setContentText("Balance: " + tempsavingsaccount.getBalance() + " SEK\nInterest: " + tempsavingsaccount.getInterest()
                                    + " %\nAccount No: " + newaccountno);

                            alert.showAndWait();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                errormessage.setTextFill(Color.RED);
                errormessage.setText("Not a valid sum, try again.");
            }
        }
        if (btnRadioCredit.isSelected() == true) {
            int newaccountno = banklogic.addCreditAccount(selectedcustomer.getpNr());
            CreditAccount tempcreditaccount;
            for (int i = 0; i < banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().size(); i++) {
                if (banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(i).getAccountNo() == newaccountno) {
                    tempcreditaccount = (CreditAccount) banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(i);
                    FXMLCustomerInfoController.obsAccountList.add(tempcreditaccount);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Account created");
                    alert.setHeaderText("A credit account has been created");
                    alert.setContentText("Balance: " + tempcreditaccount.getBalance() + " SEK\nInterest: " + tempcreditaccount.getInterest()
                            + " %\nAccount No: " + newaccountno + "\nCredit limit: " + tempcreditaccount.getLimit());

                    alert.showAndWait();
                    break;
                }
            }
        }
    }

    //Stänger den aktiva rutan
    @FXML
    public void clickedCloseAddAccount() {
        Stage tempStage = (Stage) btnCloseAddAccount.getScene().getWindow();

        tempStage.close();
    }

    //Hanterar radioknapparna, om savings ej är vald sätts den som markerad
    //Är den redan markerad sätts den som markerad igen
    @FXML
    public void handleRadioSavings(ActionEvent event
    ) {
        if (btnRadioSavings.isSelected()) {
            btnRadioCredit.setSelected(false);
            depositfield.setVisible(true);
            errormessage.setTextFill(Color.BLACK);
            errormessage.setText("Enter a deposit if you are creating a savings account.");
        }
        if (btnRadioSavings.isSelected() == false) {
            btnRadioSavings.setSelected(true);
        }

    }
//Hanterar radioknapparna, om credit ej är vald sätts den som markerad
//Är den redan markerad sätts den som markerad igen

    @FXML
    public void handleRadioCredit(ActionEvent event
    ) {
        if (btnRadioCredit.isSelected()) {
            btnRadioSavings.setSelected(false);
            depositfield.setVisible(false);
            errormessage.setText(" ");
        }
        if (btnRadioCredit.isSelected() == false) {
            btnRadioCredit.setSelected(true);
        }
    }

    @FXML
    public void initialize() {

    }

}
