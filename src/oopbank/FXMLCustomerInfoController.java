package oopbank;

import java.io.IOException;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLCustomerInfoController {

    private BankLogic banklogic = BankLogic.getInstance();

    public static int accountChoice = 0;

    //ListView
    @FXML
    private ListView accountList;

    @FXML
    private Label listViewAlert;

    //Observablelist with accounts to populate listview
    public static ObservableList<Account> obsAccountList;

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
    private  Label lblSSN;
    
    static long lvPnr;

    // Skapar nytt fönster
    @FXML
    private void clickedChangeCustomerInformation() throws IOException {
       lvPnr =parseLong(lblSSN.getText()+"L");
        Stage changeCustomerInformationStage = new Stage();
        Scene changeCustomerInformationScene
                = new Scene(FXMLLoader.load(getClass()
                        .getResource("FXMLChangeCustomerInfo.fxml")));

        changeCustomerInformationStage.setScene(changeCustomerInformationScene);
        changeCustomerInformationStage.initModality(Modality.APPLICATION_MODAL);
        changeCustomerInformationStage.setTitle("Change customer information");
        changeCustomerInformationStage.show();
    }

    // Skapar ny scen i nuvarande fönster
    @FXML
    private void clickedAccountInformation() throws IOException {
        try {
            accountChoice = accountList.getSelectionModel().getSelectedIndex();

            Stage accountInformationStage
                    = (Stage) btnAccountInfo.getScene().getWindow();
            Scene accountInformationScene = new Scene(FXMLLoader.load(getClass()
                    .getResource("FXMLAccountInfo.fxml")));

            accountInformationStage.setTitle("Account information");
            accountInformationStage.setScene(accountInformationScene);
        } catch (Exception e) {
            listViewAlert.setText("Please, choose something in the list");
        }
    }

    // Skapar nytt fönster
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
        String removedAccount = "";

        try {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete "
                    + banklogic.getCustomerList().
                    get(FXMLStartController.lvCustomerChoice)
                    .getAccountList()
                    .get(accountList.getSelectionModel().getSelectedIndex())
                    .getAccountNo() + " "
                    + banklogic.getCustomerList().
                    get(FXMLStartController.lvCustomerChoice)
                    .getAccountList()
                    .get(accountList.getSelectionModel().getSelectedIndex())
                    .getAccountType());
            alert.setContentText("Are you sure?");
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(yes, no);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == yes) {
                removedAccount = banklogic.closeAccount(
                        banklogic.getCustomerList().
                        get(FXMLStartController.lvCustomerChoice).getpNr(),
                        banklogic.getCustomerList().
                        get(FXMLStartController.lvCustomerChoice)
                        .getAccountList()
                        .get(accountList.getSelectionModel().getSelectedIndex())
                        .getAccountNo());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Info");
                alert2.setHeaderText(null);
                alert2.setContentText(removedAccount);
                alert2.showAndWait();
            } else {

            }
            removedAccount = "";
            refresh();
        } catch (Exception e) {
            listViewAlert.setText("Please, choose something in the list");
        }
    }

    // Stänger fönstret
    @FXML
    private void clickedBackCustomerInformation() {
        Stage tempStage = (Stage) btnBackCustomerInfo.getScene().getWindow();
        tempStage.close();
    }

    @FXML
    public void clickedPrintCustomerDetails() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Customer details");
        ArrayList tempCustomer = banklogic.getCustomer(banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getpNr());
        String contentText = "";

        for (int i = 0; i < tempCustomer.size(); i++) {
            contentText += tempCustomer.get(i);
            contentText += "\n";

        }

        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    public void refresh() {

        // Customer SSN display
        lblSSN.setText(String.valueOf(banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getpNr()));

        //Fill listView with accountList
        obsAccountList = FXCollections.
                observableArrayList(banklogic.getCustomerList()
                        .get(FXMLStartController.lvCustomerChoice).getAccountList());
        accountList.setItems(obsAccountList);
        
        FXMLStartController.getNameChange().set(banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getFirstName() + " " + banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getLastName());
        lblFullName.textProperty().bind(FXMLStartController.getNameChange());

        listViewAlert.setText("");
    }

  
    @FXML
    public void initialize() {
        
        refresh();
    }

}
