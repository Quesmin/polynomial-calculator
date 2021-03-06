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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class AppController implements Initializable {


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
    @FXML
    private Text invalidInput;

    private TextField selectedTextField;
    private Button selectedOperation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedTextField =  firstPol;
        selectedOperation = buttonAdd;
        selectedOperation.setStyle("-fx-background-color: blue");

        invalidInput.setVisible(false);

    }



    @FXML
    void selectTextField(MouseEvent event) {
        selectedTextField = (TextField) event.getSource();
    }

    @FXML
    void getInputButton(MouseEvent event) {
        selectedTextField.setText((selectedTextField.getText() + ((Button)event.getSource()).getText()).toLowerCase());
        selectedTextField.positionCaret(selectedTextField.getText().length());
    }

    @FXML
    void clearTextField(MouseEvent event) {
        selectedTextField.setText("");
    }

    @FXML
    void selectOperation(MouseEvent event) {

        selectedOperation.setStyle("-fx-background-color: #3e3e3e");
        selectedOperation = (Button) event.getSource();
        selectedOperation.setStyle("-fx-background-color: blue");

        if(selectedOperation.getText().equals("INTEGRATE") || selectedOperation.getText().equals("DIFF")){
            secondPol.setDisable(true);
        } else {
            secondPol.setDisable(false);
        }

        Controller.result = null;
        resultPol.setText("");
        invalidInput.setVisible(false);

    }

    private boolean areInputsValid(){

        if(selectedOperation.getText().equals("INTEGRATE") || selectedOperation.getText().equals("DIFF")){
            if(!firstPol.getText().isEmpty() && Controller.isGoodInput(firstPol.getText())){
                Controller.pol1 = new Polynomial(firstPol.getText());
                return true;
            } else {
                return false;
            }

        } else {
            if(!firstPol.getText().isEmpty() && Controller.isGoodInput(firstPol.getText()) &&
                    !secondPol.getText().isEmpty() && Controller.isGoodInput(secondPol.getText())){
                Controller.pol1 = new Polynomial(firstPol.getText());
                Controller.pol2 = new Polynomial(secondPol.getText());
                return true;
            } else {
                return false;
            }

        }
    }

    @FXML
    void getResult(MouseEvent event) {
        Controller.result = null;
        resultPol.setText("");
        invalidInput.setVisible(false);

        if(areInputsValid()){

            switch(selectedOperation.getText()) {
                case "ADD":
                    Controller.result = new Polynomial(Controller.add(Controller.pol1, Controller.pol2));
                    System.out.println(Controller.pol1.toString() + '\n');
                    System.out.println(Controller.pol2.toString() + '\n');
                    break;
                case "SUBTRACT":
                    Controller.result = new Polynomial(Controller.subtract(Controller.pol1, Controller.pol2));
                    System.out.println(Controller.pol1.toString() + '\n');
                    System.out.println(Controller.pol2.toString() + '\n');
                    break;
                case "DIVIDE":
                    if(!secondPol.getText().equals("0") && !firstPol.getText().equals("0")){
                        Controller.result = new Polynomial(Controller.divide(Controller.pol1, Controller.pol2));
                    } else {
                        resultPol.setText("");
                        invalidInput.setVisible(true);
                    }
                    System.out.println(Controller.pol1.toString() + '\n');
                    System.out.println(Controller.pol2.toString() + '\n');
                    break;
                case "MODULO":
                    if(!secondPol.getText().equals("0") && !firstPol.getText().equals("0")){
                        Controller.result = new Polynomial(Controller.modulo(Controller.pol1, Controller.pol2));
                    } else {
                        resultPol.setText("");
                        invalidInput.setVisible(true);
                    }
                    System.out.println(Controller.pol1.toString() + '\n');
                    System.out.println(Controller.pol2.toString() + '\n');
                    break;
                case "MULTIPLY":
                    Controller.result = new Polynomial(Controller.multiply(Controller.pol1, Controller.pol2));
                    System.out.println(Controller.pol1.toString() + '\n');
                    System.out.println(Controller.pol2.toString() + '\n');
                    break;
                case "INTEGRATE":
                    Controller.result = new Polynomial(Controller.integrate(Controller.pol1));
                    System.out.println(Controller.pol1.toString() + '\n');
                    System.out.println(Controller.pol2.toString() + '\n');
                    break;
                case "DIFF":
                    Controller.result = new Polynomial(Controller.differentiate(Controller.pol1));
                    System.out.println(Controller.pol1.toString() + '\n');
                    System.out.println(Controller.pol2.toString() + '\n');
                    break;
                default:
                    System.out.println("no match");

            }

            try{
                resultPol.setText(Controller.result.toString());
            }catch (Exception NullPointerException){
                System.out.println("null result");
            }
        } else {
            invalidInput.setVisible(true);
        }

    }
}