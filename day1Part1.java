import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.ArrayList;


public class day1Part1 extends AdventOfCode {
private Integer frequency = 0;
private HashSet<Integer> freqs = new HashSet<Integer>();

public day1Part1(String fileName) {
        super(fileName);
}

public String getAnswer() {
        return this.lineOutput.get(this.lineOutput.size()-1).toString();
}

protected Object processLine(String line) {
        if (line.charAt(0) == '-') {
                frequency -= Integer.parseInt(line.substring(1));
        } else {
                frequency += Integer.parseInt(line.substring(1));
        }
        return frequency;
}
}
