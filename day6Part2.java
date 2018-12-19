import java.util.ArrayList;
import java.awt.Point;
import java.util.stream.Stream;

public class day6Part2 extends AdventOfCode {

private int minX = Integer.MAX_VALUE;
private int maxX = Integer.MIN_VALUE;
private int minY = Integer.MAX_VALUE;
private int maxY = Integer.MIN_VALUE;
private int maxDist = 10000;

public day6Part2(String fileName) {
        super(fileName);
}

private int totalDist(int x, int y) {
        int size = 0;
        for (int i = 0; i < this.lineOutput.size(); i++) {
                Point p = (Point)this.lineOutput.get(i);
                int dist = Math.abs(x - p.x) + Math.abs(y - p.y);
                size += dist;
        }
        return size;

}

protected String getAnswer() {
        int area = 0;

        for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                        if (totalDist(x, y) < 10000) {
                                area++;
                        }
                }
        }
        return ((Integer)area).toString();
}

protected Object processLine(String line) {
        String numbers[];
        numbers = line.split(",");
        int x = Integer.parseInt(numbers[0].trim());
        int y = Integer.parseInt(numbers[1].trim());
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
        return p;
}

}
