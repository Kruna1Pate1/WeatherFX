package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * MainController
 */
public class MainController {

    @FXML
    private Button checkBtn;

    @FXML
    public void checkFun(ActionEvent e) {
        System.out.println("Button Working...");
    }
}