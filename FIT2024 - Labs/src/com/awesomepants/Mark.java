package com.awesomepants;

import java.text.MessageFormat;

/**
 * Created by eastd on 5/04/2016.
 */
public class Mark {
    private double mark;
    private String comment;
    public Mark(int mark, String comment) {
        setMark(mark);
        this.comment = comment;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment == null || comment.equals("") ? "-" : comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String description() {
        return MessageFormat.format("Mark {0}, Comment {1}", Math.round(mark), getComment());
    }
}
