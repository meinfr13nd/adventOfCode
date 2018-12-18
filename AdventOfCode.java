import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.function.Function;

public abstract class AdventOfCode {
protected ArrayList<Object> processFile(String fileName) throws IOException {
        ArrayList<Object> lines = new ArrayList<Object>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        for(String line; (line = br.readLine()) != null;) {
                lines.add(processLine(line));
        }
        return lines;
}

abstract Object processLine(String line);

}
