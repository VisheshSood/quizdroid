package edu.washington.vsood.quizdroid;

/**
 * Created by vishesh on 2/11/17.
 */

public class Question {

    private String QUESTION;
    private String OPTION1;
    private String OPTION2;
    private String OPTION3;
    private String OPTION4;
    private int CORRECT_ANSWER;

    public Question(String question, String option1, String option2, String option3, String option4, int answer) {
        QUESTION = question;
        OPTION1 = option1;
        OPTION2 = option2;
        OPTION3 = option3;
        OPTION4 = option4;
        CORRECT_ANSWER = answer;
    }

    public String getQuestion() {
        return QUESTION;
    }

    public void setQuestion(String questionText) {
        QUESTION = questionText;
    }

    public String getOption1() {
        return OPTION1;
    }

    public void setOption1(String option1) {
        OPTION1 = option1;
    }

    public String getOption2() {
        return OPTION2;
    }

    public void setOption2(String option2) {
        OPTION2 = option2;
    }

    public String getOption3() {
        return OPTION3;
    }

    public void setOption3(String option3) {
        OPTION3 = option3;
    }

    public String getOption4() {
        return OPTION4;
    }

    public void setOption4(String option4) {
        OPTION4 = option4;
    }

    public int getAnswer() {
        return CORRECT_ANSWER;
    }

    public void setAnswer(int answer) {
        CORRECT_ANSWER = answer;
    }

}