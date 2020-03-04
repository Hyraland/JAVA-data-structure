package bearmaps;

import java.util.*;

public class KDTree {
    private ArrayList<Double> trees = null;
    private KDTree lchild;
    private KDTree rchild;
    private int D;

    public KDTree() {
        trees = null;
        lchild = null;
        rchild = null;
        D = 0;
    }

    public KDTree(ArrayList<Point> points){
        int D = 0;
        int n = points.size();
        if (n == 0) {
            trees = null;
            lchild = null;
            rchild = null;
            this.D = 0;
            return;
        }

        KDTree nodetrack;
        Point p = points.get(0);
        trees = new ArrayList<Double>();
        trees.add(p.getX());
        trees.add(p.getY());

        for (int i = 1; i < n; i++) {
            p = points.get(i);
            nodetrack = this;
            while (true) {
                if(insert(p, nodetrack) && nodetrack.lchild == null) {
                    nodetrack.lchild = new KDTree();
                    nodetrack.lchild.trees = new ArrayList<Double>();
                    nodetrack.lchild.trees.add(p.getX());
                    nodetrack.lchild.trees.add(p.getY());
                    nodetrack.lchild.D = (nodetrack.D + 1)%2;
                    break;
                }else if(!insert(p, nodetrack) && nodetrack.rchild == null) {
                    nodetrack.rchild = new KDTree();
                    nodetrack.rchild.trees = new ArrayList<Double>();
                    nodetrack.rchild.trees.add(p.getX());
                    nodetrack.rchild.trees.add(p.getY());
                    nodetrack.rchild.D = (nodetrack.D + 1)%2;
                    break;
                }else if(insert(p, nodetrack)) {nodetrack = nodetrack.lchild;}
                else {nodetrack = nodetrack.rchild;}
            }
        }
    }

    private boolean insert(Point p, KDTree kdtree) {
        Point ptree = new Point(kdtree.trees.get(0), kdtree.trees.get(1));
        return Compare(kdtree.D, p, ptree);
    }

    private boolean Compare(int D, Point p, Point pc) {
        if (D == 0 && p.getX() < pc.getX()) {
            return true; // if goodside on the left, return true, otherwise return false
        }else if (D == 0 && p.getX() >= pc.getX()) {
            return false;
        }
        if (D == 1 && p.getY() < pc.getY()) {
            return true;
        }else if (D == 1 && p.getY() >= p.getY()) {
           return false;
        }
        return false;
    }

    public Point nearest(double x, double y) {
        Point curp = new Point(x, y);
        KDTree best = nearest(curp, this, this);
        Point pbest = new Point(best.trees.get(0), best.trees.get(1));
        return pbest;
    }

    private KDTree nearest(Point goal, KDTree node, KDTree best) {
        if (node == null) {
            return best;
        }
        KDTree goodside = null;
        KDTree badside = null;
        Point pnode = new Point(node.trees.get(0), node.trees.get(1));
        Point pbest = new Point(best.trees.get(0), best.trees.get(1));

        if (Point.distance(goal, pnode) < Point.distance(goal, pbest)) {
            best = node;
        }
        if (Compare(node.D, goal, pnode)) {
            goodside = node.lchild;
            badside = node.rchild;
        }else {
            goodside = node.rchild;
            badside = node.lchild;
        }
        best = nearest(goal, goodside, best);
        if ((node.D == 0 && (goal.getX() - pnode.getX()) < Point.distance(goal, pbest))
                || (node.D == 1 && (goal.getX() - pnode.getX()) < Point.distance(goal, pbest))) {
            best = nearest(goal, badside, best);
        }
        return best;
    }
}
