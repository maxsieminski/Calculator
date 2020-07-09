package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// TODO: convert if else to switch
// TODO: fix decimal division
// TODO: UI

public class Main extends Application {

    Label answer;
    float tempNumber, number = 0;
    char operation = 'x';
    boolean endProgram = false, equalsPressed = false, uiInitialized = false, resizedFlag = false;
    Button[] numbers = new Button[10];
    Button add, subtract, divide, multiply, equals, clear, negate, comma;

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
        comma = new Button(",");
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

        GridPane gridPane = new GridPane();

        gridPane.setHgap(0.5);
        gridPane.setVgap(0.5);

        Scene scene = new Scene(gridPane, 220, 300);

        addElementsToGrid(gridPane);
        setKeyboardAction(scene);
        setButtonAction();

        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        resizeButtons(scene);
                        setUI(scene);
                    });
                    Thread.sleep(1);
                }
            }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(162);
        primaryStage.setMinHeight(300);
        primaryStage.setScene(scene);
        primaryStage.setOpacity(0.985);
        primaryStage.show();

        primaryStage.setOnCloseRequest(windowEvent -> {
            endProgram = true;
        });

        gridPane.requestFocus();
    }

    private void setKeyboardAction(Scene scene) {

        final KeyCodeCombination shiftAdd = new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN);
        final KeyCodeCombination shiftMultiply = new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            // TODO: convert to switch
            if(key.getCode() == KeyCode.PLUS || key.getCode() == KeyCode.ADD || shiftAdd.match(key)) {
                add.fire();
            }
            else if(key.getCode() == KeyCode.SLASH || key.getCode() == KeyCode.DIVIDE) {
                divide.fire();
            }
            else if(key.getCode() == KeyCode.STAR || key.getCode() == KeyCode.MULTIPLY || shiftMultiply.match(key)) {
                multiply.fire();
            }
            else if(key.getCode() == KeyCode.MINUS || key.getCode() == KeyCode.SUBTRACT) {
                subtract.fire();
            }
            else if(key.getCode() == KeyCode.ENTER || key.getCode() == KeyCode.EQUALS) {
                equals.fire();
            }
            else if(key.getCode() == KeyCode.PERIOD || key.getCode() == KeyCode.DECIMAL) {
                comma.fire();
            }
            else if(key.getCode() == KeyCode.DIGIT0 || key.getCode() == KeyCode.NUMPAD0) {
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
            else if(key.getCode() == KeyCode.BACK_SPACE) {
                int textLength = answer.getText().length();
                if (textLength > 1) {
                    answer.setText(answer.getText().substring(0, answer.getText().length() - 1));
                }
                else if (textLength == 1) {
                    answer.setText("0");
                }
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
        negate.setOnAction(this::buttonAction);
        comma.setOnAction(this::buttonAction);
    }

    private void buttonAction(ActionEvent event) {
        if (event.getSource() == equals) {
            if (!equalsPressed) {
                tempNumber = Float.parseFloat(answer.getText());
            }
            switch(operation) {
                case 'a':
                    if (equalsPressed) {
                        answer.setText(Float.toString(Float.parseFloat(answer.getText()) + tempNumber));
                    }
                    else {
                        answer.setText(Float.toString(number + Float.parseFloat(answer.getText())));
                    }
                    break;
                case 's':
                    if (equalsPressed) {
                        answer.setText(Float.toString(Float.parseFloat(answer.getText()) - tempNumber));
                    }
                    else {
                        answer.setText(Float.toString(number - Float.parseFloat(answer.getText())));
                    }
                    break;
                case 'm':
                    if (equalsPressed) {
                        answer.setText(Float.toString(Float.parseFloat(answer.getText()) * tempNumber));
                    }
                    else {
                        answer.setText(Float.toString(number * Float.parseFloat(answer.getText())));
                    }
                    break;
                case 'd':
                    if (equalsPressed) {
                        answer.setText(Float.toString(Float.parseFloat(answer.getText()) / tempNumber));
                    }
                    else {
                        answer.setText(Float.toString(number / Float.parseFloat(answer.getText())));
                    }
                    break;
            }
            equalsPressed = true;
        }
        else if(event.getSource() == add || event.getSource() == subtract || event.getSource() == multiply || event.getSource() == divide) {
            number = Float.parseFloat(answer.getText());
            switch (((Button)event.getSource()).getText()) {
                case "+":
                    operation = 'a';
                    break;
                case "-":
                    operation = 's';
                    break;
                case "*":
                    operation = 'm';
                    break;
                case "/":
                    operation = 'd';
                    break;
            }
            equalsPressed = false;
            answer.setText("0");
        }
        else if(event.getSource() == clear) {
            number = 0;
            operation = 'x';
            answer.setText("0");
            equalsPressed = false;
        }
        else if(event.getSource() == negate) {
            answer.setText(Float.toString(Float.parseFloat(answer.getText()) * -1));
        }
        else if(event.getSource() == comma) {
            if (!answer.getText().contains(".")) {
                answer.setText(Float.toString(Float.parseFloat(answer.getText() + ".")));
                answer.setText(answer.getText().substring(0, answer.getText().length() - 1));
            }
        }
        else {
            for (Button b : numbers) {
                if (event.getSource() == b && answer.getText().length() <= 12) {
                    if (equalsPressed) {
                        answer.setText("0");
                        equalsPressed = false;
                    }
                    if (answer.getText().equals("0")) {
                        answer.setText(b.getText());
                        break;
                    }
                    else {
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
        gridPane.add(comma, 2, 6, 1, 1);
        gridPane.add(equals, 3, 6, 1, 1);

        answer.setFocusTraversable(false);
        clear.setFocusTraversable(false);
        divide.setFocusTraversable(false);
        multiply.setFocusTraversable(false);
        subtract.setFocusTraversable(false);
        add.setFocusTraversable(false);
        negate.setFocusTraversable(false);
        equals.setFocusTraversable(false);

        for(Button b : numbers) {
            b.setFocusTraversable(false);
        }
    }

    private void resizeButtons(Scene scene) {
        answer.setPrefWidth(scene.getWidth());
        answer.setPrefHeight(scene.getHeight() / 3);

        clear.setPrefWidth(scene.getWidth() * 0.78);
        clear.setPrefHeight(scene.getHeight() * 0.1333333);

        for (int i = 0; i < 10; i++) {
            setButtonSize(scene, numbers[i]);
        }

        setButtonSize(scene, add);
        setButtonSize(scene, subtract);
        setButtonSize(scene, multiply);
        setButtonSize(scene, divide);
        setButtonSize(scene, negate);
        setButtonSize(scene, comma);
        setButtonSize(scene, equals);
    }

    private void setUI(Scene scene) { //TODO
        scene.getRoot().setStyle("-fx-background-color: #232323");

        answer.setStyle("-fx-background-color: #242424; -fx-font-family: Segoe; -fx-font-size: 48; -fx-text-fill: white;");
        answer.setAlignment(Pos.CENTER_RIGHT);

        for (int i = 0; i < 10; i++) {
            if (!uiInitialized) {
                int tempI = i;
                numbers[i].setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16");
                numbers[i].setOnMouseEntered(e->numbers[tempI].setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
                numbers[i].setOnMouseExited(e->numbers[tempI].setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            }
            if (scene.getHeight() > 500 && scene.getWidth() > 400 && !resizedFlag) {
                for (int j = 0; j < 10; j++) {
                    int tempJ = j;
                    numbers[j].setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;");
                    numbers[j].setOnMouseEntered(e->numbers[tempJ].setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
                    numbers[j].setOnMouseExited(e->numbers[tempJ].setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
                }
            }
            else if (scene.getWidth() < 400 && scene.getHeight() < 500 && resizedFlag) {
                for (int j = 0; j < 10; j++) {
                    int tempJ = j;
                    numbers[j].setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;");
                    numbers[j].setOnMouseEntered(e->numbers[tempJ].setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
                    numbers[j].setOnMouseExited(e->numbers[tempJ].setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
                }
            }
        }

        if(!uiInitialized) {
            negate.setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0;");
            comma.setStyle(negate.getStyle());

            clear.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0;");
            divide.setStyle(clear.getStyle());
            multiply.setStyle(clear.getStyle());
            add.setStyle(clear.getStyle());
            subtract.setStyle(clear.getStyle());

            equals.setStyle("-fx-background-color: #16466d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0;");

            negate.setOnMouseEntered(e->negate.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            negate.setOnMouseExited(e->negate.setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            comma.setOnMouseEntered(e->comma.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            comma.setOnMouseExited(e->comma.setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            clear.setOnMouseEntered(e->clear.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            clear.setOnMouseExited(e->clear.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            divide.setOnMouseEntered(e->divide.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            divide.setOnMouseExited(e->divide.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            multiply.setOnMouseEntered(e->multiply.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            multiply.setOnMouseExited(e->multiply.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            add.setOnMouseEntered(e->add.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            add.setOnMouseExited(e->add.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            subtract.setOnMouseEntered(e->subtract.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            subtract.setOnMouseExited(e->subtract.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            equals.setOnMouseEntered(e->equals.setStyle("-fx-background-color: #0470c5; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            equals.setOnMouseExited(e->equals.setStyle("-fx-background-color: #16466d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            uiInitialized = true;
        }

        if (scene.getHeight() > 500 && scene.getWidth() > 400 && !resizedFlag) {
            negate.setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;");
            comma.setStyle(negate.getStyle());

            clear.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;");
            divide.setStyle(clear.getStyle());
            multiply.setStyle(clear.getStyle());
            add.setStyle(clear.getStyle());
            subtract.setStyle(clear.getStyle());

            equals.setStyle("-fx-background-color: #16466d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;");

            negate.setOnMouseEntered(e->negate.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
            negate.setOnMouseExited(e->negate.setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));

            comma.setOnMouseEntered(e->comma.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
            comma.setOnMouseExited(e->comma.setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));

            clear.setOnMouseEntered(e->clear.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
            clear.setOnMouseExited(e->clear.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));

            divide.setOnMouseEntered(e->divide.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
            divide.setOnMouseExited(e->divide.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));

            multiply.setOnMouseEntered(e->multiply.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
            multiply.setOnMouseExited(e->multiply.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));

            add.setOnMouseEntered(e->add.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
            add.setOnMouseExited(e->add.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));

            subtract.setOnMouseEntered(e->subtract.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
            subtract.setOnMouseExited(e->subtract.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));

            equals.setOnMouseEntered(e->equals.setStyle("-fx-background-color: #0470c5; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));
            equals.setOnMouseExited(e->equals.setStyle("-fx-background-color: #16466d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 32;"));

            resizedFlag = true;
        }
        else if (scene.getWidth() < 400 && scene.getHeight() < 500 && resizedFlag) {
            negate.setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;");
            comma.setStyle(negate.getStyle());

            clear.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;");
            divide.setStyle(clear.getStyle());
            multiply.setStyle(clear.getStyle());
            add.setStyle(clear.getStyle());
            subtract.setStyle(clear.getStyle());

            equals.setStyle("-fx-background-color: #16466d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;");

            negate.setOnMouseEntered(e->negate.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            negate.setOnMouseExited(e->negate.setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            comma.setOnMouseEntered(e->comma.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            comma.setOnMouseExited(e->comma.setStyle("-fx-background-color: #070707; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            clear.setOnMouseEntered(e->clear.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            clear.setOnMouseExited(e->clear.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            divide.setOnMouseEntered(e->divide.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            divide.setOnMouseExited(e->divide.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            multiply.setOnMouseEntered(e->multiply.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16"));
            multiply.setOnMouseExited(e->multiply.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            add.setOnMouseEntered(e->add.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            add.setOnMouseExited(e->add.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            subtract.setOnMouseEntered(e->subtract.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            subtract.setOnMouseExited(e->subtract.setStyle("-fx-background-color: #151515; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            equals.setOnMouseEntered(e->equals.setStyle("-fx-background-color: #0470c5; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));
            equals.setOnMouseExited(e->equals.setStyle("-fx-background-color: #16466d; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 0; -fx-font-size: 16;"));

            resizedFlag = false;
        }
        if (answer.getText().endsWith(".0")) {
            answer.setText(answer.getText().substring(0, answer.getText().indexOf(".")));
        }
        if(answer.getText().contains("E")) {
            answer.setText(answer.getText().replace('E', 'e'));
        }
    }

    private void setButtonSize(Scene scene, Button b) {
        b.setPrefWidth(scene.getWidth() * 0.25454545);
        b.setPrefHeight(scene.getHeight() * 0.1333333);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
