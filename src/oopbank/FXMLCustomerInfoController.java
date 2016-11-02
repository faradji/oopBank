package oopbank;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLCustomerInfoController {
    
    public static int accountChoice = 0;
    
    //ListView
    @FXML
    private ListView accountList;

    //Observablelist with accounts to populate listview
    ObservableList<Account> obsAccountList;

    // Buttons
    @FXML
    private Button btnChangeCustomerInfo;

    @FXML
    private Button btnAccountInfo;

    @FXML
    private Button btnAddAccount;

    @FXML
    private Button btnDeleteAccount;

    @FXML
    private Button btnBackCustomerInfo;

    //Labels
    @FXML
    private Label lblFullName;

    @FXML
    private Label lblSSN;

    // Creates new stage
    @FXML
    private void clickedChangeCustomerInformation() throws IOException {
        Stage changeCustomerInformationStage = new Stage();
        Scene changeCustomerInformationScene
                = new Scene(FXMLLoader.load(getClass()
                        .getResource("FXMLChangeCustomerInfo.fxml")));

        changeCustomerInformationStage.setScene(changeCustomerInformationScene);
        changeCustomerInformationStage.initModality(Modality.APPLICATION_MODAL);
        changeCustomerInformationStage.setTitle("Change customerinformation");
        changeCustomerInformationStage.show();
    }

    // Load new scene into existing stage
    @FXML
    private void clickedAccountInformation() throws IOException {
        accountChoice = accountList.getSelectionModel().getSelectedIndex();
        System.out.println("Konto index: " + accountChoice);
        
        Stage accountInformationStage
                = (Stage) btnAccountInfo.getScene().getWindow();
        Scene accountInformationScene = new Scene(FXMLLoader.load(getClass()
                .getResource("FXMLAccountInfo.fxml")));

        accountInformationStage.setTitle("Accountinformation");
        accountInformationStage.setScene(accountInformationScene);
    }

    // Creates new stage
    @FXML
    private void clickedAddAccount() throws IOException {

        Stage addAccountStage = new Stage();
        Scene addAccountScene = new Scene(FXMLLoader.load(getClass()
                .getResource("FXMLAddAccount.fxml")));

        addAccountStage.setScene(addAccountScene);
        addAccountStage.initModality(Modality.APPLICATION_MODAL);
        addAccountStage.setTitle("Add account");
        addAccountStage.show();
    }

    @FXML
    private void clickedDeleteAccount() {
        System.out.println("Account deleted");
    }

    // Closes stage
    @FXML
    private void clickedBackCustomerInformation() {
        Stage tempStage = (Stage) btnBackCustomerInfo.getScene().getWindow();
        tempStage.close();
    }

    public ListView getAccountList() {
        return accountList;
    }

    @FXML
    public void initialize() {
    
        System.out.println(FXMLStartController.lvCustomerChoice);
        
        //Customer name display
        lblFullName.setText(OopBank.banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getFirstName() + " " + OopBank.banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getLastName());
        
        // Customer SSN display
        lblSSN.setText(String.valueOf(OopBank.banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getpNr()));
        
        //Fill listView with accountList
        obsAccountList = FXCollections.
                observableArrayList(OopBank.banklogic.getCustomerList()
                        .get(FXMLStartController.lvCustomerChoice).getAccountList());
        accountList.setItems(obsAccountList);
    }


}
