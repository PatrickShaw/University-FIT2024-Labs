package com.awesomepants;

import java.util.*;

/**
 * Created by eastd on 8/03/2016.
 */
public class Unit {
    private String code;
    private String name;
    private HashMap<Integer, Student> enrolledStudents = new HashMap<>();
    private AssessmentScheme assessmentScheme;
    public Unit(String code, String name, AssessmentScheme assessmentScheme)
    {
        this.code = code;
        this.name = name;
        this.assessmentScheme = assessmentScheme;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public String description()
    {
        StringJoiner newLineJoiner = new StringJoiner("\n");
        newLineJoiner.add(String.join(" ", code, name));
        newLineJoiner.add("Enrolled Students: ");
        Iterator<Map.Entry<Integer, Student>> i = enrolledStudents.entrySet().iterator();
        while(i.hasNext())
        {
            newLineJoiner.add(i.next().getValue().getDescription());
        }
        newLineJoiner.add("Assessments");
        Iterator<Assessment> j = assessmentScheme.getAssessments().iterator();
        while(i.hasNext())
        {
            newLineJoiner.add(j.next().description());
        }
        return newLineJoiner.toString();
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
