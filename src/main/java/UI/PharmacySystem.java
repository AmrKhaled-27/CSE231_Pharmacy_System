package UI;

import javafx.application.Application;
import javafx.stage.Stage;

public class PharmacySystem extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginPage loginPage = new LoginPage();
        loginPage.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
