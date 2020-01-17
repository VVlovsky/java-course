package lab10;

import java.awt.*;

public class SquareBubble implements XmasShape {
    int[] xc = {-5, 0, 5, 0};
    int[] yc = {0, -10, 0, 10};
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    public SquareBubble(int i, int i1, int i2) {
        x = i;
        y = i1;
        scale = i2;
    }

    public SquareBubble(int x, int y, double scale, Color fillColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;

        this.fillColor = fillColor;
        this.lineColor = Color.black;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillPolygon(xc, yc, xc.length);
        g2d.setColor(lineColor);
        g2d.drawPolygon(xc, yc, xc.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}