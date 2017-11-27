package Presentation;


import Acq.IBuilding;
import Acq.ISensor;
import Acq.SensorType;
import Acq.IBusiness;
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
import java.util.*;

public class AlertBox {

    private static Stage createWindow(String header) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        return window;
    }

    public static HashMap<String, String> displayBuildingInputFields(String header, String text) {
        Stage window = createWindow(header);

        Label label = new Label();
        label.setText(text);
        label.setWrapText(true);

        Button closeButton = new Button("Add");
        closeButton.setOnAction(e -> window.close());

        Label nameLabel = new Label("Enter name of building");
        TextField nameField = new TextField();

        Label addressLabel = new Label("Enter address of building");
        TextField addressField = new TextField();

        Label cityLabel = new Label("Enter city of building");
        TextField cityField = new TextField();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, nameLabel, nameField, addressLabel, addressField, cityLabel, cityField, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        HashMap<String, String> settings = new HashMap<>();
        settings.put("name", nameField.getText());
        settings.put("address", addressField.getText());
        settings.put("city", cityField.getText());
        return settings;
    }

    public static HashMap<String, String> displaySensorInputFields(String header, String text, List<IBuilding> buildingList) {
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

        Label sensorNameLabel = new Label("Enter name of sensor");
        TextField sensorNameField = new TextField();

        Label sensorAmountLabel = new Label("Choose amount of sensors of this type to add");
        ObservableList<Integer> sensorAmountOptions = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        ComboBox<Integer> sensorAmountComboBox = new ComboBox(sensorAmountOptions);
        sensorAmountComboBox.setValue(sensorAmountOptions.get(0));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, buildingLabel, buildingComboBox, sensorTypeLabel, sensorTypeComboBox, sensorNameLabel, sensorNameField, sensorAmountLabel, sensorAmountComboBox, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        HashMap<String, String> settings = new HashMap<>();
        settings.put("BuildingName", buildingComboBox.getValue().getName());
        settings.put("type", sensorTypeComboBox.getValue().toString());
        settings.put("amount", sensorAmountComboBox.getValue().toString());
        settings.put("sensorName", sensorNameField.getText());
        return settings;
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

    public static HashMap<String, String> displaySensorInputFieldsRemove(String header, String text, List<IBuilding> buildingList, IBusiness ibusiness) {
        Stage window = createWindow(header);

        Label label = new Label();
        label.setText(text);
        label.setWrapText(true);

        Button closeButton = new Button("Remove");
        closeButton.setOnAction(e -> window.close());


        Label buildingLabel = new Label("Choose which building to remove sensor from");
        ObservableList<IBuilding> buildingOptions = FXCollections.observableArrayList();
        for (IBuilding building : buildingList) {
            if (ibusiness.getSensorsForBuilding(building.getName()).size() != 0) {
                buildingOptions.add(building);
            }
        }
        if (buildingOptions.size() == 0) {
            return null;
        }
        ComboBox<IBuilding> buildingComboBox = new ComboBox<>(buildingOptions);
        buildingComboBox.setValue(buildingOptions.get(0));


        Set<SensorType> possibleSensorTypes = new HashSet<>();
        setPossibleSensorTypes(ibusiness, possibleSensorTypes, buildingComboBox.getValue());
        ObservableList<SensorType> sensorTypeOptions = FXCollections.observableArrayList(possibleSensorTypes);

        Label sensorTypeLabel = new Label("Choose type of sensor");
        ComboBox<SensorType> sensorTypeComboBox = new ComboBox(sensorTypeOptions);
        sensorTypeComboBox.setValue(sensorTypeOptions.get(0));

        buildingComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            setPossibleSensorTypes(ibusiness, possibleSensorTypes, newValue);
            sensorTypeOptions.clear();
            sensorTypeOptions.addAll(possibleSensorTypes);
            sensorTypeComboBox.setValue(sensorTypeOptions.get(0));
        });


        Label sensorID = new Label("Choose which sensor to remove");
        ObservableList<ISensor> sensorIDOptions = FXCollections.observableArrayList(ibusiness.getSensorsForBuilding(buildingComboBox.getValue().getName()));
        ComboBox<ISensor> sensorIDComboBox = new ComboBox(sensorIDOptions);
        sensorIDComboBox.setValue(sensorIDOptions.get(0));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, buildingLabel, buildingComboBox, sensorTypeLabel, sensorTypeComboBox, sensorID, sensorIDComboBox, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        HashMap<String, String> settings = new HashMap<>();
        settings.put("BuildingName", buildingComboBox.getValue().getName());
        settings.put("type", sensorTypeComboBox.getValue().toString());
        settings.put("id", String.valueOf(sensorIDOptions.indexOf(sensorIDComboBox.getValue())));
        return settings;
    }

    private static void setPossibleSensorTypes(IBusiness ibusiness, Set<SensorType> possibleSensorTypes, IBuilding newValue) {
        List<ISensor> possibleSensors = new ArrayList<>();
        possibleSensorTypes.clear();
        possibleSensors.addAll(ibusiness.getSensorsForBuilding(newValue.getName()));
        for (ISensor possibleSensor : possibleSensors) {
            possibleSensorTypes.add(possibleSensor.getSensorType());
        }
    }


}
