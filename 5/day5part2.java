import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;


public class day5part2 {

private static int processFile(String fileName) {
        HashMap<Date, String> timeToMessage = new HashMap<Date, String>();
        HashSet<String> combinations = new HashSet<String>();
        for(char alphabet = 'a'; alphabet <='z'; alphabet++ ) {
                combinations.add(alphabet + "" + Character.toUpperCase(alphabet));
                combinations.add("" + Character.toUpperCase(alphabet) + alphabet);
        }
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = br.readLine();
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
                return min;
        } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
                System.exit(1);
        }
        return -1;
}



public static void main(String[] args) {
        System.out.println(processFile(args[0]));
}
}
