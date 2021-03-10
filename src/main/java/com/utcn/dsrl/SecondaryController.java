package com.utcn.dsrl;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        AppController.setRoot("primary");
    }
}