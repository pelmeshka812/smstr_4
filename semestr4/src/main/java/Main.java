
import com.fasterxml.jackson.core.JsonProcessingException;
import model.Node;
import model.Tree;
import model.Types;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Node russia = new Node("Russia", Types.COUNTRY, 1,null);
        Node tatarstan = new Node("Tatarstan", Types.DISTRICT, 1, russia);
        russia.addChild(tatarstan);
        Node kazan = new Node("Kazan", Types.CITY, 2, tatarstan );
        tatarstan.addChild(kazan);
        Node profsoyznaya = new Node("Profsoyznaya", Types.STREET, 1, kazan);
        Node profsHouseNumber = new Node("32", Types.HOUSE, 1, profsoyznaya);
        Node elabuga = new Node("Elabuga", Types.CITY, 1, tatarstan);
        tatarstan.addChild(elabuga);
        Node raduznaya = new Node("Raduznaya", Types.STREET, 1, elabuga);
        elabuga.addChild(raduznaya);
        Node radHouseNumber = new Node("25", Types.HOUSE, 1, raduznaya);
        Tree tree = new Tree(russia);
        Iterator iter = tree.getDFS();
        Node profsHouseNumber1 = Node.newBuilder().setName("56").setType(Types.HOUSE).setPriority(2).setParent(profsoyznaya).build();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }

//        System.out.println(new ObjectMapper().writeValueAsString(tree));
    }
}
