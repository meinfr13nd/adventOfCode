import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class day1Part2 {

private static int processFile(String fileName) {
        int frequency = 0;
        HashSet<Integer> freqs = new HashSet<Integer>();
        freqs.add(0);
        while(true) {
                try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                        for(String line; (line = br.readLine()) != null; ) {
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
                        }
                } catch (Exception e) {
                        return Integer.MAX_VALUE;
                }
        }
}



public static void main(String[] args) {
        System.out.println(processFile(args[0]));
}
}
