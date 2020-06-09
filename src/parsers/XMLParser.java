package parsers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public abstract class XMLParser {

    private Document document;

    public void loadXmlDocument(String filePath){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.parse(filePath);
            this.document.getDocumentElement().normalize();

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public Document getDocument(){
        return document;
    }

    public abstract void parse();
}