package javaFX;

import Data.Baby;
import datastructure.HashTable;
import datastructure.Helpers;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class javaFx extends Application {
    Helpers helpers = new Helpers();
    TextField filesDirectory = new TextField("");
    HashTable<Baby> hashTable = new HashTable<>(10);

    Button read = new Button("Read ");
    Button addNewRecord = new Button("Add new name/gender record");
    Button delete = new Button("Delete a name record.");
    Button searchForName = new Button("Search for Name");
    Button exit = new Button("Exit");
    Button addToHeap = new Button(" Add a new name’s year/freq record.");
    Button update = new Button("Update a name’s year/freq record");
    Button maxFrequency = new Button("Name with max frequency");

    Pane pane = new Pane();

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(pane, 600, 650);
        filesDirectory.setPromptText("No File Uploaded");
        Text text = new Text("Popularity of a Name");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        text.setX(1);
        text.setY(50);
        text.setFill(Color.rgb(8, 1, 82));

        pane.setStyle("-fx-background-color: #c1bcf5");

        VBox vBox = finalLook();
        exit.setLayoutX(230);
        exit.setLayoutY(580);
        exit.setMinWidth(100);
        exit.setFont(new Font(15));
        pane.getChildren().addAll(text, vBox, exit);
//        read.setOnAction(e -> {
//            int year = Integer.parseInt(filesDirectory.getText());
//      //      hashTable = helpers.read(year);
//        });

        addNewRecord.setOnAction(e -> {
            Dialog<String> dialog = new Dialog<>();

            dialog.setTitle("Add new Record ");
            dialog.setHeaderText("Enter the Name And gender");
            dialog.getDialogPane().setPrefSize(400, 290);
            dialog.setX(460);
            dialog.setY(270);
            VBox vBox1 = new VBox();
            TextField name = new TextField();
            name.setPromptText("Enter the Name :");
            name.setMinWidth(50);
            ComboBox<String> gender = new ComboBox<>();
            gender.setMinWidth(150);
            gender.getItems().addAll("F", "M");
            gender.setPromptText("select gender");
            ButtonType done = new ButtonType("Exit", ButtonBar.ButtonData.FINISH);
            Button add = new Button("Add");
            TextArea textArea = new TextArea();
            textArea.setMinWidth(100);
            textArea.setMinHeight(100);

            add.setOnAction(m -> {
                String name1 = name.getText();
                String gender1 = gender.getValue();
                helpers.insert(name1, gender1);
                textArea.setText("Done");

            });

            vBox1.setSpacing(20);
            vBox1.getChildren().addAll(name, gender, textArea, add);
            dialog.getDialogPane().getButtonTypes().addAll(done);
            dialog.getDialogPane().setContent(vBox1);
            dialog.showAndWait();
        });

        update.setOnAction(e -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Update Frequency");
            dialog.setHeaderText("Enter the Name that you want to update and specific year");
            GridPane gridPane = new GridPane();
            TextField name = new TextField();
            name.setPromptText("Enter the Name ");
            TextField year1 = new TextField();
            year1.setPromptText("Enter the year");
            TextField frequency1 = new TextField();
            frequency1.setPromptText("Enter New Frequency");
            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.getItems().addAll("F", "M");
            comboBox.setPromptText("select gender");
            comboBox.setMinWidth(150);
            TextArea textArea = new TextArea();
            textArea.setMinWidth(300);
            textArea.setDisable(true);
            ButtonType exit = new ButtonType("Exit", ButtonBar.ButtonData.OK_DONE);
            Button calculate = new Button("Done");
            calculate.setOnAction(m -> {
                String nameValue = name.getText();
                String gender = comboBox.getValue();
                int year = Integer.parseInt(year1.getText());
                int frequency = Integer.parseInt(frequency1.getText());
                String result = helpers.update(nameValue, gender, year, frequency);
                textArea.setText(result);
            });
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.add(name, 0, 0);
            gridPane.add(comboBox, 1, 0);
            gridPane.add(year1, 0, 2);
            gridPane.add(frequency1, 1, 2);
            gridPane.add(textArea, 0, 3);
            gridPane.add(calculate, 0, 4);
            dialog.getDialogPane().setPrefSize(400, 290);
            dialog.setX(460);
            dialog.setY(270);
            dialog.getDialogPane().getButtonTypes().addAll(exit);
            dialog.getDialogPane().setContent(gridPane);
            dialog.show();


        });

        delete.setOnAction(e -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Delete Record");
            dialog.setHeaderText("delete name and gender");
            GridPane gridPane = new GridPane();
            TextField name = new TextField();
            name.setPromptText("Enter Name");
            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.getItems().addAll("F", "M");
            comboBox.setPromptText("select gender");
            comboBox.setMinWidth(150);
            TextField output = new TextField();
            output.setPromptText("Output here");
            output.setMinWidth(200);
            output.setDisable(true);

            ButtonType exit = new ButtonType("Exit", ButtonBar.ButtonData.OK_DONE);
            Button calculate = new Button("Done");
            calculate.setOnAction(m -> {
                String nameValue = name.getText();
                String gender = comboBox.getValue();
               String re =helpers.delete(nameValue, gender);
                output.setText(re);
            });
            gridPane.setHgap(10);
            gridPane.setVgap(50);
            gridPane.add(name, 0, 0);
            gridPane.add(comboBox, 1, 0);
            gridPane.add(output, 0, 1);
            gridPane.add(calculate, 0, 3);
            dialog.getDialogPane().setPrefSize(400, 290);
            dialog.setX(460);
            dialog.setY(270);
            dialog.getDialogPane().getButtonTypes().addAll(exit);
            dialog.getDialogPane().setContent(gridPane);
            dialog.show();

        });
        addToHeap.setOnAction(e -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Add to Heap");
            dialog.setHeaderText("Enter name and gender to add for them a new record");
            GridPane gridPane = new GridPane();
            TextField name = new TextField();
            name.setPromptText("Enter the name ");
            TextField year1 = new TextField();
            year1.setPromptText("Enter year");
            TextField frequency1 = new TextField();
            frequency1.setPromptText("Enter the frequency");
            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.getItems().addAll("F", "M");
            comboBox.setPromptText("select gender");
            comboBox.setMinWidth(150);
            TextField textArea = new TextField();
            textArea.setMinWidth(300);
            textArea.setMinHeight(100);
            textArea.setDisable(true);
            ButtonType exit = new ButtonType("Exit", ButtonBar.ButtonData.OK_DONE);
            Button calculate = new Button("Done");
            calculate.setOnAction(m -> {
                String nameValue = name.getText();
                String gender = comboBox.getValue();
                int year = Integer.parseInt(year1.getText());
                int frequency = Integer.parseInt(frequency1.getText());
                String result = helpers.addToHeap(nameValue, gender, year, frequency);
                textArea.setText(result);
            });
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.add(name, 0, 0);
            gridPane.add(comboBox, 1, 0);
            gridPane.add(year1, 0, 1);
            gridPane.add(frequency1, 1, 1);
            gridPane.add(textArea, 0, 2);
            gridPane.add(calculate, 0, 3);
            dialog.getDialogPane().setPrefSize(400, 290);
            dialog.setX(460);
            dialog.setY(270);
            dialog.getDialogPane().getButtonTypes().addAll(exit);
            dialog.getDialogPane().setContent(gridPane);
            dialog.show();

        });

        searchForName.setOnAction(e -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Search for Record");
            dialog.setHeaderText("Enter name and gender");
            GridPane gridPane = new GridPane();
            TextField name = new TextField();
            name.setPromptText("Enter the name");
            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.getItems().addAll("F", "M");
            comboBox.setPromptText("select gender");
            comboBox.setMinWidth(150);
            TextArea textArea = new TextArea();
            textArea.setMinWidth(100);
            textArea.setMinHeight(100);
            ButtonType exit = new ButtonType("Exit", ButtonBar.ButtonData.OK_DONE);
            Button calculate = new Button("Done");
            calculate.setOnAction(m -> {
                String nameValue = name.getText();
                String gender = comboBox.getValue();
                String result = helpers.search(nameValue, gender);
                textArea.setText(result);
            });
            gridPane.setHgap(10);
            gridPane.setVgap(50);
            gridPane.add(name, 0, 0);
            gridPane.add(comboBox, 1, 0);
            gridPane.add(textArea, 0, 1);
            gridPane.add(calculate, 0, 3);
            dialog.getDialogPane().setPrefSize(400, 290);
            dialog.setX(460);
            dialog.setY(270);
            dialog.getDialogPane().getButtonTypes().addAll(exit);
            dialog.getDialogPane().setContent(gridPane);
            dialog.show();

        });

        maxFrequency.setOnAction(e->{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Max frequency for Record");
            GridPane gridPane = new GridPane();
            TextField name = new TextField();
            name.setPromptText("Output");
            name.setMinWidth(500);
            ButtonType exit = new ButtonType("Exit", ButtonBar.ButtonData.OK_DONE);
            Button calculate = new Button("Done");
            calculate.setOnAction(m -> {
                Baby result = helpers.maxFrequency();
                name.setText(result.toString());
            });
            gridPane.setHgap(10);
            gridPane.setVgap(50);
            gridPane.add(name, 0, 0);
            gridPane.add(calculate, 0, 1);
            dialog.getDialogPane().setPrefSize(400, 290);
            dialog.setX(460);
            dialog.setY(270);
            dialog.getDialogPane().getButtonTypes().addAll(exit);
            dialog.getDialogPane().setContent(gridPane);
            dialog.show();

        });

        exit.setOnAction(e -> {
            hashTable.printHashTable();
            System.exit(0);
        });

        stage.setScene(scene);
        stage.show();

    }

    public StackPane addStackPane() {
        StackPane pane = new StackPane();
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        filesDirectory.setMinHeight(40);
        filesDirectory.setMinWidth(400);
        read.setStyle(" -fx-background-radius: 15, 25, 29, 28;" + "-fx-padding: 10px 10px 10px 10px;" + "-fx-background-color: beige" + " linear-gradient(rosybrown,grey );");
        Label title = new Label("Directory Name :");
        title.setStyle("-fx-translate-y: -7;" + "-fx-translate-x:15;" + "-fx-font-size: 15;");
        pane.setAlignment(title, Pos.TOP_LEFT);
        hBox.setStyle("-fx-padding: 10px,10px,10px,100px;" + "-fx-border-insets: 20 15 15 15;" + "-fx-background-color: #c1bcf5;" + "-fx-border-color: antiquewhite");
        hBox.getChildren().addAll(filesDirectory, read);
        pane.getChildren().addAll(hBox, title);
        return pane;
    }

    public StackPane addButtons() {
        StackPane pane = new StackPane();
        VBox vBox = new VBox();
        Label title = new Label("Select Method");
        title.setStyle("-fx-translate-y: -7;" + "-fx-translate-x:15;" + "-fx-font-size: 15;");
        vBox.setStyle("-fx-padding: 50px,20px,20px,20px;" + "-fx-border-insets: 20 15 15 15;" + "-fx-background-color: #c1bcf5;" + "-fx-border-color: antiquewhite");
        addNewRecord.setMinWidth(400);
        addNewRecord.setMinHeight(40);
        addNewRecord.setFont(new Font(15));

        update.setMinWidth(400);
        update.setMinHeight(40);
        update.setFont(new Font(15));

        delete.setMinWidth(400);
        delete.setMinHeight(40);
        delete.setFont(new Font(15));

        addToHeap.setMinWidth(400);
        addToHeap.setMinHeight(40);
        addToHeap.setFont(new Font(15));

        maxFrequency.setMinWidth(400);
        maxFrequency.setMinHeight(40);
        maxFrequency.setFont(new Font(15));

        searchForName.setMinWidth(400);
        searchForName.setMinHeight(40);
        searchForName.setFont(new Font(15));
        vBox.setSpacing(10);

        vBox.getChildren().addAll(addNewRecord, update, delete, addToHeap, searchForName, maxFrequency);
        pane.getChildren().addAll(vBox, title);
        pane.setAlignment(title, Pos.TOP_LEFT);
        return pane;

    }

    public VBox finalLook() {
        VBox vBox = new VBox();
        StackPane grid = addStackPane();
        StackPane grid2 = addButtons();
        vBox.setLayoutX(20);
        vBox.setLayoutY(100);
        vBox.getChildren().addAll(grid, grid2);
        return vBox;
    }
}

