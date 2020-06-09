package models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Fact {
    private final String id;
    private final String description;
    private final Map<String, Boolean> factValues = new HashMap<>();

    public Fact(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getIdSet() {
        return factValues.keySet();
    }

    public void setFactValueById(String id, boolean value) {
        factValues.put(id, value);
    }

    public boolean getValueById(String id){
        return factValues.get(id);
    }
}
