package lab10;


import java.awt.*;

public class Ground implements XmasShape {
    int[] xc = {-1000, -1000, 1000, 1000};
    int[] yc = {-200, 0, 0, -200};
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    public Ground(int i, int i1, int i2) {
        x = i;
        y = i1;
        scale = i2;
    }

    public Ground(int x, int y, double scale, Color fillColor) {
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