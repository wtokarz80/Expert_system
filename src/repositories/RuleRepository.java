package repositories;

import models.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuleRepository {
    private final Iterator<Question> questionIterator = new QuestionIterator();
    private final List<Question> questions = new ArrayList<>();


    public void addQuestion(Question question) {
        questions.add(question);
    }

    private class QuestionIterator implements Iterator<Question> {
        int index;

        @Override
        public boolean hasNext() {
            return index < questions.size();
        }

        @Override
        public Question next() {
            if (this.hasNext()) {
                return questions.get(index++);
            }
            return null;
        }
    }

    public Iterator<Question> getQuestionIterator() {
        return questionIterator;
    }

}



