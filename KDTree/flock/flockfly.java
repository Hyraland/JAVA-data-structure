package flock;

import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class flockfly {

    public static void main(String[] args) {
        double unirad = 500;
        int nb = 50;
        int k = 20;
        double sepfrac = 3.0;
        double cohfrac = 0.25;
        double alifrac = 0.001;
        double curT = 0;
        double T = 100; // Double.valueOf(args[0]);
        double dt = 0.01; // Double.valueOf(args[1]);
        ArrayList<Point> ps = new ArrayList<>(nb);
        ArrayList<Point> vs = new ArrayList<>(nb);
        Random r = new Random();
        ArrayList<Double> tempp;
        for (int i = 0; i < nb; i++){
            tempp = new ArrayList<Double>();
            tempp.add(r.nextDouble()*0.2*unirad);
            tempp.add(r.nextDouble()*0.2*unirad);
            Point p = new Point(tempp);
            tempp = new ArrayList<Double>();
            tempp.add(r.nextDouble()*unirad);
            tempp.add(r.nextDouble()*unirad);
            Point v = new Point(tempp);
            ps.add(p);
            vs.add(v);
        }
        boids b = new boids(nb, ps, vs);

        StdDraw.setScale(-1 * unirad, unirad);
        StdDraw.setPenRadius(0.005);
        StdDraw.clear();
        for (int i = 0; i < nb; i++) {
            StdDraw.point(b.posof(i).get(0), b.posof(i).get(1));
        }

        StdDraw.enableDoubleBuffering();
        while (curT <= T) {
            for (int i = 0; i < nb; i++) {
                b.separation(1, i, sepfrac);
                b.cohesion(k*2, i, cohfrac);
                b.alignment(k, i, alifrac);
                //b.upvel(b.velof(i), i);
                b.posof(i).addmul(b.velof(i), dt);
                //b.uppos(newp, i);
            }

            StdDraw.clear();
            for (int i = 0; i < nb; i++) {
                double x = b.posof(i).get(0);
                double y = b.posof(i).get(1);
                while (x > unirad) {x -= 2*unirad;}
                while (y > unirad) {y -= 2*unirad;}
                StdDraw.point(x,y);
            }
            StdDraw.show();
            StdDraw.pause(10);
            curT += dt;
        }
    }
}
