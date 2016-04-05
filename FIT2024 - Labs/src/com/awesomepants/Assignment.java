package com.awesomepants;

import org.w3c.dom.ranges.RangeException;

/**
 * Created by eastd on 22/03/2016.
 */
public class Assignment extends Assessment {
    String title;
    public Assignment(int weight, String title) throws RangeException {
        super(weight);
        setTitle(title);
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    @Override
    public String description() {
        return "Assignment: " + title + ", weight " +  Integer.toString(getWeight()) + "%";
    }
}
