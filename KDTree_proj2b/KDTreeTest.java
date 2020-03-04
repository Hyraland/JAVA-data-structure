package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void simpleKDTreeNearestCheck() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(2, 3));
        points.add(new Point(4, 2));
        points.add(new Point(4, 5));
        points.add(new Point(3, 3));
        points.add(new Point(1, 5));
        points.add(new Point(4, 4));

        KDTree newkd = new KDTree(points);
        NaivePointSet newnv = new NaivePointSet(points);
        assertEquals(newkd.nearest(0,5), newnv.nearest(0,5));
        assertEquals(newkd.nearest(2,3), newnv.nearest(2,3));
        assertEquals(newkd.nearest(7,7), newnv.nearest(7,7));
    }

    @Test
    public void randomKDTreeNearestCheck() {
        ArrayList<Point> points = new ArrayList<Point>();
        Random r =new Random();

        for (int i = 0; i < 50;i++) {
            points.add(new Point(r.nextDouble() * 20, r.nextDouble() * 20));
        }

        KDTree newkd = new KDTree(points);
        NaivePointSet newnv = new NaivePointSet(points);
        assertEquals(newkd.nearest(0,5), newnv.nearest(0,5));
        assertEquals(newkd.nearest(2,3), newnv.nearest(2,3));
        assertEquals(newkd.nearest(7,7), newnv.nearest(7,7));
    }
}
