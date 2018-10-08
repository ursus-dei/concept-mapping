package com.ursusdei.region_detector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Region {

    private final Set<Point> points = new HashSet<Point>();
    private final Set<Region> neighbors = new HashSet<Region>();
    private boolean updated = false;

    private Color color = null;

    public Region(Point point) {
        points.add(point);
        color = new Color(point.color.getRGB());
    }

    public void addNeighbor(Region region) {
        neighbors.add(region);
    }

    public Set<Point> getPoints() {
        return points;
    }

    public Color getColor() {
        if (updated) {
            double sumR = 0;
            double sumB = 0;
            double sumG = 0;
            for (Point point : points) {
                sumR += point.color.getRed();
                sumB += point.color.getBlue();
                sumG += point.color.getGreen();
            }
            int r = (int) (sumR / points.size());
            int g = (int) (sumG / points.size());
            int b = (int) (sumB / points.size());
            color = new Color(r, g, b);
            System.out.println("Color updated");
            updated = false;
        }
        return color;
    }

    public Set<Region> getNeighbors() {
        return neighbors;
    }

    public void merge(Set<Region> branches) {
        updated = true;
        for (Region branch : branches) {
            neighbors.addAll(branch.neighbors);
            points.addAll(branch.points);
        }
        neighbors.removeAll(branches);
        neighbors.remove(this);
        for (Point point : points) {
            point.setRegion(this);
        }
    }

    public double distanceTo(Region neighbor) {
        return colorDistance(getColor(), neighbor.getColor());
    }

    private double colorDistance(Color c1, Color c2) {
        double r = c1.getRed() - c2.getRed();
        double g = c1.getGreen() - c2.getGreen();
        double b = c1.getBlue() - c2.getBlue();
        return Math.sqrt(r * r + g * g + b * b);
    }
}
