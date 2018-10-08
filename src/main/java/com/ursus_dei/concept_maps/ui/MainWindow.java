package com.ursus_dei.concept_maps.ui;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.ursus_dei.concept_maps.controller.MainController;

public class MainWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String title = MainController.windowTitle();
        int width = MainController.windowWidth();
        int height = MainController.windowHeight();

        primaryStage.setTitle(title);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, width, height);

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("mouse click detected! " + mouseEvent.getSource());
                    }
                });

        scene.addEventFilter(KeyEvent.ANY,
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        System.out.println("key event " + event);
                    }
                });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(helloButton());

        root.setTop(menuBar());
        root.setCenter(stackPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button helloButton() {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(actionEvent -> {
                    System.out.println("Hello World!");
                    MainController.control();
                }
        );
        return btn;
    }

    private MenuBar menuBar() {
        MenuBar menuBar = new MenuBar();
        // File menu - new, save, exit
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem,
                new SeparatorMenuItem(), exitMenuItem);

        Menu webMenu = new Menu("Web");
        CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
        htmlMenuItem.setSelected(true);
        webMenu.getItems().add(htmlMenuItem);

        CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
        cssMenuItem.setSelected(true);
        webMenu.getItems().add(cssMenuItem);

        Menu sqlMenu = new Menu("SQL");
        ToggleGroup tGroup = new ToggleGroup();
        RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
        mysqlItem.setToggleGroup(tGroup);

        RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
        oracleItem.setToggleGroup(tGroup);
        oracleItem.setSelected(true);

        sqlMenu.getItems().addAll(mysqlItem, oracleItem,
                new SeparatorMenuItem());

        Menu tutorialManeu = new Menu("Tutorial");
        tutorialManeu.getItems().addAll(
                new CheckMenuItem("Java"),
                new CheckMenuItem("JavaFX"),
                new CheckMenuItem("Swing"));

        sqlMenu.getItems().add(tutorialManeu);

        menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);
        return menuBar;
    }
}

