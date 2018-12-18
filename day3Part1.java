import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.ArrayList;

public class day3Part1 extends AdventOfCode {
private int frequency = 0;
private HashSet<String> claims = new HashSet<String>();
private HashSet<String> overlapping = new HashSet<String>();
public day3Part1(String fileName) {
        super(fileName);
}

protected String getAnswer() {
        return ((Integer)overlapping.size()).toString();
}

protected Object processLine(String line) {

        String[] splitOnSpaces = line.split("\\s+");
        String[] splitOnCommas = splitOnSpaces[2].split(",");
        String[] splitOnXs = splitOnSpaces[3].split("x");
        splitOnCommas[1] = splitOnCommas[1].substring(0, splitOnCommas[1].length()-1);
        int leftOff = Integer.parseInt(splitOnCommas[0]);
        int upOff = Integer.parseInt(splitOnCommas[1]);
        int width = Integer.parseInt(splitOnXs[0]);
        int height = Integer.parseInt(splitOnXs[1]);
        for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                        String position = (leftOff + i) + " " + (upOff + j);
                        if (!claims.contains(position)) {
                                claims.add(position);
                        } else {
                                overlapping.add(position);
                        }
                }
        }
        return null;

}
}
