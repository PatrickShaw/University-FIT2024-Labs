package com.awesomepants;

import org.w3c.dom.ranges.RangeException;

/**
 * Created by eastd on 22/03/2016.
 */
public class Exam extends Assessment {
    private int duration;

    /**
     *
     * @param duration
     * Duration in minutes
     */
    public void setDuration(int duration) {
        if(duration < 0) { throw new RangeException((short)1, "duration = " +Integer.toString(duration)+ ". cannot be <");}
        this.duration = duration;
    }

    /**
     *
     * @param duration
     * Duration in minutes
     * @throws RangeException
     * If duration is less than 0
     */
    public Exam(int weight, int duration) throws RangeException {
        super(weight);
        setDuration(duration);
    }
    public int getDuration() {
        return duration;
    }
    @Override
    public String description() {
        return "Exam: duration " + Integer.toString(getDuration())+", weight "+ Integer.toString(getWeight()) + "%";
    }
}
