package com.ursus_dei.concept_maps.ui;
import javafx.scene.input.KeyEvent;

import javafx.event.EventHandler;

public class KeyboardHandler implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {
        System.out.println("KEY: " + event);
    }
}
