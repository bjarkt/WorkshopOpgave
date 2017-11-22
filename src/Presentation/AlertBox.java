package Presentation;


import Acq.IBuilding;
import Acq.ISensor;
import Acq.SensorType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlertBox {

    private static Stage createWindow(String header) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        return window;
    }

    public static List<String> displayBuildingInputFields(String header, String text) {
        Stage window = createWindow(header);

        Label label = new Label();
        label.setText(text);
        label.setWrapText(true);

        Button closeButton = new Button("Add");
        closeButton.setOnAction(e -> window.close());

        Label nameLabel = new Label("Enter name of building");
        TextField nameField = new TextField();

        Label addressLabel = new Label("Enter address of building");
        TextField AddressField = new TextField();

        Label cityLabel = new Label("Enter city of building");
        TextField cityField = new TextField();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, nameLabel, nameField, addressLabel, AddressField, cityLabel, cityField, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        ArrayList<String> l = new ArrayList<>();
        Collections.addAll(l, nameField.getText(), AddressField.getText(), cityField.getText());
        return l;
    }

    public static List<String> displaySensorInputFields(String header, String text, List<IBuilding> buildingList) {
        Stage window = createWindow(header);

        Label label = new Label();
        label.setText(text);
        label.setWrapText(true);

        Button closeButton = new Button("Add");
        closeButton.setOnAction(e -> window.close());


        Label buildingLabel = new Label("Choose which building to add to");
        ObservableList<IBuilding> buildingOptions = FXCollections.observableArrayList(buildingList);
        ComboBox<IBuilding> buildingComboBox = new ComboBox<>(buildingOptions);
        buildingComboBox.setValue(buildingOptions.get(0));

        Label sensorTypeLabel = new Label("Choose type of sensor");
        ObservableList<SensorType> sensorTypeOptions = FXCollections.observableArrayList(Arrays.asList(SensorType.values()));
        ComboBox<SensorType> sensorTypeComboBox = new ComboBox(sensorTypeOptions);
        sensorTypeComboBox.setValue(sensorTypeOptions.get(0));


        Label sensorAmountLabel = new Label("Choose amount of sensors of this type to add");
        ObservableList<Integer> sensorAmountOptions = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        ComboBox<Integer> sensorAmountComboBox = new ComboBox(sensorAmountOptions);
        sensorAmountComboBox.setValue(sensorAmountOptions.get(0));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, buildingLabel, buildingComboBox, sensorTypeLabel, sensorTypeComboBox, sensorAmountLabel, sensorAmountComboBox, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        ArrayList<String> l = new ArrayList<>();
        Collections.addAll(l, buildingComboBox.getValue().getName(), sensorTypeComboBox.getValue().toString(), sensorAmountComboBox.getValue().toString());
        return l;
    }

    public static <T> void displayList(String header, String text, List<T> list) {
        Stage window = createWindow(header);

        ListView<Object> lv = new ListView<>();
        lv.getItems().addAll(list);

        Label label = new Label();
        label.setText(text);
        label.setWrapText(true);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setTop(label);
        borderPane.setCenter(lv);
        borderPane.setBottom(closeButton);
        BorderPane.setAlignment(label, Pos.CENTER);
        BorderPane.setAlignment(closeButton, Pos.CENTER);
        Scene scene = new Scene(borderPane);

        //VBox layout = new VBox(10);
        //layout.getChildren().addAll(label, lv, closeButton);
        //layout.setAlignment(Pos.CENTER);

        //Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }


}
