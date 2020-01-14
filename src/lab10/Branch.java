package lab10;

import java.awt.*;

public class Branch implements XmasShape {
    int[] xc = {-30, 0, 0};
    int[] xc1 = {30, 0, 0};
    int[] yc = {30, 0, 30};
    double scale;
    int x;
    int y;
    boolean side = false;

    Color fillColor;

    public Branch(int x, int y, double scale) {
        this.scale = scale;
        this.x = x;
        this.y = y;
    }

    public Branch(int x, int y, double scale, Color fillColor) {
        this.scale = scale;
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
    }

    public Branch(int x, int y, double scale, Color fillColor, boolean side) {
        this.scale = scale;
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        this.side = side;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);

        if (side) {
            g2d.fillPolygon(xc1, yc, xc.length);
        } else {
            g2d.fillPolygon(xc, yc, xc.length);
        }
    }
}
