package com.awesomepants;

/**
 * Created by eastd on 5/04/2016.
 */
public class Mark {
    private int mark;
    private String comment;
    public Mark(int mark, String comment) {
        this.mark = mark;
        this.comment = comment;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
