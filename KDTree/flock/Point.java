package flock;

import java.util.*;

public class Point {

    private ArrayList<Double> coords;
    private int dim;

    public Point(ArrayList<Double> coords) {
        this.coords = coords;
        this.dim = coords.size();
    }

    public double get(int d) {
        return coords.get(d);
    }

    public int dim() {
        return this.dim;
    }

    /**
     * Returns the euclidean distance (L2 norm) squared between two points
     * (x1, y1) and (x2, y2). Note: This is the square of the Euclidean distance,
     * i.e. there's no square root.
     */
    private static double distance(ArrayList<Double> coords1, ArrayList<Double> coords2) {
        double totd = 0.0;
        int d = coords1.size();
        for (int i = 0; i < d; i++) {
            totd += Math.pow(coords1.get(i) - coords2.get(i), 2);
        }
        return totd;
    }

    /**
     * Returns the euclidean distance (L2 norm) squared between two points.
     * Note: This is the square of the Euclidean distance, i.e.
     * there's no square root. 
     */
    public static double distance(Point p1, Point p2) {
        return distance(p1.coords, p2.coords);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Point otherp = (Point) other;
        for (int i = 0; i < otherp.coords.size(); i++){
            if (coords.get(i) != otherp.coords.get(i)) {return false;}
        }
        return true;
    }

    @Override
    public int hashCode() {
        int tothash = 1;
        for (int i = 0; i < coords.size(); i++){
            double curc = coords.get(i);
            tothash *= Double.hashCode(curc);
        }
        return tothash;
    }
}
