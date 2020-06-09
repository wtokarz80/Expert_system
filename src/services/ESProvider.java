package services;

import models.Fact;
import models.Question;
import parsers.FactParser;
import parsers.RuleParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ESProvider {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final FactParser factParser;
    private final RuleParser ruleParser;
    private final Map<String, Boolean> selection = new HashMap<>();

    public ESProvider(FactParser factParser, RuleParser ruleParser){
        this.factParser = factParser;
        this.ruleParser = ruleParser;
    }

    public void collectAnswers() throws IOException {
        ruleParser.parse();
        factParser.parse();
        Iterator<Question> questionIterator = ruleParser.getRuleRepository().getQuestionIterator();

        while (questionIterator.hasNext()){
            Question question = questionIterator.next();
            String questionId = question.getId();
            System.out.println(question.getQuestion());
            boolean userAnswer = getAnswerByQuestion(question);
            selection.put(questionId, userAnswer);
        }
    }


    public boolean getAnswerByQuestion(Question question) throws IOException {
        String userInput = reader.readLine();
        return question.getEvaluatedAnswer(userInput);
    }

    public String evaluate(){
        String result = "";
        Iterator<Fact> factIterator = factParser.getFactRepository().getFactIterator();
        while(factIterator.hasNext()){
            Fact fact = factIterator.next();
            if(checkMatch(fact.getIdSet(), fact)){
                result = fact.getDescription();
            }
        }
        return result;
    }

    private boolean checkMatch(Set<String> idSet, Fact fact) {
        for(String id : idSet){
            if(fact.getValueById(id) != selection.get(id)){
                return false;
            }
        }
        return true;
    }
}
