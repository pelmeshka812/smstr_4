package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class Tree {
    @XmlElement(name = "root")
    Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Tree() {
    }

    public Node getRoot(){
        return root;
    }
    public IteratorBFS getBFS(){
        return new IteratorBFS(getRoot());
    }
    public IteratorDFS getDFS() {
        return new IteratorDFS(getRoot());
    }
  public IteratorBFSP getBFSP(){
        return new IteratorBFSP(getRoot());
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
