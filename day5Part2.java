import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;


public class day5Part2 extends day5 {

public day5Part2(String fileName) {
        super(fileName);
}

protected String getAnswer() {
        return this.lineOutput.get(0).toString();
}

protected Object processLine(String line) {
        int min = Integer.MAX_VALUE;
        for(char alphabet = 'a'; alphabet <='z'; alphabet++ ) {
                String newLine = line;
                newLine = newLine.replace(alphabet + "", "");
                newLine = newLine.replace(Character.toUpperCase(alphabet) + "", "");
                while (true) {
                        int len = newLine.length();
                        for (int i = 1; i < newLine.length(); i++) {
                                if (combinations.contains(newLine.substring(i-1, i+1))) {
                                        newLine = newLine.substring(0, i-1) + newLine.substring(i+1);
                                        i -= 1;
                                }
                        }
                        if (len == newLine.length()) {
                                break;
                        }
                }
                if (newLine.length() < min) {
                        min = newLine.length();
                }
        }
        return (Integer)min;
}
}
