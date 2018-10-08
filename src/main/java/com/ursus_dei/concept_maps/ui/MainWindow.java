package com.ursus_dei.concept_maps.ui;

import com.ursus_dei.concept_maps.controller.MainController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {

    final private Diagram diagram = Diagram.INSTANCE;
    final private TopMenu topMenu = TopMenu.INSTANCE;

    final public static Line line = new Line(0,0,500,500);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String title = MainController.windowTitle();
        int width = MainController.windowWidth();
        int height = MainController.windowHeight();

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, width, height);

        final Text text1 = new Text(50, 50, "(2007) JavaFX based on F3");
        text1.setFill(Color.CHOCOLATE);
        text1.setFont(Font.font(java.awt.Font.SERIF, 25));
        root.getChildren().add(text1);

        root.getChildren().add(line);



        primaryStage.setTitle(title);
//        primaryStage.widthProperty().addListener(
//                (observable, oldValue, newValue) ->
//                        diagram.setWidth(newValue.doubleValue()));
//        primaryStage.heightProperty().addListener(
//                (observable, oldValue, newValue) ->
//                        diagram.setHeight(newValue.doubleValue()));

        scene.addEventFilter(MouseEvent.ANY, new MouseHandler());
        scene.addEventFilter(KeyEvent.ANY, new KeyboardHandler());

        root.setTop(topMenu);
        //root.setCenter(diagram);


        primaryStage.setScene(scene);
        primaryStage.show();

        diagram.render();

    }

}

