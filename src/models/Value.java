package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Value {

    private final List<String> inputPatterns;
    private final boolean selectionType;

    public Value(String pattern, boolean selectionType){
        this.inputPatterns = new ArrayList<>();
        this.selectionType = selectionType;
        inputPatterns.add(pattern);
    }

    public Value(List<String> inputPatterns, boolean selectionType){
        this.inputPatterns = inputPatterns;
        this.selectionType = selectionType;
    }

    public List<String> getInputPatterns() {
        return inputPatterns;
    }

    public boolean getSelectionType() {
        return selectionType;
    }
}
