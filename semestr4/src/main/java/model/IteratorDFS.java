package model;

import model.Node;

import java.util.ArrayDeque;
import java.util.Iterator;

public class IteratorDFS implements Iterator {
    private Node current;
    private ArrayDeque<Node> nodeQueue;

    public IteratorDFS(Node root) {
        this.nodeQueue = new ArrayDeque<>();
        nodeQueue.push(root);
    }

    public Node next() {
        this.current = nodeQueue.pop();
        if (!current.getChildren().isEmpty()) {
            current.getChildren().forEach(o -> nodeQueue.push(o));
        }
        return current;

    }

    public boolean hasNext() {
        return !nodeQueue.isEmpty();
    }


}
