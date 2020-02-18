package parsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Tree;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Scanner;

public class XMLSaver {
    static XMLSaver xmlSaver;
    ObjectMapper mapper = new ObjectMapper();

    private XMLSaver() {
    }

    public static XMLSaver getInstance() {
        return xmlSaver == null ? xmlSaver = new XMLSaver() : xmlSaver;
    }
    private static Tree XmlToObject(String string) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Tree.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Tree) jaxbUnmarshaller.unmarshal(new StringReader(string));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    private static String jaxbObjectToXML(Tree tree) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Tree.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(tree, sw);
            return sw.toString();
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void writeInFile(Tree tree) {
        try {
            File file = new File("Tree.xml");
            if (!file.exists())
                file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            String str = jaxbObjectToXML(tree);
            System.out.println(str);
            fileWriter.write(str);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tree readFromFile() {
        try {
            File file = new File("Tree.xml");
            Scanner sc = new Scanner(file);
            StringBuilder stringBuilder = new StringBuilder();
            while (sc.hasNextLine()) {
                stringBuilder.append(sc.nextLine());
            }
            return XmlToObject(stringBuilder.toString());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }



}
