import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.ArrayList;

public class day2Part2 {

private static String processFile(String fileName) {
        int frequency = 0;
        int hasTwo = 0;
        int hasThree = 0;
        HashSet<String> pos = new HashSet<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                for(String line; (line = br.readLine()) != null; ) {
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
                }
        } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
        }
        return "";
}



public static void main(String[] args) {
        System.out.println(processFile(args[0]));
}
}
