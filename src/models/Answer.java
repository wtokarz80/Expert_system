package models;

import java.util.ArrayList;
import java.util.List;

public class Answer {

    private final List<Value> values = new ArrayList<>();

    public void addValue(Value value){
        values.add(value);
    }

    public List<Value> getValues() {
        return values;
    }

    public boolean evaluateAnswerByInput(String input){
        boolean evaluatedAnswer = false;
        for (Value value : values){
            if (value.getInputPatterns().contains(input)){
                evaluatedAnswer = value.getSelectionType();
            }
        }
        return evaluatedAnswer;
    }
}
