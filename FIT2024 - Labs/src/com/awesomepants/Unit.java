package com.awesomepants;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.w3c.dom.ranges.RangeException;

import java.text.MessageFormat;
import java.util.*;

/**
 * Created by eastd on 8/03/2016.
 */
public class Unit {
    private String code;
    private String name;
    private Staff chiefExaminer;
    private HashMap<Integer, Student> enrolledStudents = new HashMap<>();
    private AssessmentScheme assessmentScheme;
    public Unit(String code, String name, AssessmentScheme assessmentScheme, Staff chiefExaminer) throws Exception
    {
        setChiefExaminer(chiefExaminer);
        setCode(code);
        this.name = name;
        this.assessmentScheme = assessmentScheme;
    }

    public Staff getChiefExaminer() {
        return chiefExaminer;
    }

    public void setChiefExaminer(Staff chiefExaminer) {
        this.chiefExaminer = chiefExaminer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws Exception {
        if(code == null)
            throw new NullPointerException("Codes cannot be null");
        if(code.length() != 7)
            throw new RangeException((short)123, "Codes must be of length 7");
        for(int i = 0 ; i<=2; i++)
            if(!Character.isAlphabetic(code.charAt(i)))
                throw new IllegalArgumentException("Codes must be of format: 3 letters followed by 4 numbers");
        for(int i = 3 ; i<=6; i++)
            if(!Character.isDigit(code.charAt(i)))
                throw new IllegalArgumentException("Codes must be of format: 3 letters followed by 4 numbers");
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssessmentScheme getAssessmentScheme()
    {
        return assessmentScheme;
    }

    public void setAssessmentScheme(AssessmentScheme assessmentScheme)
    {
        this.assessmentScheme = assessmentScheme;
    }

    public boolean completedAllAssessments(Student student)
    {
        Student enrolledStudent = enrolledStudents.get(student.getStudentID());
        if(enrolledStudent == null)
            return false;
        return assessmentScheme.isFullyMark(enrolledStudent.getStudentID());
    }

    public String description()
    {
        StringJoiner newLineJoiner = new StringJoiner("\n");
        newLineJoiner.add(String.join(" ", code, name));
        newLineJoiner.add("Chief examiner: ");
        newLineJoiner.add(chiefExaminer.getDescription());
        newLineJoiner.add("Enrolled Students: ");
        Iterator<Map.Entry<Integer, Student>> i = enrolledStudents.entrySet().iterator();
        while(i.hasNext())
        {
            Student student = i.next().getValue();
            newLineJoiner.add(MessageFormat.format("{0},\nCompleted assessments: {1}",student.getDescription(), completedAllAssessments(student)? "Yes, " + Double.toString(Math.round(getAssessmentScheme().getOverallMark(student.getStudentID()))) + "%" : "No"));
        }
        newLineJoiner.add(assessmentScheme.toString());
        return newLineJoiner.toString();
    }

    public void putAssessmentMark(Student student,Mark mark, int assessmentIndex)
    {
        if(assessmentScheme.getAssessment(assessmentIndex) == null)
            throw new NullPointerException("No such assessment found.");
        // Assumes you can ammend marks after the student is no longer enrolled
        if(!isEnrolled(student) || assessmentScheme.getAssessment(assessmentIndex).hasMark(student))
        {
            throw new ValueException("Student needs to be enrolled in the unit or they need to have a mark for the assessment already.");
        }
        assessmentScheme.getAssessment(assessmentIndex).putMark(student, mark);
    }

    public void enrolStudent(Student student)
    {
        enrolledStudents.put(student.getStudentID(),student);
    }
    public ArrayList<Student> getEnrolledStudents()
    {
        ArrayList<Student> enrolledStudentList = new ArrayList<>();
        Iterator<Map.Entry<Integer, Student>> i = enrolledStudents.entrySet().iterator();
        while(i.hasNext())
        {
            enrolledStudentList.add(i.next().getValue());
        }
        return enrolledStudentList;
    }
    public boolean isEnrolled(Student student)
    {
        return enrolledStudents.get(student.getStudentID()) != null;
    }
    public boolean unenrollStudent(Student student)
    {
        try{
            enrolledStudents.remove(student.getStudentID());
        }
        catch(Exception ex)
        {
            return false;
        }
        return true;
    }
    @Override
    public String toString()
    {
        return description();
    }
}
