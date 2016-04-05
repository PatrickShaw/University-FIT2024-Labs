package com.awesomepants;

import org.w3c.dom.ranges.Range;
import org.w3c.dom.ranges.RangeException;

import java.util.HashMap;

/**
 * Created by eastd on 22/03/2016.
 */
public abstract class Assessment {
    private int weight;
    private HashMap<Integer, Mark> studentMarks = new HashMap<>();
    public Assessment(int weight) throws RangeException
    {
        setWeight(weight);
    }
    public int getWeight()
    {
        return weight;
    }
    private void setWeight(int weight) throws RangeException
    {
        if(weight < 0 || weight > 100) {
            throw new RangeException((short)0,"Weights cannot be below 0 nor above 100");
        }
        this.weight = weight;
    }
    public abstract String description();

}
