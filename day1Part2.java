import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.ArrayList;


public class day1Part2 extends AdventOfCode {
private Integer frequency = 0;
private HashSet<Integer> freqs = new HashSet<Integer>();

public day1Part2(String fileName) {
        super(fileName);
}

public String getAnswer() {
        while (this.lineOutput != null && this.lineOutput.size() == 0) {
                try {
                        this.lineOutput = this.processFile(this.inputFile);
                } catch(Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                }
        }
        return this.lineOutput.get(0).toString();
}

protected Object processLine(String line) {
        if (line.charAt(0) == '-') {
                frequency -= Integer.parseInt(line.substring(1));
        } else {
                frequency += Integer.parseInt(line.substring(1));
        }
        int size = freqs.size();
        freqs.add(frequency);
        if (size == freqs.size()) {
                return frequency;
        }
        return null;
}
}
