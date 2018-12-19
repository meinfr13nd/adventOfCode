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

public day6Part1(String fileName) {
        super(fileName);
}

private int minX = Integer.MAX_VALUE;
private int maxX = Integer.MIN_VALUE;
private int minY = Integer.MAX_VALUE;
private int maxY = Integer.MIN_VALUE;
private Color edge = new Color("edge", 0);
private HashMap<Point, Color> pointToColor = new HashMap<Point, Color>();
private ArrayList<Color> colors = new ArrayList<Color>();
private HashSet<Point> processed = new HashSet<Point>();

protected String getAnswer() {
        exploreMap();
        int max = Integer.MIN_VALUE;
        for (Color c: colors) {
                max = Math.max(c.getCount(), max);
        }
        return ((Integer)(max)).toString();
}

protected Object processLine(String line) {
        String numbers[];
        numbers = line.split("\\s+");
        int x = Integer.parseInt(numbers[0].substring(0, numbers[0].length()-1));
        int y = Integer.parseInt(numbers[1]);
        Point p = new Point(x, y);
        processed.add(p);
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
        Color clr = new Color(line, 1);
        colors.add(clr);
        pointToColor.put(p, clr);
        return p;
}

private void exploreMap() {
        while (this.lineOutput.size() != 0) {
                Point current = (Point)this.lineOutput.get(0);
                Color currentClr = pointToColor.get(current);
                this.lineOutput.remove(0);
                for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                                if (!currentClr.equals(edge)) {
                                        Point p = new Point(current.x + i, current.y + j);
                                        if (pointToColor.get(p) != null && !currentClr.equals(pointToColor.get(p))) {
                                                pointToColor.get(p).decrementCount();
                                                pointToColor.put(p, edge);

                                        } else if (p.x <= minX || p.x >= maxX || p.y <= minY || p.y >= maxY) {
                                                currentClr.markEdge();
                                                pointToColor.put(p, currentClr);
                                        } else if (!processed.contains(p)) {
                                                pointToColor.put(p, currentClr);
                                                this.lineOutput.add(p);
                                                currentClr.incrementCount();
                                                processed.add(p);
                                        }
                                }

                        }
                }
        }
}

}
