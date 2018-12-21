//largely based on code from harto on this stack overflow question https://stackoverflow.com/questions/2739392/sample-directed-graph-and-topological-sort-code

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class day7Part2 extends AdventOfCode {

private final HashMap<String, Node> keyToNode = new HashMap<String, Node>();

protected String getAnswer() {
        return drawGraph();
}

public day7Part2(String fileName) {
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
        Integer timeTaken = 0;
        int seconds = 0;
        HashMap<Integer, Integer> workerToUntilAble = new HashMap<Integer, Integer>();
        HashMap<Integer, Node> workerToNode = new HashMap<Integer, Node>();
        workerToUntilAble.put(5, 0);
        workerToUntilAble.put(1, 0);
        workerToUntilAble.put(2, 0);
        workerToUntilAble.put(3, 0);
        workerToUntilAble.put(4, 0);
        int asciiCapitalOffset = 64;
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
                System.out.println(workerToUntilAble);
                if (workerToUntilAble.containsValue(0)) {
                        ArrayList<Node> sortedNexts = new ArrayList<Node>(new TreeSet<Node>(noIncomingEdges));
                        for (Node n: sortedNexts) {
                                noIncomingEdges.remove(n);
                                sortedElements.add(n);
                                while (workerToNode.size() == 5) {
                                        seconds += 1;
                                        for (Integer id : workerToUntilAble.keySet()) {
                                                workerToUntilAble.put(id, workerToUntilAble.get(id) - 1);

                                        }
                                        if (workerToUntilAble.containsValue(0)) {
                                                Integer worker = null;
                                                for (Integer w: workerToUntilAble.keySet()) {
                                                        if (workerToUntilAble.get(w) == 0) {
                                                                worker = w;
                                                                break;
                                                        }
                                                }
                                                removeNode(workerToNode.get(worker), noIncomingEdges);
                                                workerToNode.remove(worker);
                                        }
                                }
                                for (Integer id : workerToUntilAble.keySet()) {
                                        if (workerToUntilAble.get(id) == 0) {
                                                workerToUntilAble.put(id, (int)(n.getKey().charAt(0)) - asciiCapitalOffset + 60);
                                                break;
                                        }
                                }
                        }
                }
                seconds += 1;
        }
        timeTaken += seconds;
        String order = "";
        for (Node n : sortedElements) {
                order += n.getKey();
        }
        System.out.println(timeTaken);
        return order;
}

private void removeNode(Node n, HashSet<Node> noIncomingEdges) {
        for(Iterator<Edge> it = n.outEdges.iterator(); it.hasNext();) { //for each node m with an edge e from n to m do
                //remove edge e from the graph
                Edge e = it.next(); //remove edge e from the graph
                Node m = e.to;
                it.remove(); //Remove edge from n
                m.inEdges.remove(e); //Remove edge from m

                if(m.inEdges.isEmpty()) { //if m has no other incoming edges then insert m into S
                        noIncomingEdges.add(m);
                }
        }
}

}
