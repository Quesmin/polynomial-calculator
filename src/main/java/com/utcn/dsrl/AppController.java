package com.utcn.dsrl;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class AppController extends Application implements Initializable {

    private static Scene scene;

    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button buttonX;
    @FXML
    private Button buttonPow;
    @FXML
    private Button buttonPlus;
    @FXML
    private Button buttonMinus;
    @FXML
    private Button buttonDel;

    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonSubtract;
    @FXML
    private Button buttonIntegrate;
    @FXML
    private Button buttonDiff;
    @FXML
    private Button buttonDivide;
    @FXML
    private Button buttonModulo;
    @FXML
    private Button buttonMultiply;
    @FXML
    private Button buttonResult;

    @FXML
    private TextField firstPol;
    @FXML
    private TextField secondPol;
    @FXML
    private TextField resultPol;

    private TextField selectedTextField;
    private Button selectedOperation;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.setTitle("PolyCalc");
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedTextField =  firstPol;
        selectedOperation = buttonAdd;
        selectedOperation.setStyle("-fx-background-color: blue");

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @FXML
    void selectTextField(MouseEvent event) {
        selectedTextField = (TextField) event.getSource();
    }

    @FXML
    void getInputButton(MouseEvent event) {
        selectedTextField.setText((selectedTextField.getText() + ((Button)event.getSource()).getText()).toLowerCase());
//        selectedTextField.requestFocus();
    }

    @FXML
    void clearTextField(MouseEvent event) {
        selectedTextField.setText("");
//        selectedTextField.requestFocus();
    }

    @FXML
    void selectOperation(MouseEvent event) {

//        selectedOperation.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        selectedOperation.setStyle("-fx-background-color: #3e3e3e");
        selectedOperation = (Button) event.getSource();
//        selectedOperation.getStyleClass().add("button-pressed");
        selectedOperation.setStyle("-fx-background-color: blue");

        if(selectedOperation.getText().equals("INTEGRATE") || selectedOperation.getText().equals("DIFF")){
            secondPol.setDisable(true);
        } else {
            secondPol.setDisable(false);
        }
    }


    public static void main(String[] args) {
        launch();
    }
}