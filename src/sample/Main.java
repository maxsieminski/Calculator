package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

    Label answer;
    long number = 0;
    char operation = 'x';
    boolean isResult = false;
    Button[] numbers = new Button[10];
    Button add, subtract, divide, multiply, equals, clear, negate;

    @Override
    public void start(Stage primaryStage) {
        answer = new Label("0");
        add = new Button("+");
        subtract = new Button("-");
        divide = new Button("/");
        multiply = new Button("*");
        equals = new Button("=");
        clear = new Button("CLEAR");
        negate = new Button("N");
        numbers[0] = new Button("0");
        numbers[1] = new Button("1");
        numbers[2] = new Button("2");
        numbers[3] = new Button("3");
        numbers[4] = new Button("4");
        numbers[5] = new Button("5");
        numbers[6] = new Button("6");
        numbers[7] = new Button("7");
        numbers[8] = new Button("8");
        numbers[9] = new Button("9");

        GridPane gridPane = new GridPane() {
            @Override
            public void requestFocus() {
                // So that ENTER doesn't click on the focused button
            }
        };

        Scene scene = new Scene(gridPane, 220, 300);
        scene.getRoot().requestFocus();

        addElementsToGrid(gridPane);
        setKeyboardAction(scene);
        setButtonAction();
        resizeButtons();
        setUI();

        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setKeyboardAction(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.DIGIT0 || key.getCode() == KeyCode.NUMPAD0) {
                numbers[0].fire();
            }
            else if(key.getCode() == KeyCode.DIGIT1 || key.getCode() == KeyCode.NUMPAD1) {
                numbers[1].fire();
            }
            else if(key.getCode() == KeyCode.DIGIT2 || key.getCode() == KeyCode.NUMPAD2) {
                numbers[2].fire();
            }
            else if(key.getCode() == KeyCode.DIGIT3 || key.getCode() == KeyCode.NUMPAD3) {
                numbers[3].fire();
            }
            else if(key.getCode() == KeyCode.DIGIT4 || key.getCode() == KeyCode.NUMPAD4) {
                numbers[4].fire();
            }
            else if(key.getCode() == KeyCode.DIGIT5 || key.getCode() == KeyCode.NUMPAD5) {
                numbers[5].fire();
            }
            else if(key.getCode() == KeyCode.DIGIT6 || key.getCode() == KeyCode.NUMPAD6) {
                numbers[6].fire();
            }
            else if(key.getCode() == KeyCode.DIGIT7 || key.getCode() == KeyCode.NUMPAD7) {
                numbers[7].fire();
            }
            else if(key.getCode() == KeyCode.DIGIT8 || key.getCode() == KeyCode.NUMPAD8) {
                numbers[8].fire();
            }
            else if(key.getCode() == KeyCode.DIGIT9 || key.getCode() == KeyCode.NUMPAD9) {
                numbers[9].fire();
            }
            else if(key.getCode() == KeyCode.PLUS || key.getCode() == KeyCode.ADD) {
                add.fire();
            }
            else if(key.getCode() == KeyCode.SLASH || key.getCode() == KeyCode.DIVIDE) {
                divide.fire();
            }
            else if(key.getCode() == KeyCode.STAR || key.getCode() == KeyCode.MULTIPLY) {
                multiply.fire();
            }
            else if(key.getCode() == KeyCode.MINUS || key.getCode() == KeyCode.SUBTRACT) {
                subtract.fire();
            }
            else if(key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.EQUALS) {
                equals.fire();
            }
            else {
                System.out.println(key.getCode());
            }
        });
    }

    private void setButtonAction() {
        for (Button b : numbers) {
            b.setOnAction(this::buttonAction);
        }
        add.setOnAction(this::buttonAction);
        subtract.setOnAction(this::buttonAction);
        multiply.setOnAction(this::buttonAction);
        divide.setOnAction(this::buttonAction);
        equals.setOnAction(this::buttonAction);
        clear.setOnAction(this::buttonAction);
    }

    private void buttonAction(ActionEvent event) {
        if (event.getSource() == equals) {
            switch(operation) {
                case 'a':
                    answer.setText(Long.toString(number + Long.parseLong(answer.getText())));
                    isResult = true;
                    break;
                case 's':
                    answer.setText(Long.toString(number - Long.parseLong(answer.getText())));
                    isResult = true;
                    break;
                case 'm':
                    answer.setText(Long.toString(number * Long.parseLong(answer.getText())));
                    isResult = true;
                    break;
                case 'd':
                    answer.setText(Long.toString(number / Long.parseLong(answer.getText())));
                    isResult = true;
                    break;
            }
        }
        else if(event.getSource() == add) {
            number = Integer.parseInt(answer.getText());
            answer.setText("0");
            operation = 'a';
        }
        else if(event.getSource() == subtract) {
            number = Integer.parseInt(answer.getText());
            answer.setText("0");
            operation = 's';
        }
        else if(event.getSource() == multiply) {
            number = Integer.parseInt(answer.getText());
            answer.setText("0");
            operation = 'm';
        }
        else if(event.getSource() == divide) {
            number = Integer.parseInt(answer.getText());
            answer.setText("0");
            operation = 'd';
        }
        else if(event.getSource() == clear) {
            number = 0;
            operation = 'x';
            isResult = false;
            answer.setText("0");
        }
        else {
            for (Button b : numbers) {
                if (event.getSource() == b && answer.getText().length() <= 12) {
                    if (answer.getText().equals("0") || isResult) {
                        answer.setText(b.getText());
                    } else {

                        answer.setText(answer.getText() + b.getText());
                    }
                    return;
                }
            }
        }
    }

    private void addElementsToGrid(GridPane gridPane) {
        gridPane.add(answer, 0, 0, 4, 1);
        gridPane.add(clear, 0, 1, 4, 1);
        gridPane.add(divide, 3, 1, 1, 1);

        gridPane.add(numbers[7], 0, 3, 1, 1);
        gridPane.add(numbers[8], 1, 3, 1, 1);
        gridPane.add(numbers[9], 2, 3, 1, 1);
        gridPane.add(multiply, 3, 3,1,1);

        gridPane.add(numbers[4], 0, 4, 1, 1);
        gridPane.add(numbers[5], 1, 4, 1, 1);
        gridPane.add(numbers[6], 2, 4, 1,1);
        gridPane.add(subtract, 3, 4, 1, 1);

        gridPane.add(numbers[1], 0, 5, 1, 1);
        gridPane.add(numbers[2], 1, 5, 1 ,1);
        gridPane.add(numbers[3], 2, 5, 1, 1);
        gridPane.add(add, 3, 5,1, 1);

        gridPane.add(negate, 0, 6, 1, 1);
        gridPane.add(numbers[0], 1, 6, 1, 1);
        gridPane.add(equals, 2, 6, 2, 1);
    }

    private void resizeButtons() {
        answer.setPrefWidth(300);
        answer.setPrefHeight(100);

        clear.setPrefWidth(165);
        clear.setPrefHeight(40);

        equals.setPrefWidth(110);
        equals.setPrefHeight(40);

        setButtonSize(numbers[0]);
        setButtonSize(numbers[1]);
        setButtonSize(numbers[2]);
        setButtonSize(numbers[3]);
        setButtonSize(numbers[4]);
        setButtonSize(numbers[5]);
        setButtonSize(numbers[6]);
        setButtonSize(numbers[7]);
        setButtonSize(numbers[8]);
        setButtonSize(numbers[9]);

        setButtonSize(add);
        setButtonSize(subtract);
        setButtonSize(multiply);
        setButtonSize(divide);
        setButtonSize(negate);
    }

    private void setUI() {
        answer.setFont(new Font("Segoe", 32));
    }

    private void setButtonSize(Button b) {
        b.setPrefWidth(55);
        b.setPrefHeight(40);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
