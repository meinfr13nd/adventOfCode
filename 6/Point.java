public class Point /* implements Comparable<Point>*/ {
private int x;
private int y;
private boolean isEdge = false;
public Point(int x, int y) {
        this.x = x;
        this.y = y;
}
public int getY() {
        return this.y;
}
public int getX() {
        return this.x;
}
public boolean equals(Point p) {
        return this.getX() == p.getX() && this.getY() == p.getY();
}

public int distance(Point p) {
        return Math.abs(this.getX() - p.getX()) + Math.abs(this.getY() - p.getY());
}

private int sign(Point p2, Point p3) {
        return (this.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (this.getY() - p3.getY());
}

public boolean inTriangle(Point v1, Point v2, Point v3) {
        int d1 = this.sign(v1, v2);
        int d2 = this.sign(v2, v3);
        int d3 = this.sign(v3, v1);

        boolean has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        boolean has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
}
public String toString() {
        return "*" + this.x + ", " + this.y + ", " + this.isEdge + "*";
}

public void setTrue() {
        this.isEdge = true;
}

public boolean getBoolean() {
        return this.isEdge;
}
}
