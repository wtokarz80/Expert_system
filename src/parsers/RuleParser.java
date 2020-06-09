package parsers;

import models.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import repositories.RuleRepository;

import java.util.Arrays;
import java.util.List;

public class RuleParser extends XMLParser {

    private final RuleRepository ruleRepository = new RuleRepository();

    public RuleRepository getRuleRepository() {
        return ruleRepository;
    }


    @Override
    public void parse() {
        loadXmlDocument("rules.xml");

        NodeList nodeList = getDocument().getElementsByTagName("Rule");

        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node nNode = nodeList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String id = eElement.getAttribute("id");
                String questionText = eElement.getElementsByTagName("Question").item(0).getTextContent();
                Answer answer = getAnswer(eElement);

                Question question = new Question(id, questionText, answer);
                ruleRepository.addQuestion(question);
            }
        }
    }

    private Answer getAnswer(Element eElement) {
        Answer answer = new Answer();

        NodeList answerList = eElement.getElementsByTagName("Selection");

        for(int temp = 0; temp < answerList.getLength(); temp++){
            Node aNode = answerList.item(temp);
            if(aNode.getNodeType() == Node.ELEMENT_NODE){
                Element aElement = (Element) aNode;
                Element valueNode = (Element) aNode.getChildNodes().item(1);
                boolean answerType = Boolean.parseBoolean(aElement.getAttribute("value"));
                Value value;

                if(valueNode.getNodeName().equalsIgnoreCase("SingleValue")){
                    String pattern = valueNode.getAttribute("value");
                    value = new SingleValue(pattern, answerType);
                } else {
                    List<String> params = Arrays.asList(valueNode.getAttribute("value").split(","));
                    value = new MultipleValue(params, answerType);
                }
                answer.addValue(value);
            }
        }
        return answer;
    }
}
