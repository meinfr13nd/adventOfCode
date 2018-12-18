import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;


public class day5Part1 extends day5 {

public day5Part1(String fileName) {
        super(fileName);
}

protected String getAnswer() {
        return ((Integer)((String)this.lineOutput.get(0)).length()).toString();
}

protected Object processLine(String line) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < line.length(); i++) {
                if (combinations.contains(line.substring(i-1, i+1))) {
                        line = line.substring(0, i-1) + line.substring(i+1);
                        i = Math.max(0, i-2);
                }
        }
        return line;
}
}
