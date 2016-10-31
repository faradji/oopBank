package oopbank;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLCustomerInfoController {

    //ListView
    @FXML
    private ListView accountList;

    // Radiobutton
    @FXML
    private RadioButton savingsAccount;
 
    @FXML
    private RadioButton creditAccount;
    
    // Radiobuttongroup
    @FXML
    private ToggleGroup accountsToggleGroup;
    
    // Value of the radiobutton checked
    private String rbtnChecked = "";

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
                        .getResource("FXMLAddAccount.fxml")));

        changeCustomerInformationStage.setScene(changeCustomerInformationScene);
        changeCustomerInformationStage.initModality(Modality.APPLICATION_MODAL);
        changeCustomerInformationStage.setTitle("Change customerinformation");
        changeCustomerInformationStage.show();
    }
    
    // Load new scene into existing stage
    @FXML
    private void clickedAccountInformation() throws IOException {
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

    @FXML
    public void initialize() {
        
        //Group radiobuttons. Only one button can be checked.
        accountsToggleGroup =  new ToggleGroup();
        
        savingsAccount.setToggleGroup(accountsToggleGroup);
        savingsAccount.setUserData("sa");
        creditAccount.setToggleGroup(accountsToggleGroup);
        creditAccount.setUserData("ca");
    }

}
