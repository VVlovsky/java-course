package lab10;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class DrawPanel extends JPanel {


    public List<XmasShape> shapes = new ArrayList<>();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (XmasShape s : shapes) {
            s.draw((Graphics2D) g);
        }

    }

    public void add(XmasShape xs) {
        shapes.add(xs);
    }

}