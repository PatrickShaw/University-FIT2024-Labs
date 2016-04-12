package com.awesomepants;

import org.w3c.dom.ranges.Range;
import org.w3c.dom.ranges.RangeException;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

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
    public boolean hasMark(Student student)
    {
        return studentMarks.get(student.getStudentID()) != null;
    }
    /**
     * Adds a student's mark to the assessment. If the student already has a mark, it will overwrite their mark.
     */
    public void putMark(Student student, Mark mark) throws RangeException
    {
        // Assuming we can have bonus marks to go above 100
        if(mark.getMark() < 0 || mark.getMark() > weight)
            throw new RangeException((short)1231,"Marks can only be between 0 and " + Integer.toString(weight));
        studentMarks.put(student.getStudentID(), mark);
    }

    /**
     * @return null if no mark
     */
    public Mark getMark(int studentId)
    {
        return studentMarks.get(studentId);
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
    public String description()
    {
        StringJoiner lineJoiner = new StringJoiner("\n");
        for(Map.Entry<Integer, Mark> mark : studentMarks.entrySet()) {
            lineJoiner.add(MessageFormat.format("Student {0}: {1}", Integer.toString(mark.getKey()), mark.getValue().description()));
        }
        return lineJoiner.toString();
    }

}
