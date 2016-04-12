package com.awesomepants;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.w3c.dom.ranges.RangeException;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by eastd on 22/03/2016.
 */
public class AssessmentScheme {
    public class NoMarkException extends NullPointerException
    {
        Assessment assessment;
        int studentID;
        public NoMarkException(Assessment assessment, int studentID)
        {
            this.assessment = assessment;
            this.studentID = studentID;
        }
        public Assessment getAssessment()
        {
            return assessment;
        }
        public int getStudentID()
        {
            return studentID;
        }
        @Override
        public String getMessage() {
            return MessageFormat.format("No mark found at for assessment {0} for student ID {1}", assessment, studentID);
        }
    }
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
    public Assessment getAssessment(int assessmentIndex)
    {
        return assessments.get(assessmentIndex);
    }
    private int getUnmarkedIndex(int studentID)
    {
        for(int i = 0 ; i < assessments.size();i++)
        {
            Assessment assessment = assessments.get(i);
            Mark mark = assessment.getMark(studentID);
            if(mark == null)
                return i;
        }
        return -1;
    }
    public  boolean isFullyMark(int studentID)
    {
        return getUnmarkedIndex(studentID) == -1;
    }
    public double getOverallMark(int studentID)
    {
        double totalWeight = 0;
        double totalMark = 0;
        int unmarkedIndex = getUnmarkedIndex(studentID);
        if(unmarkedIndex != -1)
        {
            throw new NoMarkException(assessments.get(unmarkedIndex), studentID);
        }
        for(int i = 0 ; i < assessments.size();i++)
        {
            Assessment assessment = assessments.get(i);
            Mark mark = assessment.getMark(studentID);
            if(mark == null)
                throw new NoMarkException(assessment, studentID);
            totalMark += mark.getMark();
            totalWeight += assessment.getWeight();
        }
        return 100*(totalMark / totalWeight);
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

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("Assessments Scheme");
        for(Assessment assessment : assessments)
            sj.add(assessment.description());
        return sj.toString();
    }
}
