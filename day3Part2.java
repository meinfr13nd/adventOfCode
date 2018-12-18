import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.HashMap;

public class day3Part2 extends AdventOfCode {
private HashMap<String, HashSet<String> > positionToClaims = new HashMap<String, HashSet<String> >();
private HashSet<String> elves = new HashSet<String>();

public day3Part2(String fileName) {
        super(fileName);
}

public String getAnswer() {
        HashSet<String> eliminated = new HashSet<String>();
        eliminateOverlap(eliminated);
        for (String elf: elves) {
                if (!eliminated.contains(elf)) {
                        return elf.substring(1);
                }
        }
        return "unable to return";
}

private void eliminateOverlap(HashSet<String> eliminated) {
        for (String position: positionToClaims.keySet()) {
                if (positionToClaims.get(position).size() != 1 ) {
                        for(String name: positionToClaims.get(position)) {
                                eliminated.add(name);
                        }
                }
        }
}

protected Object processLine(String line) {
        String[] splitOnSpaces = line.split("\\s+");
        String[] splitOnCommas = splitOnSpaces[2].split(",");
        String[] splitOnXs = splitOnSpaces[3].split("x");
        splitOnCommas[1] = splitOnCommas[1].substring(0, splitOnCommas[1].length()-1);
        int leftOff = Integer.parseInt(splitOnCommas[0]);
        int upOff = Integer.parseInt(splitOnCommas[1]);
        int width = Integer.parseInt(splitOnXs[0]);
        int height = Integer.parseInt(splitOnXs[1]);
        String elf = splitOnSpaces[0];
        elves.add(elf);
        for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                        String position = (leftOff + i) + " " + (upOff + j);
                        if (!positionToClaims.containsKey(position)) {
                                HashSet<String> elves = new HashSet<String>();
                                elves.add(elf);
                                positionToClaims.put(position, elves);
                        } else {
                                positionToClaims.get(position).add(elf);
                        }
                }

        }
        return "";
}
}
