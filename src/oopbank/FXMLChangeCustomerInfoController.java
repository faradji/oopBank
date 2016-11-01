/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FXMLChangeCustomerInfoController {

    @FXML
    TextField txtFieldFirstName;

    @FXML
    TextField txtFieldLastName;

    private void clickedSaveChanges() {

        /*if (txtFieldFirstName.getText().equals("") 
                || txtFieldLastName.getText().equals("")) {
            System.out.println("All fields needs to be filled");
        } else {
            BankLogic.getCustomerList().get(FXMLStartController.choice)
                    .setFirstName(txtFieldFirstName.getText());

            BankLogic.getCustomerList().get(FXMLStartController.choice)
                    .setLastName(txtFieldLastName.getText());
        }
*/
    }

    @FXML
    public void initialize() {

    }

}
