package lab10;

import java.awt.*;

public class Wood implements XmasShape {
    int[] xc = {-20, 20, 20, -20};
    int[] yc = {0, 0, 70, 70};
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    public Wood(int i, int i1, int i2) {
        x = i;
        y = i1;
        scale = i2;
    }

    public Wood(int x, int y, double scale, Color fillColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;

        this.fillColor = fillColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillPolygon(xc, yc, xc.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}