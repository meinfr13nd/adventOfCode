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
                Object result = processLine(line);
                if (result != null) {
                        lines.add(result);
                }
        }
        return lines;
}

protected abstract Object processLine(String line);

public abstract String getAnswer(String fileName);

}
