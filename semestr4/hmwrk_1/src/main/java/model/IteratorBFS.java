package model;

import java.util.ArrayDeque;
import java.util.Iterator;


public class IteratorBFS implements Iterator<Node> {
    private Node root;
    private Node current;
    private ArrayDeque<Node> nodeQueue;

    public IteratorBFS(Node root) {
        this.root = root;
        this.current = root;
        this.nodeQueue = new ArrayDeque<>();
        nodeQueue.add(root);
    }

    public Node next() {
        current = nodeQueue.pop();
        if (!current.getChildren().isEmpty()) {
            nodeQueue.addAll(current.getChildren());
        }
        return current;

    }

    public boolean hasNext() {
        return !nodeQueue.isEmpty();
    }


}
