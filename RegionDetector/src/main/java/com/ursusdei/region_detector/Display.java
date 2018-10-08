package com.ursusdei.region_detector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display {

    public Display(BufferedImage img, Integer p) {
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(img.getWidth(),img.getHeight());
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setTitle(p.toString());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
