import java.util.HashMap;
import java.util.HashSet;

public abstract class day5 extends AdventOfCode {
protected HashSet<String> combinations = new HashSet<String>();

public day5(String fileName) {
        super(fileName);
        constructCombinations();
}

private void constructCombinations() {
        for(char alphabet = 'a'; alphabet <='z'; alphabet++ ) {
                combinations.add(alphabet + "" + Character.toUpperCase(alphabet));
                combinations.add("" + Character.toUpperCase(alphabet) + alphabet);
        }
}

}
