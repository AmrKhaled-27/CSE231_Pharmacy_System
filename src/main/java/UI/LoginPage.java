package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginPage {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy System - Login");

        String imageUrl = "https://i.pinimg.com/736x/5c/6a/e5/5c6ae5cfdeda6f2c6dd208348c570390.jpg";
        Image image = new Image(imageUrl, true);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);


        // Main Title centered on top
        Label mainTitle = new Label("Pharmacy System");
        mainTitle.setFont(new Font("Arial", 28));
        mainTitle.setTextFill(Color.DARKGREEN);
        mainTitle.setAlignment(Pos.CENTER);

        // Top Layout with BorderPane: image on left, title in center
        BorderPane topPane = new BorderPane();
        topPane.setLeft(imageView);
        BorderPane.setAlignment(imageView, Pos.CENTER_LEFT);
        topPane.setPadding(new Insets(10));

        // Login Title
        Label titleLabel = new Label("Login");
        titleLabel.setFont(new Font("Arial", 30));
        titleLabel.setTextFill(Color.DARKBLUE);

        // Username field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Email");
        usernameField.setMaxWidth(250);

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(250);

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px;");

        // Button Action
        loginButton.setOnAction(e -> {
            String email = usernameField.getText();
            String password = passwordField.getText();

            // داخل loginButton.setOnAction
if ((email.equals("kerlosmelad@gmail.com") && password.equals("1"))   || 
        (email.equals("AmrKhaled@gmail.com") && password.equals("2")) ||
        (email.equals("Elhasan@gmail.com") && password.equals("3"))   ||
        (email.equals("AkramMagdy@gmail.com") && password.equals("4"))   ||
        (email.equals("AhmedAnas@gmail.com") && password.equals("5")) ) {
    String name = email.split("@")[0];

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Login Successful");
    alert.setHeaderText(null);
    alert.setContentText("Welcome, " + name + "!");
    alert.show();

    final String finalName = name;
    DashboardPage dashboardPage = new DashboardPage();
    dashboardPage.start(primaryStage, finalName);

}

else {
    
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Login Failed");
    alert.setHeaderText(null);
    alert.setContentText("Invalid Email or Passowrd");
    alert.show();
}

        });

        // Center VBox layout
        VBox vbox = new VBox(15, mainTitle , titleLabel, usernameField, passwordField, loginButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(40));

        BorderPane root = new BorderPane();
        root.setTop(topPane);
        root.setCenter(vbox);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
