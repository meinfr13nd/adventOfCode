import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;
import java.awt.Point;

public class day6Part1 extends AdventOfCode {
//3223

ArrayList<Point> pts = new ArrayList<Point>();
int minX = Integer.MAX_VALUE;
int maxX = Integer.MIN_VALUE;
int minY = Integer.MAX_VALUE;
int maxY = Integer.MIN_VALUE;
HashMap<Point, Integer> closestTo = new HashMap<Point, Integer>();

public day6Part1(String fileName) {
        super(fileName);
}

protected String getAnswer() {
        exploreMap();
        removeInfinite();
        int maxClosest = Integer.MIN_VALUE;
        for (Point pt : closestTo.keySet()) {
                if (closestTo.get(pt) > maxClosest) {
                        maxClosest = closestTo.get(pt);
                }
        }
        return ((Integer)maxClosest).toString();
}

private static Point nearestPoint(Point p, ArrayList<Point> pts) {
        Point minPoint = new Point(0, 0);
        int numAtDist = 0;
        int minDist = Integer.MAX_VALUE;
        for (Point pt: pts) {
                int dist = Math.abs(p.x - pt.x) + Math.abs(p.y - pt.y);
                if (dist < minDist) {
                        minDist = dist;
                        numAtDist = 1;
                        minPoint = pt;
                } else if (dist == minDist) {
                        numAtDist++;
                }
        }
        if (numAtDist > 1) {
                return null;
        } else {
                return minPoint;
        }
}

protected Object processLine(String line) {
        String numbers[];
        numbers = line.split(",");
        int x = Integer.parseInt(numbers[0].trim());
        int y = Integer.parseInt(numbers[1].trim());
        Point p = new Point(x, y);
        pts.add(p);
        if (y > maxY) {
                maxY = y;
        } else if (y < minY) {
                minY = y;
        }
        if (x > maxX) {
                maxX = x;
        } else if (x < minX) {
                minX = x;
        }
        closestTo.put(p, 0);
        return null;
}

private void exploreMap() {
        for (int i = minX; i <= maxX; i++) {
                for (int j = minY; j <= maxY; j++) {
                        Point p = new Point(i, j);
                        Point closest = nearestPoint(p, pts);
                        if (closest != null) {
                                closestTo.put(closest, closestTo.get(closest) + 1);
                        }
                }
        }
}

private void removeInfinite() {
        HashSet<Point> toRemove = new HashSet<Point>();
        for (int i = minX; i <= maxX; i++) {
                toRemove.add(nearestPoint(new Point(i, minY), pts));
                toRemove.add(nearestPoint(new Point(i, maxY), pts));
        }
        for (int j = minY; j <= maxY; j++) {
                toRemove.add(nearestPoint(new Point(minX, j), pts));
                toRemove.add(nearestPoint(new Point(maxX, j), pts));
        }
        for (Point p : toRemove) {
                closestTo.remove(p);
        }
}


}
