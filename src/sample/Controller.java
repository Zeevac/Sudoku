package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;

public class Controller {
    private Board board;
    private TextField[] tfs;
    @FXML
    GridPane gridPane;
    @FXML
    Button startButton;
    @FXML
    Button stopButton;
    @FXML
    Label timeLabel;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(0);
    private Timeline timeline;

    public void initialize() throws IOException {
        board = new Board();
        tfs = new TextField[81];
        int c = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tfs[c] = new TextField();
                tfs[c].setFont(new Font(24));
                tfs[c].setPrefWidth(50);
                tfs[c].setPrefHeight(50);
                tfs[c].setText(board.getValue(i, j) + "");
                tfs[c].setDisable(true);
                String style = "";
                if (board.getValue(i, j) != 0) {
                    style += "-fx-background-color: #ebe6e6;";
                }

                if (i == 2 || i == 5) {

                    style += "-fx-border-width: 1 1 3 1;";
                }
                if (j == 2 || j == 5) {

                    style += "-fx-border-width: 1 3 1 1;";
                }
                if ((i == 2 || i == 5) && (j == 2 || j == 5)) {
                    style += "-fx-border-width: 1 3 3 1;";
                }
                tfs[c].setStyle(style);
                gridPane.add(tfs[c], j, i);
                int finalI = i;
                int finalJ = j;
                tfs[c].textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        if (newValue.matches("[0-9]")) {
                            int a = Integer.parseInt(newValue);
                            boolean b = board.setValue(finalI, finalJ, a);
                            if (!b) {
                                showAlert("Error", "Not a valid move.", Alert.AlertType.ERROR);
                            }
                        } else {
                            showAlert("Error", "Invalid input.", Alert.AlertType.ERROR);
                        }
                        if (board.isGameFinished() && board.isBoardCorrect()) {
                            showAlert("Solved", "Congratulations. You solved the sudoku.", Alert.AlertType.INFORMATION);
                        }
                    }
                });
                c++;
            }
        }


    }

    public void onPressedStartButton() {
        stopButton.setDisable(false);
        activateTextFields();
        startButton.setDisable(true);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        if (timeSeconds.get() != 0) {
        }
        timeline.play();
    }

    private void updateTime() {
        int seconds = timeSeconds.get();
        timeSeconds.set(seconds + 1);
        timeLabel.setText("Elapsed Time:   " + timeSeconds.get() + " sn");
    }

    public void onPressedStopButton() {
        if (timeline != null) {
            timeline.stop();
        }
        passivateTextFields();
        startButton.setDisable(false);
        stopButton.setDisable(true);
    }

    public void onPressedResetButton() {
        timeSeconds.set(0);
        timeLabel.setText("Elapsed Time:    0 sn");
    }

    public void activateTextFields() {
        for (int i = 0; i < 81; i++) {
            if (tfs[i].getText().equals("0"))
                tfs[i].setDisable(false);
        }
    }

    public void passivateTextFields() {
        for (int i = 0; i < 81; i++) {
            if (tfs[i].getText().equals("0"))
                tfs[i].setDisable(true);
        }
    }

    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.show();
    }
}

