package com.ursusdei.region_detector;

import java.awt.*;
import java.util.Set;
import java.util.HashSet;

public class Point {

    public final int x;
    public final int y;
    public final Color color;

    private Region region;
    private Point[][] neighbors = new Point[3][3];
    private Set<Point> neighborSet = new HashSet<Point>();

    public Point(int x, int y, int rgb) {
        this.x = x;
        this.y = y;
        this.color = new Color(rgb);
        region = new Region(this);
    }

    public Region getRegion() {
        return region;
    }

    public void addNeighbor(Point point, int dx, int dy) {
        neighbors[1 + dx][1 + dy] = point;
        neighborSet.add(point);
        region.addNeighbor(point.region);
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
