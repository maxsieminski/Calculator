package sample;

import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    public GridPane gridPane;
    @FXML
    public Label answer, operationLabel;
    @FXML
    public Button clear, negate, comma, equals, backspace;
    @FXML
    public Button add, subtract, divide, multiply;
    @FXML
    public Button zero, one, two, three, four, five, six, seven, eight, nine;

    // Operation currently executed, each operation is represented by its first letter, no operation is represented by x
    private char operation = 'x';
    private float firstNumber, secondNumber;
    private boolean equalsPressed = false;

    @FXML
    public void buttonListener(ActionEvent actionEvent) {

        Button buttonClicked = (((Button)actionEvent.getSource()));

        if (buttonClicked == clear) {
            answer.setText("0");
            operation = 'x';
            firstNumber = 0;
        }

        else if (buttonClicked == negate) {
            float number = Float.parseFloat(answer.getText());
            // If number is not decimal, it is represented as an int
            if (((int)number) == number) {
                answer.setText(Integer.toString((int)number * -1));
            }
            else {
                answer.setText(Float.toString(number * -1));
            }
        }

        else if (buttonClicked == backspace) {
            if (!answer.getText().equals("0")) {
                if (answer.getText().length() == 1) {
                    answer.setText("0");
                }
                else {
                    answer.setText(answer.getText().substring(0, answer.getText().length() - 1));
                }
            }
        }

        else if (buttonClicked == comma) {
            if (!answer.getText().contains(".")) {
                answer.setText(answer.getText() + ".");
            }
        }

        else if (buttonClicked == equals) {
            float result = 0;

            if(!equalsPressed) {
                secondNumber = Float.parseFloat(answer.getText());
            }
            else {
                firstNumber = Float.parseFloat(answer.getText());
            }

            switch (operation) {
                case 'a':
                    result = firstNumber + secondNumber;
                    break;
                case 's':
                    result = firstNumber - secondNumber;
                    break;
                case 'd':
                    result = firstNumber / secondNumber;
                    break;
                case 'm':
                    result = firstNumber * secondNumber;
                    break;
            }
            // If number is not decimal, it is represented as an int
            if (((int)result) == result) {
                answer.setText(Integer.toString((int)result));
            }
            else {
                answer.setText(Float.toString(result));
            }
            equalsPressed = true;
        }

        else if (buttonClicked == add || buttonClicked == subtract ||
                buttonClicked == divide || buttonClicked == multiply) {

            firstNumber = Float.parseFloat(answer.getText());

            switch (buttonClicked.getText()) {
                case "+":
                    operation = 'a';
                    break;
                case "-":
                    operation = 's';
                    break;
                case "/":
                    operation = 'd';
                    break;
                case "*":
                    operation = 'm';
                    break;
            }
            equalsPressed = false;
            answer.setText("0");
        }

        // Numbers
        else {
            String buttonText = (((Button)actionEvent.getSource()).getText());
            if (answer.getText().equals("0") || equalsPressed) {
                if (equalsPressed) {
                    equalsPressed = false;
                }
                answer.setText(buttonText);
            }
            else if (answer.getText().length() <= 11) {
                answer.setText(answer.getText() + buttonText);
            }
        }
    }

    @FXML
    private void keyboardListener(KeyEvent keyEvent) throws InterruptedException {

        final KeyCodeCombination shiftAdd = new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN);
        final KeyCodeCombination shiftMultiply = new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN);

        if (shiftAdd.match(keyEvent)) {
            add.fire();
        }
        else if (shiftMultiply.match(keyEvent)) {
            multiply.fire();
        } else {
            switch (keyEvent.getCode().toString()) {
                case "DIGIT0":
                    zero.fire();
                    break;
                case "DIGIT1":
                    one.fire();
                    break;
                case "DIGIT2":
                    two.fire();
                    break;
                case "DIGIT3":
                    three.fire();
                    break;
                case "DIGIT4":
                    four.fire();
                    break;
                case "DIGIT5":
                    five.fire();
                    break;
                case "DIGIT6":
                    six.fire();
                    break;
                case "DIGIT7":
                    seven.fire();
                    break;
                case "DIGIT8":
                    eight.fire();
                    break;
                case "DIGIT9":
                    nine.fire();
                    break;
                case "ENTER":
                case "EQUALS":
                    equals.fire();
                    break;
                case "MINUS":
                    subtract.fire();
                    break;
                case "SLASH":
                    divide.fire();
                    break;
                case "PERIOD":
                    comma.fire();
                    break;
                case "BACK_SPACE":
                    backspace.fire();
                    break;
            }
        }
    }
}