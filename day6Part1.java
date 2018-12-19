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
private HashMap<Point, Color> pointToColor = new HashMap<Point, Color>();

protected Object processLine(String line) {
        String numbers[];
        numbers = line.split("\\s+");
        int x = Integer.parseInt(numbers[0].substring(0, numbers[0].length()-1));
        int y = Integer.parseInt(numbers[1]);
        Point p = new Point(x, y);
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
        pointToColor.put(p, new Color(line, 1));
}



}
