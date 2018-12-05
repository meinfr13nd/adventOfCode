import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class day2Part1 {

private static int processFile(String fileName) {
        int frequency = 0;
        int hasTwo = 0;
        int hasThree = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                for(String line; (line = br.readLine()) != null; ) {
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
                }
        } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
        }
        return hasTwo * hasThree;
}



public static void main(String[] args) {
        System.out.println(processFile(args[0]));
}
}
