import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class day7Part1 extends AdventOfCode {

HashMap<String, HashSet<String> > stringToAdjacent = new HashMap<String, HashSet<String> >();

public day7Part1(String fileName) {
        super(fileName);
}

protected String getAnswer() {
        drawGraph();
        return getOrder();
}

protected Object processLine(String line) {
        String one = line.charAt(5) + "";
        String two = line.charAt(36) + "";
        String[] letters = {one, two};
        return letters;
}

private void drawGraph() {
        for (int i = 0; i < this.lineOutput.size(); i++) {
                String[] letters = (String[])this.lineOutput.get(i);
                if (stringToAdjacent.containsKey(letters[0])) {
                        stringToAdjacent.get(letters[0]).add(letters[1]);
                } else {
                        HashSet<String> adjacency = new HashSet<String>();
                        adjacency.add(letters[1]);
                        stringToAdjacent.put(letters[0], adjacency);
                }
        }
}

private String getOrder() {
        System.out.println(stringToAdjacent);
        int numMax = 0;
        int max = 0;
        ArrayList<String> possibleNext;
        for (String s: stringToAdjacent.keySet()) {
                System.out.println(stringToAdjacent.get(s));
                if (max < stringToAdjacent.get(s).size()) {
                        max = stringToAdjacent.get(s).size();
                        numMax = 1;
                } else if (max == stringToAdjacent.get(s).size()) {
                        numMax += 1;
                }
        }
        System.out.println(numMax + "  " + max);
        return "";
}

}
