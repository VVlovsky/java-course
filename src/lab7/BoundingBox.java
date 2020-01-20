package lab7;

public class BoundingBox {
    double xmin = Double.longBitsToDouble(0x7ff8000000000000L);
    double ymin = Double.longBitsToDouble(0x7ff8000000000000L);
    double xmax = Double.longBitsToDouble(0x7ff8000000000000L);
    double ymax = Double.longBitsToDouble(0x7ff8000000000000L);

//    BoundingBox (double xmin, double xmax, double ymin, double ymax){
//        this.xmin = xmin;
//        this.xmax = xmax;
//        this.ymin = ymin;
//        this.ymax = ymax;
//    }

    void addPoint(double x, double y) {
        if (!this.isEmpty()) {
            if (x < xmin) xmin = x;
            if (x > xmax) xmax = x;
            if (y < ymin) ymin = y;
            if (y > ymax) ymax = y;
        } else {
            xmin = x;
            xmax = x;
            ymin = y;
            ymax = y;
        }
    }

    boolean contains(double x, double y) {
        return x < xmax && x > xmin && y > ymin && y < ymax;
    }

    boolean contains(BoundingBox bb) {
        return ymin < bb.ymin && ymax > bb.ymax && xmin < bb.xmin && xmax > bb.xmax;
    }


    boolean intersects(BoundingBox bb) {
        return (bb.xmin < xmax && bb.xmin > xmin && ((bb.ymin > ymin && bb.ymin < ymax) || (bb.ymax < ymax && bb.ymax > ymin))) ||
                (bb.xmax < xmax && bb.xmax > xmin && ((bb.ymin > ymin && bb.ymin < ymax) || (bb.ymax < ymax && bb.ymax > ymin)));

    }


    BoundingBox add(BoundingBox bb) {
        if (bb.ymax > ymax) ymax = bb.ymax;
        if (bb.ymin < ymin) ymin = bb.ymin;
        if (bb.xmin < xmin) xmin = bb.xmin;
        if (bb.xmax > xmax) xmax = bb.xmax;
        return this;
    }


    boolean isEmpty() {
        return Double.isNaN(xmin) || Double.isNaN(xmax) || Double.isNaN(ymax) || Double.isNaN(ymin);
    }


    double getCenterX() {
        if (this.isEmpty()) {
            throw new RuntimeException("Not implemented");
        }
        return (xmax + xmin) / 2;
    }

    double getCenterY() {
        if (this.isEmpty()) {
            throw new RuntimeException("Not implemented");
        }
        return (ymax + ymin) / 2;
    }

    //(ang. haversine formula)
    double distanceTo(BoundingBox bbx) {
        return Math.sqrt(Math.pow(this.getCenterX() - bbx.getCenterX(), 2) + Math.pow(this.getCenterY() - bbx.getCenterY(), 2));
    }

    public String toString(){
        StringBuilder b = new StringBuilder();
        b.append("[" + xmin + ", " + ymin + "] [" + xmax + ", " + ymax + "]\n");
        return b.toString();
    }

}
