package UI;

import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecipePage {

    private TableView<RecipeData> table;
    private ObservableList<RecipeData> recipeList;

    public void start(Stage stage) {
        Label title = new Label("Recipe Management");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: seagreen;");

        TextField patientField = new TextField();
        patientField.setPromptText("Patient Name");

        TextField doctorField = new TextField();
        doctorField.setPromptText("Doctor Name");

        DatePicker issuedDatePicker = new DatePicker();
        DatePicker expirationDatePicker = new DatePicker();

        Button addButton = new Button("Add Recipe");
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        Button deleteButton = new Button("Delete Selected");
        deleteButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        // TableView setup
        table = new TableView<>();
        recipeList = FXCollections.observableArrayList();
        table.setItems(recipeList);

        TableColumn<RecipeData, String> patientCol = new TableColumn<>("Patient");
        patientCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));

        TableColumn<RecipeData, String> doctorCol = new TableColumn<>("Doctor");
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctorName"));

        TableColumn<RecipeData, LocalDate> issuedCol = new TableColumn<>("Issued Date");
        issuedCol.setCellValueFactory(new PropertyValueFactory<>("issuedDate"));
        issuedCol.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : format.format(item));
            }
        });

        TableColumn<RecipeData, LocalDate> expirationCol = new TableColumn<>("Expiration Date");
        expirationCol.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        expirationCol.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : format.format(item));
            }
        });

        table.getColumns().addAll(patientCol, doctorCol, issuedCol, expirationCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        addButton.setOnAction(e -> {
            try {
                String patient = patientField.getText().trim();
                String doctor = doctorField.getText().trim();
                LocalDate issued = issuedDatePicker.getValue();
                LocalDate expiry = expirationDatePicker.getValue();

                validateInput(patient, doctor, issued, expiry); // ممكن ترمي Exception

                recipeList.add(new RecipeData(patient, doctor, issued, expiry));

                // Reset fields
                patientField.clear();
                doctorField.clear();
                issuedDatePicker.setValue(null);
                expirationDatePicker.setValue(null);
            } catch (IllegalArgumentException ex) {
                showAlert(Alert.AlertType.WARNING, ex.getMessage());
            }
        });

        deleteButton.setOnAction(e -> {
            try {
                RecipeData selected = table.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    throw new IllegalArgumentException("Please select a recipe to delete.");
                }
                recipeList.remove(selected);
            } catch (IllegalArgumentException ex) {
                showAlert(Alert.AlertType.INFORMATION, ex.getMessage());
            }
        });

        VBox inputBox = new VBox(10,
                patientField, doctorField,
                new HBox(10, new Label("Issued:"), issuedDatePicker, new Label("Expiry:"), expirationDatePicker),
                addButton
        );
        inputBox.setAlignment(Pos.CENTER_LEFT);

        VBox layout = new VBox(20, title, inputBox, table, deleteButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout, 700, 550);
        stage.setScene(scene);
        stage.setTitle("Recipe Page");
        stage.show();
    }

    private void validateInput(String patient, String doctor, LocalDate issued, LocalDate expiry) {
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("Patient name is required.");
        }
        if (doctor.isEmpty()) {
            throw new IllegalArgumentException("Doctor name is required.");
        }
        if (issued == null) {
            throw new IllegalArgumentException("Issued date is required.");
        }
        if (expiry == null) {
            throw new IllegalArgumentException("Expiration date is required.");
        }
        if (!expiry.isAfter(issued)) {
            throw new IllegalArgumentException("Expiration must be after Issued date.");
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}