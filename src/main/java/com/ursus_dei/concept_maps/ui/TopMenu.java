package com.ursus_dei.concept_maps.ui;

import javafx.application.Platform;
import javafx.scene.control.*;

public class TopMenu extends MenuBar {

    public final static TopMenu INSTANCE = new TopMenu();

    private TopMenu() {
        super();
        getMenus().addAll(fileMenu());
    }

    private Menu fileMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem,
                new SeparatorMenuItem(), exitMenuItem);

        return fileMenu;
    }

}
