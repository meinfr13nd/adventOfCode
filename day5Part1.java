import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;



public class day5part1 {

public static boolean willReact(String str, String str1) {
        char ch = str.charAt(0);
        char ch1 = str1.charAt(0);
        if (Character.toLowerCase(ch) == Character.toLowerCase(ch1)) {
                if ((Character.isUpperCase(ch) && Character.isLowerCase(ch1)) || (Character.isUpperCase(ch1) && Character.isLowerCase(ch))) {
                        return true;
                }
        }
        return false;
}


private static int processFile(String fileName) {
        HashMap<Date, String> timeToMessage = new HashMap<Date, String>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                LinkedList<String> text = new LinkedList<String>();
                for(String line; (line = br.readLine()) != null;) {
                        for (int i = 0; i < line.length(); i++) {
                                text.add(String.valueOf(line.charAt(i)));
                        }
                }
                while (true) {
                        LinkedList<Integer> toRemove = new LinkedList<Integer>();
                        for (int i = 1; i < text.size(); i++) {
                                if (willReact(text.get(i), text.get(i-1))) {
                                        toRemove.add(0, i-1);
                                        toRemove.add(0, i);
                                        i++;
                                }
                        }
                        for (int i = 0; i < toRemove.size(); i++) {
                                text.remove((int)toRemove.get(i));
                        }
                        if (toRemove.size() == 0) {
                                break;
                        }
                        System.out.println(text);
                }
                return text.size();
        } catch (Exception e) {
                System.out.println("error");
                System.out.println(e.getMessage());
                System.exit(1);
        }
        return -1;
}



public static void main(String[] args) {
        System.out.println(processFile(args[0]));
}
}
