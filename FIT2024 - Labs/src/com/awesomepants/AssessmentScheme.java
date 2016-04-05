package com.awesomepants;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.w3c.dom.ranges.RangeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eastd on 22/03/2016.
 */
public class AssessmentScheme {
    private List<Assessment> assessments;
    public AssessmentScheme(Assessment... assessments)
    {
        setAssessments(Arrays.asList(assessments));
    }
    public AssessmentScheme(List<Assessment> assessments) throws ValueException
    {
        setAssessments(assessments);
    }
    public List<Assessment> getAssessments()
    {
        return assessments;
    }
    public void setAssessments(List<Assessment> assessments) throws ValueException
    {
        int sumOfWeights =0;
        for(Assessment i : assessments)
        {
            sumOfWeights += i.getWeight();
        }
        if(sumOfWeights != 100 && assessments.size() != 0){ throw new ValueException("Assessments must add up to 100, not " + Integer.toString(sumOfWeights)); }
        this.assessments = assessments;
    }
}
