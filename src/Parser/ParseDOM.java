package Parser;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ParseDOM {
    private static List<Train> trains = new ArrayList<>();
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        parseDOM();
    }

    private static void parseDOM() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("trains_formatted.xml"));
        NodeList trainsElements = document.getDocumentElement().getElementsByTagName("train");
        for(int i = 0; i<trainsElements.getLength();i++){
            Node train = trainsElements.item(i);
            NamedNodeMap attributes = train.getAttributes();
            trains.add(new Train(attributes.getNamedItem("from").getNodeValue(),attributes.getNamedItem("to").getNodeValue(),
                    LocalDate.parse(attributes.getNamedItem("date").getNodeValue().toString()),
                    LocalTime.parse(attributes.getNamedItem("departure").getNodeValue())));
        }
        for (Train t : trains) {
            System.out.println(String.format("From: %s, to: %s, depDate: %s, depTime: %s", t.getFrom(), t.getTo(),
                    t.getDepDate(), t.getDepTime()));
        }
    }
}