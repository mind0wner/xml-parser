package Parser;

import org.w3c.dom.*;
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
    //private static List<Train> trains = new ArrayList<>(); -  needed for parseDOM() method
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Trains trains = ParseDOM.readFromFile(new File("trains.xml"));
        System.out.println(trains);
    }

    private static Trains readFromFile(File f) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Trains catalog = new Trains();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(f);
        Element root = document.getDocumentElement();
        NodeList trains = root.getChildNodes();
        for (int i = 0; i< trains.getLength();i++){
            Node node = trains.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element el = (Element) node;
                Train train = getTrainFromNode(el);
                if(train!=null){
                    catalog.addTrain(train);
                }
            }
        }
        return catalog;
    }

    private static Train getTrainFromNode(Element el) {
        if(!el.getTagName().equals("train")){
            return null;
        }
        String from = el.getElementsByTagName("from").item(0).getTextContent();
        String to = el.getElementsByTagName("to").item(0).getTextContent();
        LocalDate date = LocalDate.parse(el.getElementsByTagName("date").item(0).getTextContent());
        LocalTime time = LocalTime.parse(el.getElementsByTagName("departure").item(0).getTextContent());
        return new Train(from,to,date,time);
    }

//    private static void parseDOM() throws ParserConfigurationException, IOException, SAXException {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document document = builder.parse(new File("trains_formatted.xml"));
//        NodeList trainsElements = document.getDocumentElement().getElementsByTagName("train");
//        for(int i = 0; i<trainsElements.getLength();i++){
//            Node train = trainsElements.item(i);
//            NamedNodeMap attributes = train.getAttributes();
//            trains.add(new Train(attributes.getNamedItem("from").getNodeValue(),attributes.getNamedItem("to").getNodeValue(),
//                    LocalDate.parse(attributes.getNamedItem("date").getNodeValue().toString()),
//                    LocalTime.parse(attributes.getNamedItem("departure").getNodeValue())));
//        }
//        for (Train t : trains) {
//            System.out.println(String.format("From: %s, to: %s, depDate: %s, depTime: %s", t.getFrom(), t.getTo(),
//                    t.getDepDate(), t.getDepTime()));
//        }
//    }
}