package com.awesomepants;

/**
 * Created by eastd on 15/03/2016.
 */
public class Student {
    private final int studentID;
    private String firstName, lastName;

    public Student(int studentID)
    {
        this.studentID = studentID;
    }

    public Student(int studentID, String firstName, String lastName) {
        this.studentID = studentID;
        setFirstName(firstName);
        setLastName(lastName);
    }

    public int getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName == null ? "-" : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName == null ? "-" : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription()
    {
        return String.join(" ",
                Integer.toString(studentID),
                getFirstName(),
                getLastName());
    }
    @Override
    public String toString()
    {
        return getDescription();
    }
}
