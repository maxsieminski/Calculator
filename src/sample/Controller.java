package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;

public class Controller {
    public Label answer;
    public Button clear, negate, comma, equals;
    public Button add, subtract, divide, multiply;
    public Button zero, one, two, three, four, five, six, seven, eight, nine;

    private char operation = 'x';
    private float firstNumber;

    public void buttonListener(ActionEvent actionEvent) {
        if (actionEvent.getSource() == clear) {
            answer.setText("0");
            operation = 'x';
            firstNumber = 0;
        }

        else if (actionEvent.getSource() == negate) {
            answer.setText(Float.toString(Float.parseFloat(answer.getText()) * -1));
        }

        else if (actionEvent.getSource() == comma) {
            if (!answer.getText().contains(".")) {
                answer.setText(answer.getText() + ".");
            }
        }

        else if (actionEvent.getSource() == equals) {
            switch (operation) {
                case 'a':
                    answer.setText(Float.toString(firstNumber + Float.parseFloat(answer.getText())));
                    break;
                case 's':
                    answer.setText(Float.toString(firstNumber - Float.parseFloat(answer.getText())));
                    break;
                case 'd':
                    answer.setText(Float.toString(firstNumber / Float.parseFloat(answer.getText())));
                    break;
                case 'm':
                    answer.setText(Float.toString(firstNumber * Float.parseFloat(answer.getText())));
                    break;
            }
        }

        else if (actionEvent.getSource() == add || actionEvent.getSource() == subtract ||
                 actionEvent.getSource() == divide || actionEvent.getSource() == multiply) {

            firstNumber = Float.parseFloat(answer.getText());

            switch ((((Button)actionEvent.getSource()).getText())) {
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
            answer.setText("0");
        }

        // Numbers
        else {
            String buttonText = (((Button)actionEvent.getSource()).getText());

            if (answer.getText().equals("0")) {
                answer.setText(buttonText);
            }
            else {
                answer.setText(answer.getText() + buttonText);
            }
        }
    }

    @FXML
    private void keyboardListener(KeyEvent keyEvent) {

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
            }
        }
    }
}
