package jcorehw52;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import myclass.Employee;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static jcorehw51.Main.listToJson;
import static jcorehw51.Main.writeString;

public class Main {

    public static List<Employee> parseXML(String fileNameXml) {
        List<Employee> list = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fileNameXml));
            Node root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    NodeList elementList = node.getChildNodes();
                    Element element = (Element) elementList;
                    long id = parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    int age = parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    Employee employee = new Employee(id, firstName, lastName, country, age);
                    list.add(employee);
                }
            }
        } catch (NumberFormatException | DOMException | ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        String fileNameXml = "data.xml";
        List<Employee> list = parseXML(fileNameXml);
        String json = listToJson(list);
        String fileNameJson = "data2.json";
        writeString(json, fileNameJson);
    }
}