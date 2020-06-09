package models;


public class Question {
    private final String id;
    private final String question;
    private final Answer answer;

    public Question(String id, String question, Answer answer){
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public boolean getEvaluatedAnswer(String input){
        return answer.evaluateAnswerByInput(input);
    }
}
