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

        OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).setFirstName(txtFieldFirstName.getText());
        OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).setLastName(txtFieldLastName.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Changes saved");
        alert.showAndWait();

        Stage tempStage = (Stage) btnSaveChanges.getScene().getWindow();
        tempStage.close();
    }

    @FXML
    private void btnCloseClicked() {
        Stage tempStage = (Stage) btnClose.getScene().getWindow();
        tempStage.close();
    }

    @FXML
    public void initialize() {
        txtFieldFirstName.setText(OopBank.banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getFirstName());

        txtFieldLastName.setText(OopBank.banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getLastName());

        lblSSN.setText(String.valueOf(OopBank.banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getpNr()));

    }

}
