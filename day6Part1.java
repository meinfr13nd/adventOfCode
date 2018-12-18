import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;



public class day6part1 {
//3223
private static Point nearestPoint(Point p, ArrayList<Point> pts, ArrayList<Point> realPts, HashSet<Point> edges) {
        Point minPoint = new Point(0, 0);
        int numAtDist = 0;
        int minDist = Integer.MAX_VALUE;
        for (Point pt: pts) {
                int dist = p.distance(pt);
                if (dist < minDist) {
                        minDist = dist;
                        numAtDist = 1;
                        minPoint = pt;
                } else if (dist == minDist) {
                        if (!edges.contains(minPoint) && edges.contains(pt)) {
                                pt = minPoint;
                        }
                        numAtDist++;
                }
        }
        if (edges.contains(minPoint)) {
                Point pot = nearestPoint(minPoint, realPts, realPts, edges);
                if (pot != null) {
                        pot.setTrue();
                }
                return null;
        } else if (numAtDist > 1) {
                return null;
        } else {
                return minPoint;
        }
}


private static boolean isWithinShape(Point p, ArrayList<Point> pts) {
        for (Point p1: pts) {
                for (Point p2: pts) {
                        if (!p2.equals(p1)) {
                                for (Point p3: pts) {
                                        if (!p3.equals(p1) && !p3.equals(p2) && p.inTriangle(p1, p2, p3)) {
                                                return true;
                                        }
                                }
                        }
                }
        }
        return false;
}

private static boolean isPointInPolygon( Point p, ArrayList<Point> polygon) {
        int minX = polygon.get(0).getX();
        int maxX = polygon.get(0).getX();
        int minY = polygon.get(0).getY();
        int maxY = polygon.get(0).getY();
        for (int i = 1; i < polygon.size(); i++) {
                Point q = polygon.get(i);
                minX = Math.min(q.getX(), minX);
                maxX = Math.max(q.getX(), maxX);
                minY = Math.min(q.getY(), minY);
                maxY = Math.max(q.getY(), maxY);
        }
        // http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
        boolean inside = false;
        for ( int i = 0, j = polygon.size() - 1; i < polygon.size(); j = i++ ) {
                if (( polygon.get(i).getY() > p.getY()) != (polygon.get(j).getY() > p.getY()) && p.getX() < ( polygon.get(j).getX() - polygon.get(i).getX()) * (p.getY() - polygon.get(i).getY()) / (polygon.get(j).getY() - polygon.get(i).getY()) + polygon.get(i).getX()) {
                        inside = !inside;
                }
        }
        return inside;
}

private static int processFile(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                ArrayList<Point> pts = new ArrayList<Point>();
                ArrayList<Point> outsidePts = new ArrayList<Point>();
                int minX = Integer.MAX_VALUE;
                int maxX = Integer.MIN_VALUE;
                int minY = Integer.MAX_VALUE;
                int maxY = Integer.MIN_VALUE;
                HashMap<Point, Integer> closestTo = new HashMap<Point, Integer>();
                for(String line; (line = br.readLine()) != null;) {
                        String numbers[];
                        numbers = line.split("\\s+");
                        int x = Integer.parseInt(numbers[0].substring(0, numbers[0].length()-1));
                        int y = Integer.parseInt(numbers[1]);
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
                        outsidePts.add(p);
                        closestTo.put(p, 0);
                }
                HashSet<Point> edges = new HashSet<Point>();
                for (int i = minX; i <= maxX; i++) {
                        Point p0 = new Point(i, maxY);
                        Point p1 = new Point(i, maxY);
                        outsidePts.add(p0);
                        outsidePts.add(p1);
                        edges.add(p0);
                        edges.add(p1);
                }
                for (int i = minY; i <= maxY; i++) {
                        Point p0 = new Point(minX, i);
                        Point p1 = new Point(maxX, i);
                        outsidePts.add(p0);
                        outsidePts.add(p1);
                        edges.add(p0);
                        edges.add(p1);
                }
                for (int i = minX; i <= maxX; i++) {
                        for (int j = minY; j <= maxY; j++) {
                                Point p = new Point(i, j);
                                Point closest = nearestPoint(p, outsidePts, pts, edges);
                                if (closest != null) {
                                        closestTo.put(closest, closestTo.get(closest) + 1);
                                }
                        }
                }
                int maxClosest = Integer.MIN_VALUE;
                // System.out.println("minX: " + minX + " maxX: " + maxX);
                // System.out.println("minY: " + minY + " maxY: " + maxY);
                for (Point pt : closestTo.keySet()) {
                        // System.out.println(tempId + " " + closestTo.get(tempId));
                        if (!pt.getBoolean() && maxClosest < closestTo.get(pt)) {
                                maxClosest = closestTo.get(pt);
                        }
                }
                System.out.println(closestTo);
                System.out.println(closestTo.containsValue(3223));
                System.out.println((maxX-minX) + " " + (maxY -minY));
                return maxClosest;
        } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
        }
        return -1;
}



public static void main(String[] args) {
        System.out.println(processFile(args[0]));
}
}
