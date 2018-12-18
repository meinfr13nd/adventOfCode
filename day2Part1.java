import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class day2Part1 extends AdventOfCode {
private Integer frequency = 0;
private Integer hasTwo = 0;
private Integer hasThree = 0;

public day2Part1(String fileName) {
        super(fileName);
}

protected String getAnswer() {
        return ((Integer)(hasTwo * hasThree)).toString();
}

protected Object processLine(String line) {
        HashMap<String, Integer> freqs = new HashMap<String, Integer>();
        for (int i = 0; i < line.length(); i++) {
                String ch = String.valueOf(line.charAt(i));
                if (freqs.containsKey(ch)) {
                        freqs.put(ch, freqs.get(ch)+1);
                } else {
                        freqs.put(ch, 1);
                }
        }
        boolean two = false;
        boolean three = false;
        for (String c : freqs.keySet()) {
                if (freqs.get(c) == 2 && !two) {
                        hasTwo += 1;
                        two = true;
                }
                if (freqs.get(c) == 3 && !three) {
                        hasThree += 1;
                        three = true;
                }
                if (three && two) {
                        break;
                }
        }
        return null;
}
}
