package Presentation;


import Acq.IBuilding;
import Acq.ISensor;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlertBox {

    public static void display(String header, List<IBuilding> list) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);

        ListView<Object> lv = new ListView<>();
        lv.getItems().addAll(list);



        Label label = new Label();
        label.setText("test");
        label.setWrapText(true);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, lv, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }


}
