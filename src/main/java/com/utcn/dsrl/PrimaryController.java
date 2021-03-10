package com.utcn.dsrl;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        AppController.setRoot("secondary");
    }
}
