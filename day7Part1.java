//largely based on code from harto on this stack overflow question https://stackoverflow.com/questions/2739392/sample-directed-graph-and-topological-sort-code

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class day7Part1 extends AdventOfCode {

private final HashMap<String, Node> keyToNode = new HashMap<String, Node>();

protected String getAnswer() {
        return drawGraph();
}

public day7Part1(String fileName) {
        super(fileName);
}

protected Object processLine(String line) {
        String one = line.charAt(5) + "";
        String two = line.charAt(36) + "";
        Node node1 = new Node(one);
        Node node2 = new Node(two);
        if (keyToNode.containsKey(one)) {
                node1 = keyToNode.get(one);
        } else {
                keyToNode.put(one, node1);
        }
        if (keyToNode.containsKey(two)) {
                node2 = keyToNode.get(two);
        } else {
                keyToNode.put(two, node2);
        }
        node1.addEdge(node2);
        return null;
}

private class Node implements Comparable<Node>{
private final String key;
private final HashSet<Edge> inEdges;
private final HashSet<Edge> outEdges;

private Node(String key) {
        this.key = key;
        inEdges = new HashSet<Edge>();
        outEdges = new HashSet<Edge>();
}

@Override
public int compareTo(Node n) {
        return this.key.compareTo(n.key);
}

public Node addEdge(Node node){
        Edge e = new Edge(this, node);
        outEdges.add(e);
        node.inEdges.add(e);
        return this;
}
public String getKey() {
        return key;
}
}

private class Edge {
private final Node from;
private final Node to;
private Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
}
@Override
public boolean equals(Object obj) {
        Edge e = (Edge)obj;
        return e.from == from && e.to == to;
}
}

private String drawGraph() {
        ArrayList<Node> sortedElements = new ArrayList<Node>(); // Empty list that will contain the sorted elements
        HashSet<Node> noIncomingEdges = new HashSet<Node>(); // Set of all nodes with no incoming edges
        for(String s : keyToNode.keySet()) {
                Node n = keyToNode.get(s);
                if(n.inEdges.size() == 0) {
                        noIncomingEdges.add(n);
                }
        }
        //while S is non-empty do
        while(!noIncomingEdges.isEmpty()) {
                ArrayList<Node> sortedNexts = new ArrayList<Node>(new TreeSet<Node>(noIncomingEdges));
                Node n = sortedNexts.get(0);
                noIncomingEdges.remove(n);
                sortedElements.add(n);
                for(Iterator<Edge> it = n.outEdges.iterator(); it.hasNext();) { //for each node m with an edge e from n to m do
                        //remove edge e from the graph
                        Edge e = it.next(); //remove edge e from the graph
                        Node m = e.to;
                        it.remove();        //Remove edge from n
                        m.inEdges.remove(e);        //Remove edge from m

                        if(m.inEdges.isEmpty()) { //if m has no other incoming edges then insert m into S
                                noIncomingEdges.add(m);
                        }
                }
        }
        String order = "";
        for (Node n : sortedElements) {
                order += n.getKey();
        }
        return order;
}
}
