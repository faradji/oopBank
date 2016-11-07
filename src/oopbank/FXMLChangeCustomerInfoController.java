/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLChangeCustomerInfoController {

    private BankLogic banklogic = BankLogic.getInstance();
    ;
    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private Label lblSSN;

    @FXML
    private Button btnSaveChanges, btnClose;

    @FXML
    private void clickedSaveChanges() {

        try {
            if(txtFieldFirstName.getText().matches("[0-9]")){
                throw new Exception();
            
        }
            banklogic.changeCustomerName(txtFieldFirstName.getText(),
                    txtFieldLastName.getText(),
                    banklogic.getCustomerList()
                    .get(FXMLStartController.lvCustomerChoice)
                    .getpNr());

            FXMLStartController.obsCustomerList.clear();
            FXMLStartController.obsCustomerList.addAll(banklogic.getCustomerList());
            FXMLStartController.getNameChange().set(txtFieldFirstName.getText()
                    + " " + txtFieldLastName.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Changes saved");
            alert.showAndWait();

            Stage tempStage = (Stage) btnSaveChanges.getScene().getWindow();
            tempStage.close();
        } catch (Exception e) {
            System.out.println("Fel");
        }

    }

    @FXML
    private void btnCloseClicked() {
        Stage tempStage = (Stage) btnClose.getScene().getWindow();
        tempStage.close();
    }

    @FXML
    public void initialize() {
        txtFieldFirstName.setText(banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getFirstName());

        txtFieldLastName.setText(banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getLastName());

        lblSSN.setText(String.valueOf(banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getpNr()));

    }

}
