package lab10;

import java.awt.*;

public class Star implements XmasShape {
    int[] xc = {-5, 0, 5, 3, 6, 2, 0, -2, -6, -3};
    int[] yc = {5, 2, 5, 1, -2, -3, -7, -3, -2, 1, 5, 3};
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    public Star(int i, int i1, int i2) {
        x = i;
        y = i1;
        scale = i2;
    }

    public Star(int x, int y, double scale, Color fillColor) {
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
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}