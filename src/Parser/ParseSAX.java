package Parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ParseSAX {
    private static List<Train> trains = new ArrayList<>();

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        parseSAX();
    }

    private static void parseSAX() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File("trains.xml"), handler);
        for (Train t : trains) {
            System.out.println(String.format("From: %s, to: %s, depDate: %s, depTime: %s", t.getFrom(), t.getTo(),
                    t.getDepDate(), t.getDepTime()));
        }
    }

    private static class XMLHandler extends DefaultHandler {
        private String from;
        private String to;
        private LocalDate depDate;
        private LocalTime depTime;
        private String lastElement;

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())
                    &&(depDate != null) && (depTime != null)) {
                trains.add(new Train(from, to, depDate, depTime));
                from = null;
                to = null;
                depDate = null;
                depTime = null;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String info = new String(ch, start, length);
            info = info.replace("\n", "").trim();
            if (!info.isEmpty()) {
                if (lastElement.equalsIgnoreCase("from")) {
                    from = info;
                }
                if (lastElement.equalsIgnoreCase("to")) {
                    to = info;
                }
                if (lastElement.equalsIgnoreCase("date")) {
                    depDate = LocalDate.parse(info);
                }
                if (lastElement.equalsIgnoreCase("departure")) {
                    depTime = LocalTime.parse(info);
                }
            }
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElement = qName;
        }
    }
}