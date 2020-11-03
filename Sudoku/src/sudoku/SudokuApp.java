package sudoku;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SudokuApp extends Application {

    private OneNumberTextField[][] temp_field = new OneNumberTextField[9][9];
    private SudokuModel sudokuModel;

    private static class AlertWindow{

        private static void display(){
            Stage alert = new Stage();
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("ERROR!");
            alert.setMinWidth(200);
            alert.setMinHeight(100);

            Label label1 = new Label();
            Label label2 = new Label();
            label1.setText("No solution!");
            label2.setText("Try again with new values");
            Button b3 = new Button("OK");
            b3.setOnAction(event -> alert.close());

            VBox vBox = new VBox();
            vBox.setPadding(new Insets(5,5,5,5));
            vBox.setSpacing(5);
            vBox.getChildren().addAll(label1,label2,b3);
            vBox.setAlignment(Pos.CENTER);
            Scene scene = new Scene(vBox);
            alert.setScene(scene);
            alert.showAndWait();



        }
    }

    private class ButtonHandler1 implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            for(int i = 0; i < 9; i++){
                for (int j = 0; j < 9; j++){
                    OneNumberTextField ontf = temp_field[i][j];
                    if(ontf.getText().isEmpty()){
                        sudokuModel.set(i,j,0);
                    }
                    else {
                        sudokuModel.set(i,j,Integer.parseInt(ontf.getText()));
                    }
                }
            }
            if(sudokuModel.solve()){
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        OneNumberTextField tf = temp_field[i][j];
                        tf.replaceText(0, 0, "" + sudokuModel.get(i, j));
                    }
                }
            }
            else{
                AlertWindow.display();
            }

        }
    }

    private class ButtonHandler2 implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            for(int i = 0; i < 9; i++){
                for (int j = 0; j < 9; j++){
                    OneNumberTextField ontf = temp_field[i][j];
                    if(!ontf.getText().isEmpty()){
                        ontf.replaceText(0,1, "");
                        sudokuModel.set(i,j, 0);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        sudokuModel = new SudokuModel();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 430);
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();

        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(5,0,0,0));
        tilePane.setPrefColumns(9);
        tilePane.setPrefRows(9);
        tilePane.setVgap(3);
        tilePane.setHgap(3);
        tilePane.setAlignment(Pos.TOP_CENTER);
        final int SIZE = 40;
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                OneNumberTextField ontf = new OneNumberTextField();
                ontf.setFont(new Font(20));
                ontf.setPrefSize(SIZE, SIZE);
                if ((i/3 + k/3) % 2 == 0) {
                    ontf.setStyle("-fx-background-color: grey;");
                }
                temp_field[i][k] = ontf;
                tilePane.getChildren().add(ontf);
            }
        }


        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(5,5,5,5));
        hBox.setSpacing(5);
        Button b1 = new Button("Solve");
        b1.setOnAction(new ButtonHandler1());
        b1.setMinWidth(190);
        Button b2 = new Button("Clear");
        b2.setOnAction(new ButtonHandler2());
        b2.setMinWidth(190);
        hBox.getChildren().addAll(b1, b2);
        root.setBottom(hBox);
        root.setCenter(tilePane);
        primaryStage.setResizable(false);

    }

}
