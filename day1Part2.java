import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.ArrayList;


public class day1Part2 extends AdventOfCode {
private Integer frequency = 0;
private HashSet<Integer> freqs = new HashSet<Integer>();

public String getAnswer(String fileName) {
        try {
                ArrayList<Object> repeats = processFile(fileName);
                while (repeats.size() == 0) {
                        repeats = processFile(fileName);
                }
                return repeats.get(0).toString();
        } catch(Exception e) {
                System.out.println(e.getMessage());
        }
        return "unable to compute";
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
