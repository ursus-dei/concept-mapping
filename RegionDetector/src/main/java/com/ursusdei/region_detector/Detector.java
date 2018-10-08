package com.ursusdei.region_detector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Detector {

    public static void main(String args[]) {

        String pathname = "/Users/dbenefi/test.jpeg";
//        String pathname = "/Users/dbenefi/Workspace/Personal/sims_14.png";

        try {
            BufferedImage img = ImageIO.read(new File(pathname));
            Regions regions = new Regions(img);
            for (int i=0; i<100; ++i) {
                double d = regions.computeDistances();
                System.out.println(d);
                regions.mergeTo(d);
                new Display(regions.getImage(), i);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//
//    private static void merge(BufferedImage img, Regions regions) throws IOException {
//        double sumErr = 0;
//        double sumReg = 0;
//        double lastAvgErr = -1;
//        double lastAvgReg = -1;
//        double goal = 0.001;
//        double cnt = 21;
//        int p = 0;
//        double slope = 0;
//        while (regions.size() > 1) {
//            double avgErr = sumErr / (cnt * regions.maxError);
//            double avgReg = sumReg / (cnt * regions.maxRegions);
//            double vErr = avgErr - lastAvgErr;
//            double vReg = avgReg - lastAvgReg;
//            slope = -vErr / vReg;
//            double dist = Math.sqrt(vErr * vErr + vReg * vReg);
//            if (cnt > 20 && dist > 0.01 && slope < 1.1) {
//                if (dist > 0.02) {
//                    goal /= 2;
//                }
//                ++p;
//                System.out.println(p + "\t" + avgReg + "\t" + avgErr + "\t" + dist + "\t" + goal);
//                BufferedImage updated = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
//                File file = new File("sims_" + p + ".png");
//                ImageIO.write(regions.populateImage(updated), "png", file);
//                lastAvgErr = avgErr;
//                lastAvgReg = avgReg;
//                sumErr = 0;
//                sumReg = 0;
//                cnt = 0;
//            } else if (cnt > 20) {
//                goal = 2 * goal;
//            }
//            sumErr += regions.error();
//            sumReg += regions.size();
//            ++cnt;
//            regions.mergeTo(regions.minDistance() + goal);
//        }
//    }
//
}
