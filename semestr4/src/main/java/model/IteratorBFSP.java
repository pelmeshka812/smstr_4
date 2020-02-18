package model;

import model.Node;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Iterator;

public class IteratorBFSP implements Iterator {
    private Node root;
    private Node current;
    private ArrayDeque<Node> nodeQueue;
    Comparator<Node> comparator = (o1, o2 ) -> o2.priority - o1.priority;

    public IteratorBFSP(Node root) {
        this.root = root;
        this.current = root;
        this.nodeQueue = new ArrayDeque<>();
        nodeQueue.add(root);
    }

    public Node next() {
        current = nodeQueue.pop();
        if (!current.getChildren().isEmpty()) {
            current.getChildren().stream().sorted(comparator).forEachOrdered(o -> nodeQueue.addLast(o));
        }
        return current;

    }

    public boolean hasNext() {
        return !nodeQueue.isEmpty();
    }


}
