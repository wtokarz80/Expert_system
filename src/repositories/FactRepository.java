package repositories;

import models.Fact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactRepository {

    private final Iterator<Fact> factIterator = new FactIterator();;
    private final List<Fact> facts = new ArrayList<>();

    public void addFact(Fact fact) {
        facts.add(fact);
    }

    private class FactIterator implements Iterator<Fact> {
        int index;

        @Override
        public boolean hasNext() {
            return index < facts.size();
        }

        @Override
        public Fact next() {
            if (this.hasNext()) {
                return facts.get(index++);
            }
            return null;
        }
    }

    public Iterator<Fact> getFactIterator() {
        return factIterator;
    }
}
