package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.lang.reflect.Type;
import java.util.ArrayList;
@JsonIgnoreProperties(value = {"parent"})
@XmlAccessorType(XmlAccessType.FIELD)
public  class Node {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "type")
    private Types type;
    @XmlElement(name = "priority")
    public int priority;
    @XmlTransient
    private Node parent;
    @XmlElement(name = "children")
    private ArrayList<Node> children;

    public Node(String name, Types type, int priority, Node parent) {
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.parent = parent;
        children = new ArrayList<>();
    }

    public Node() {
    }


    public void addChild(Node child){
        children.add(child);

    }
    public ArrayList<Node> getChildren(){
        return children;
    }

    @Override
    public String toString() {
        return "model.Node{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", priority=" + priority +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public static Builder newBuilder() {
        return (new Node()).new Builder();
    }
    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }
    public  class  Builder{

        public Builder() {
        }
        public Builder setName(String name){
            Node.this.name = name;
            return this;
        }
        public Builder setPriority(int priority){
            Node.this.priority = priority;
            return this;
        }
        public Builder setType(Types type){
            Node.this.type = type;
            return this;
        }
        public Builder setParent(Node parent){
            Node.this.parent = parent;
            return this;
        }
        public Node build(){
            Node.this.children = new ArrayList<>();
            return Node.this;
        }

    }
}
