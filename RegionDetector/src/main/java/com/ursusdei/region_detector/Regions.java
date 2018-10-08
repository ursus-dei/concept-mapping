package com.ursusdei.region_detector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Regions {

    public final int width;
    public final int height;
    public final int type;

    public final Point[][] points;
    public final Set<Region> regions;

    public Regions(BufferedImage img) {
        width = img.getWidth();
        height = img.getHeight();
        type = img.getType();
        points = new Point[width][height];
        regions = new HashSet<Region>();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                points[x][y] = new Point(x, y, img.getRGB(x, y));
                regions.add(points[x][y].getRegion());
            }
        }
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int dx = -1; dx < 2 && 0 <= x + dx && x + dx < width; ++dx) {
                    for (int dy = -1; dy < 2 && 0 <= y + dy && y + dy < height; ++dy) {
                        if (dx != 0 || dy != 0) {
                            points[x][y].addNeighbor(points[x + dx][y + dy], dx, dy);
                        }
                    }
                }
            }
        }
        initialMerge();
    }

    public BufferedImage getImage() {
        BufferedImage img = new BufferedImage(width, height, type);
        for (Region region : regions) {
            int rgb = region.getColor().getRGB();
//            Random rand = new Random();
//            Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
//            rgb = color.getRGB();
            for (Point point : region.getPoints()) {
                img.setRGB(point.x, point.y, rgb);
            }
        }
        return img;
    }

    private void initialMerge() {
        Set<Region> visited = new HashSet<Region>();
        Set<Region> merged = new HashSet<Region>();

        System.out.println(regions.size());
        for (Region root : regions) {
            if (!visited.contains(root)) {
                Set<Region> branches = new HashSet<Region>();
                branches.add(root);
                boolean expanded = false;
                while (!expanded) {
                    expanded = true;
                    Set<Region> leafs = new HashSet<Region>();
                    for (Region branch : branches) {
                        if (!visited.contains(branch)) {
                            visited.add(branch);
                            for (Region neighbor : branch.getNeighbors()) {
                                if (!visited.contains(neighbor)) {
                                    if (neighbor.getColor().getRGB() == branch.getColor().getRGB()) {
                                        leafs.add(neighbor);
                                        expanded = false;
                                    }
                                }
                            }
                        }
                    }
                    branches.addAll(leafs);
                }
                if (branches.size() > 1) {
                    branches.remove(root);
                    root.merge(branches);
                    merged.addAll(branches);
                }
            }
        }
        regions.removeAll(merged);
        System.out.println(regions.size());
    }

    public double computeDistances() {
        List<Double> distances = new ArrayList<Double>();
        for (Region region : regions) {
            for (Region neighbor : region.getNeighbors()) {
                distances.add(region.distanceTo(neighbor));
            }
        }
        Collections.sort(distances);
        return distances.get(10000);
    }

    public void mergeTo(double d) {
        Set<Region> visited = new HashSet<Region>();
        Set<Region> merged = new HashSet<Region>();

        System.out.println(regions.size());
        for (Region root : regions) {
            if (!visited.contains(root)) {
                Set<Region> branches = new HashSet<Region>();
                branches.add(root);
                boolean expanded = false;
                while (!expanded) {
                    expanded = true;
                    Set<Region> leafs = new HashSet<Region>();
                    for (Region branch : branches) {
                        if (!visited.contains(branch)) {
                            visited.add(branch);
                            for (Region neighbor : branch.getNeighbors()) {
                                if (!visited.contains(neighbor)) {
                                    if (branch.distanceTo(neighbor) <= d + 0.001) {
                                        leafs.add(neighbor);
                                        expanded = false;
                                    }
                                }
                            }
                        }
                    }
                    branches.addAll(leafs);
                }
                if (branches.size() > 1) {
                    branches.remove(root);
                    root.merge(branches);
                    merged.addAll(branches);
                }
            }
        }
        regions.removeAll(merged);
        System.out.println(regions.size());
    }
}


