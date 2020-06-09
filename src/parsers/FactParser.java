package parsers;

import models.Fact;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import repositories.FactRepository;

public class FactParser extends XMLParser{

    private final FactRepository factRepository = new FactRepository();

    public FactRepository getFactRepository() {
        return factRepository;
    }

    @Override
    public void parse() {
        loadXmlDocument("facts.xml");

        NodeList nodeList = getDocument().getElementsByTagName("Fact");

        for (int temp = 0; temp < nodeList.getLength(); temp++){
            Node nNode = nodeList.item(temp);

            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;
                String elementId = eElement.getAttribute("id");
                Element subElement = (Element) eElement.getElementsByTagName("Description").item(0);
                String elementDescription = subElement.getAttribute("value");
                Fact fact = new Fact(elementId, elementDescription);
                setFactValues(fact, eElement);
                factRepository.addFact(fact);
            }
        }
    }

    private void setFactValues(Fact fact, Element eElement) {
        Element vNode = (Element) eElement.getElementsByTagName("Evals").item(0);

        NodeList vList = vNode.getElementsByTagName("Eval");

        for(int temp = 0; temp < vList.getLength(); temp++){
            Element value = (Element) vList.item(temp);
            String id = value.getAttribute("id");
            String subject = value.getTextContent();
            fact.setFactValueById(id, Boolean.parseBoolean(subject));
        }
    }
}
