package interpretator;

import model.Node;
import model.Tree;
import model.Types;
import parsing.JSONSaver;

import java.util.Iterator;

public class Interpreter {
    public static void handle(Tree tree, String query) {
        if (query.startsWith("/children")) {
            getChildren(tree, query);

        } else if (query.startsWith("/add")) {
            add(tree, query);

        } else if (query.startsWith("/delete")) {
            delete(tree, query);

        } else if (query.startsWith("/save")) {
            save(tree);
        }
    }

    private static void save(Tree tree) {
        JSONSaver.getInstance().writeInFile(tree);
    }

    private static void delete(Tree tree, String query) {
        String[] words = query.split(" ");
        Node toDelete = findNode(tree, words[1]);
        if (toDelete != null) {
            toDelete.getParent().getChildren().remove(toDelete);
        } else System.out.println("Not found");
    }

    private static void add(Tree tree, String query) {
        String[] words = query.split(" ");
        Node parent = findNode(tree, words[1]);
        Types type = Types.valueOf(words[3]);
        parent.addChild(Node.newBuilder()
                .setName(words[2])
                .setType(type)
                .setPriority(Integer.parseInt(words[4])).build());
    }


    private static Node recursion(Node node, String[] address, int index) {
        if (address[address.length - 1].equals(node.getName())) {
            return node;
        }
        if (address.length - 1 < index || node.getChildren().isEmpty()) return null;
        for (Node child : node.getChildren()) {
            if (child.getName().equals(address[index])) {
                return recursion(child, address, ++index);
            }
        }
        return null;
    }

    public static Node findNode(Tree tree, String str) {
        String[] address = str.split(",");
        Iterator<Node> iter = tree.getBFS();
        while (iter.hasNext()) {
            Node node = iter.next();
            if (node.getName().equals(address[0])) {
                Node toReturn = recursion(node, address, 1);
                if (toReturn != null) {
                    return toReturn;
                }
            }
        }
        return null;
    }
    private static void getChildren(Tree tree, String query){
        String[] words = query.split(" ");
        Types type = Types.valueOf(words[2]);
        String name = words[3];
        Iterator<Node> iter = tree.getBFS();
        while(iter.hasNext()){
            Node node = iter.next();
            if (node.getType().equals(type)&& name.equals(node.getName())){
                System.out.println(node.getChildren());
            }
        }
    }
}
