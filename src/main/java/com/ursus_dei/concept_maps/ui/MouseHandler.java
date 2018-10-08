package com.ursus_dei.concept_maps.ui;


import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class MouseHandler implements EventHandler<MouseEvent> {

    static private EventType lastType = MouseEvent.ANY;

    @Override
    public void handle(MouseEvent event) {
        //Diagram.INSTANCE.drawCircle((int)event.getSceneX(),(int)event.getSceneY());
//        if (event.getEventType() != lastType) {
//            lastType = event.getEventType();
//            System.out.println("MOUSE: " + lastType);
//        }
//        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
//            Diagram.INSTANCE.select(event.getSceneX(), event.getSceneY());
//        }
//        Diagram.INSTANCE.render();
        MainWindow.line.setEndX(event.getSceneX());
        MainWindow.line.setEndY(event.getSceneY());
    }

}
