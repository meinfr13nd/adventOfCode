import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.ArrayList;

public class day3Part2 {

private static String processFile(String fileName) {
        HashSet<String> claims = new HashSet<String>();
        HashSet<String> overlapping = new HashSet<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                for(String line; (line = br.readLine()) != null;) {
                        String[] splitOnSpaces = line.split("\\s+");
                        String[] splitOnCommas = splitOnSpaces[2].split(",");
                        String[] splitOnXs = splitOnSpaces[3].split("x");
                        splitOnCommas[1] = splitOnCommas[1].substring(0, splitOnCommas[1].length()-1);
                        int leftOff = Integer.parseInt(splitOnCommas[0]);
                        int upOff = Integer.parseInt(splitOnCommas[1]);
                        int width = Integer.parseInt(splitOnXs[0]);
                        int height = Integer.parseInt(splitOnXs[1]);
                        for (int i = 0; i < width; i++) {
                                for (int j = 0; j < height; j++) {
                                        String position = (leftOff + i) + " " + (upOff + j);
                                        if (!claims.contains(position)) {
                                                claims.add(position);
                                        } else {
                                                overlapping.add(position);
                                        }
                                }
                        }
                }
        } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
        }
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                for(String line; (line = br.readLine()) != null;) {
                        String[] splitOnSpaces = line.split("\\s+");
                        String[] splitOnCommas = splitOnSpaces[2].split(",");
                        String[] splitOnXs = splitOnSpaces[3].split("x");
                        splitOnCommas[1] = splitOnCommas[1].substring(0, splitOnCommas[1].length()-1);
                        int leftOff = Integer.parseInt(splitOnCommas[0]);
                        int upOff = Integer.parseInt(splitOnCommas[1]);
                        int width = Integer.parseInt(splitOnXs[0]);
                        int height = Integer.parseInt(splitOnXs[1]);
                        boolean right = true;
                        for (int i = 0; i < width; i++) {
                                for (int j = 0; j < height; j++) {
                                        String position = (leftOff + i) + " " + (upOff + j);
                                        if (overlapping.contains(position)) {
                                                right = false;
                                        }
                                }
                        }
                        if (right) {
                                return splitOnSpaces[0].substring(1);
                        }
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
