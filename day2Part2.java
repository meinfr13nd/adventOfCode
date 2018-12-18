import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.ArrayList;

public class day2Part2 extends AdventOfCode {
private Integer frequency = 0;
private Integer hasTwo = 0;
private Integer hasThree = 0;
private HashSet<String> pos = new HashSet<String>();

public day2Part2(String fileName) {
        super(fileName);
}

protected String getAnswer() {
        return this.lineOutput.get(0).toString();
}

protected Object processLine(String line) {
        for(String str: pos) {
                int diffs = 0;
                for (int i = 0; i < str.length(); i++) {
                        if (str.charAt(i) != line.charAt(i)) {
                                diffs += 1;
                        }
                        if (diffs > 1) {
                                break;
                        }
                }
                if (diffs == 1) {
                        ArrayList<String> toReturn = new ArrayList<String>();
                        for (int i = 0; i < str.length(); i++) {
                                if (str.charAt(i) == line.charAt(i)) {
                                        toReturn.add(String.valueOf(str.charAt(i)));
                                }
                        }
                        return String.join("", toReturn);
                }
        }
        pos.add(line);
        return null;
}

}
