package com.ursus_dei.concept_maps.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Diagram extends Canvas {

    static final public Diagram INSTANCE = new Diagram();

    private List<Node> nodes = new ArrayList<Node>();

    private Diagram() {
        super();
        nodes.add(new Node());
    }

    static public void render() {
        GraphicsContext gc = INSTANCE.getGraphicsContext2D();
        System.out.println(INSTANCE.getLayoutX());
        for (Node node : INSTANCE.nodes) {
            gc.strokeRect(node.x, node.y, node.w, node.h);
            Text t = new Text(node.x, node.y, node.n);
            System.out.println(t.getBoundsInLocal());
            //gc.strokeText(node.n, node.x, node.y);
        }
    }

    static public void select(double x, double y) {

        double hd = TopMenu.INSTANCE.getHeight();
        for (Node node : INSTANCE.nodes) {
            if (node.x <= x && x <= node.x + node.w &&
                    node.y <= y - hd && y - hd < node.y + node.h) {
                System.out.println(node.n);
                return;
            }
        }
        System.out.println("None");
    }

}
