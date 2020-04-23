//I worked on the homework assignment alone, using only course materials.

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalTime;

/**
 * The CafeReviewPage class.
 * @author - Marylyn Chen
 * @version - 4.2
 */
public class CafeReviewPage extends Application {
    /**
     * The main method that starts the program.
     * @param args - the args passed in
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cafe1331 Reviews");

        //Included a title and formatted it with CSS.
        Label title = new Label("Reviews");
        title.setStyle("-fx-font: 28px \"Lucida Console\"; -fx-padding: 12 0 0 0;");
        title.setAlignment(Pos.CENTER);
        Button btn = new Button("Post");

        //Created a new button that clears away the messages.
        Button clear = new Button("Clear");

        //Used listview to contain messages and set its size and background image (Buzz!) when there are no messages.
        ListView<Text> list = new ListView<>();
        list.setMinSize(350, 500);
        list.setStyle("-fx-background-image: url(\"http://images.tritondigitalcms.com/1453/sites/2/2017/11/"
                + "22125118/georgia-tech-yellow-jackets-buzz-300x300.png\");-fx-background-position: center "
                + "center; -fx-background-repeat: no-repeat; -fx-border-color: black;");
        TextField name = new TextField();
        name.setPromptText("name");
        TextField message = new TextField();
        message.setPromptText("message");

        //Created a Combobox to show the rating from a 1 to 5 scale.
        ComboBox rating = new ComboBox();
        rating.setPromptText("rating");
        rating.getItems().addAll("rating", 1, 2, 3, 4, 5);
        TextField color = new TextField();
        color.setPromptText("color");

        btn.setOnAction(e -> {
            Text n;
            StringBuilder star = new StringBuilder();
            //Created stars to represent the rating :)
            try {
                star.append("★".repeat(Math.max(0, (int) rating.getValue())));
            } catch (Exception ignored) {
            }
            //Created a timestamp for every new message and set it equal to the hour and minute.
            LocalTime currentTime = LocalTime.now();
            String min;
            //Added a 0 in front of the minute if it was less than 10.
            if (currentTime.getMinute() < 10) {
                min = "0" + currentTime.getMinute();
            } else {
                min = "" + currentTime.getMinute();
            }
            String time = currentTime.getHour() + ":" + min;
            if (name.getText().equals("")) {
                n = new Text(time + " Anonymous: " + star);
            } else {
                n = new Text(time + " " + name.getText() + ": " + star);
            }
            Text m = new Text(message.getText());
            try {
                Color c = Color.web(color.getText());
                n.setFill(Paint.valueOf(c.toString()));
                m.setFill(Paint.valueOf(c.toString()));
            } catch (Exception ignored) {
            }
            if (!message.getText().equals("")) {
                list.getItems().add(n);
                list.getItems().add(m);
                //Wrapped the width of the text to the listview's width minus 15.
                n.wrappingWidthProperty().bind(list.widthProperty().subtract(18));
                m.wrappingWidthProperty().bind(list.widthProperty().subtract(18));
                name.setText("");
                message.setText("");
                rating.setValue("rating");
                color.setText("");
                list.setStyle("-fx-border-color: black;");
            }
        });

        //Created a KeyEvent that closes the window when the Escape button is pressed after pressing clear.
        clear.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                primaryStage.close();
            }
        });

        //Created a KeyEvent that enters the message when the Enter button is pressed after entering color.
        color.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                Text n;
                StringBuilder star = new StringBuilder();
                //Created stars to represent the rating :)
                try {
                    star.append("★".repeat(Math.max(0, (int) rating.getValue())));
                } catch (Exception ignored) {
                }
                //Created a timestamp for every new message and set it equal to the hour and minute.
                LocalTime currentTime = LocalTime.now();
                String min;
                //Added a 0 in front of the minute if it was less than 10.
                if (currentTime.getMinute() < 10) {
                    min = "0" + currentTime.getMinute();
                } else {
                    min = "" + currentTime.getMinute();
                }
                String time = currentTime.getHour() + ":" + min;
                if (name.getText().equals("")) {
                    n = new Text(time + " Anonymous: " + star);
                } else {
                    n = new Text(time + " " + name.getText() + ": " + star);
                }
                Text m = new Text(message.getText());
                try {
                    Color c = Color.web(color.getText());
                    n.setFill(Paint.valueOf(c.toString()));
                    m.setFill(Paint.valueOf(c.toString()));
                } catch (Exception ignored) {
                }
                if (!message.getText().equals("")) {
                    list.getItems().add(n);
                    list.getItems().add(m);
                    //Wrapped the width of the text to the listview's width minus 18.
                    n.wrappingWidthProperty().bind(list.widthProperty().subtract(18));
                    m.wrappingWidthProperty().bind(list.widthProperty().subtract(18));
                    name.setText("");
                    message.setText("");
                    rating.setValue("rating");
                    color.setText("");
                    list.setStyle("-fx-border-color: black;");
                }
            }
        });

        //Set the clear button's function to clear the messages and reinstate Buzz :)
        clear.setOnAction(e -> {
            list.setItems(FXCollections.observableArrayList());
            list.setStyle("-fx-background-image: url(\"http://images.tritondigitalcms.com/1453/sites/2/2017/11/"
                + "22125118/georgia-tech-yellow-jackets-buzz-300x300.png\");-fx-background-position: center "
                + "center; -fx-background-repeat: no-repeat; -fx-border-color: black;");
        });

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(name, message, rating, color, btn, clear);
        VBox vbox = new VBox(12);
        StackPane temp = new StackPane();
        temp.getChildren().add(title);
        vbox.getChildren().addAll(temp, list, hbox);

        //Changed the background color to represent GT colors and also added a black border.
        vbox.setStyle("-fx-background-color: #c5c07d; -fx-border-color: black;");
        primaryStage.setScene(new Scene(vbox, 700, 605));
        primaryStage.show();
    }
}
