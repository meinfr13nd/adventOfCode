public class Color {
private String id;
private int numColored;
public Color(String id, int numColored) {
        this.id = id;
        this.numColored = numColored;
}

public boolean equals(Color c) {
        return this.id.equals(c.getID());
}

public String getID() {
        return this.id;
}

public void incrementCount() {
        this.numColored += 1;
}

public void decrementCount() {
        this.numColored -= 1;
}

public int getCount() {
        return this.numColored;
}

public void markEdge() {
        this.numColored = Integer.MIN_VALUE/2;
}

public String toString() {
        return this.id + " _____ " + this.numColored + "\n";
}

}
