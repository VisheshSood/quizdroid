package edu.washington.vsood.quizdroid;

import java.util.List;

/**
 * Created by vishesh on 2/11/17.
 */

public class Topic {

    private String TITLE;
    private String SHORT_DESCRIPTION;
    private String LONG_DESCRIPTION;
    private List<Question> QUESTIONS;

    public Topic() {

    }

    public Topic(String title, String short_desc, String long_desc, List<Question> questions) {
        TITLE = title;
        SHORT_DESCRIPTION = short_desc;
        LONG_DESCRIPTION= long_desc;
        QUESTIONS = questions;
    }

    public void setTitle(String title){
        TITLE= title;
    }

    public String getTitle () {
        return TITLE;
    }

    public void setShort(String short_desc) {
        SHORT_DESCRIPTION = short_desc;
    }

    public String getShort() {
        return SHORT_DESCRIPTION;
    }

    public void setLong(String long_desc) {
        LONG_DESCRIPTION= long_desc;
    }

    public String getLong() {
        return LONG_DESCRIPTION;
    }

    public void setQuestions(List<Question> questions) {
        QUESTIONS = questions;
    }

    public List<Question> getQuestions() {
        return QUESTIONS;
    }

}
