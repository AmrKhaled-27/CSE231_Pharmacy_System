package pharmacysystem;

import pharmacysystem.RecipePage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DashboardPage {

    public void start(Stage stage, String name) {
        Label welcomeLabel = new Label("Welcome, " + name + "!");
        welcomeLabel.setFont(new Font("Arial", 26));
        welcomeLabel.setTextFill(Color.DARKSLATEBLUE);

        Button btn1 = createStyledButton("Manage Items");
        Button btn2 = createStyledButton("Orders");
        Button btn3 = createStyledButton("Recipes");
        Button btn4 = createStyledButton("Payments");

        // 🟢 زرار Recipes يفتح صفحة RecipePage
        btn3.setOnAction(e -> {
            RecipePage recipePage = new RecipePage();
            recipePage.start(stage);
        });

        VBox buttonsBox = new VBox(15, btn1, btn2, btn3, btn4);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(25, welcomeLabel, buttonsBox);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene dashboardScene = new Scene(layout, 400, 500);
        stage.setScene(dashboardScene);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(200);
        button.setFont(new Font("Arial", 16));
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 10;");
        return button;
    }
}
