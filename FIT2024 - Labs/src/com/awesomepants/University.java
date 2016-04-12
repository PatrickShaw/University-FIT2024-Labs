package com.awesomepants;

import com.sun.corba.se.impl.io.TypeMismatchException;
import org.w3c.dom.ranges.RangeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class University {
    private class DuplicationException extends Exception{
        public DuplicationException(String message){super(message);}
    }
    private ArrayList<Unit> units = new ArrayList<>();
    private HashMap<Integer, Student> students = new HashMap<>();
    /**
        Method for testing this class
     */
    private void addUnit(Unit unit)
    {
        units.add(unit);
    }
    public void run()
    {
        System.out.println("Welcome to Java University");
        System.out.println();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        initialiseTestUniversity();
        inputAddUnits(in);
        inputAddStudent(in);
        displayUnits();
        testEnrollement();
        System.out.println();
        System.out.println("Thank you for using Java University");
    }
    private void initialiseTestUniversity()
    {
        createTestStudents();
        createTestUnits();
    }
    private void createTestUnits() {
        try {
            Unit unit = new Unit("FIT2024", "Software Engineering Practice", new AssessmentScheme(
                    new Assignment(40, "Assignment 1"),
                    new Exam(60, 260)
            ), getStaff(4));

            try {

                new Unit("FIT2024", "Software Engineering Practice", new AssessmentScheme(
                        new Assignment(40, "Assignment 1"),
                        new Exam(60, -1)
                ), getStaff(5));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {

                new Unit("FIT2024", "Software Engineering Practice", new AssessmentScheme(
                        new Assignment(101, "Assignment 1")
                ),
                        getStaff(4));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {

                new Unit("FIT2024", "Software Engineering Practice",
                        new AssessmentScheme(
                                new Assignment(40, "Assignment 1")
                        ),
                        getStaff(5)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                new Unit("FIT2024", "Software Engineering Practice", new AssessmentScheme(
                        new Assignment(40, "Assignment 1"),
                        new Assignment(40, "Assignment 2"),
                        new Assignment(40, "Assignment 3")
                ),
                        getStaff(4));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            unit.enrolStudent(getStudent(26898187));
            unit.enrolStudent(getStudent(12312314));
            unit.unenrollStudent(getStudent(21231333));
            try {
                unit.putAssessmentMark(getStudent(26898187), new Mark(1, "Wow.. you earned 1 mark"), 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            units.add(unit);

            unit = new Unit("FIT1008", "Introduction to Computer Science", new AssessmentScheme(
                    new Assignment(40, "Assignment 1"),
                    new Exam(60, 160)
            ),
                    getStaff(4));
            unit.enrolStudent(getStudent(0));
            try {
                unit.putAssessmentMark(getStudent(26898187), new Mark(1, "WTF?"), 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            units.add(unit);

            unit = new Unit("FIT1010", "Introduction to Software Engineering", new AssessmentScheme(
                    new Assignment(40, "Assignment 1"),
                    new Exam(60, 60)
            ),
                    getStaff(3));
            unit.enrolStudent(getStudent(1));
            unit.enrolStudent(getStudent(2));
            try {
                unit.putAssessmentMark(getStudent(1), new Mark(0, "Did terribly"), 0);
                unit.putAssessmentMark(getStudent(1), new Mark(60, "Did terribly"), 1);
                unit.putAssessmentMark(getStudent(0), new Mark(1000, "WTF?"), 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            units.add(unit);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void testEnrollement() {
        try {
            Unit testUnit = new Unit("FIT1337", "Enrolment test unit", new AssessmentScheme(
                    new Assignment(40, "Assignment 1"),
                    new Exam(60, 180)
            ),
                    getStaff(4));
            Student testStudent = new Student(1231030, "Test", "Student");

            Tester.assertEqual(testUnit.isEnrolled(testStudent), false);

            testUnit.enrolStudent(testStudent);
            System.out.println(testStudent.getFirstName() + " was added to the test unit");

            Tester.assertEqual(testUnit.isEnrolled(testStudent), true);

            System.out.println(testStudent.getFirstName() + " was added to the test unit");
            testUnit.unenrollStudent(testStudent);
            Tester.assertEqual(testUnit.isEnrolled(testStudent), false);

            System.out.println();
            System.out.println(testUnit.toString());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void addStudent(Student student) throws DuplicationException
    {
        if(students.get(student.getStudentID()) != null){ throw new DuplicationException("Student ID " + Integer.toString(student.getStudentID()) + " already exists within the database."); }
        students.put(student.getStudentID(), student);
    }
    public Staff getStaff(int staffId)
    {
        if(!(students.get(staffId) instanceof Staff))
        {
            throw new TypeMismatchException("Not a staff member");
        }
        return (Staff)students.get(staffId);
    }
    private Student getStudent(int studentID)
    {
        return students.get(studentID);
    }
    private void createTestStudents() {
        try {
            addStudent(new Student(26898187, "Patrick", "Shaw"));
            Student example = new Student(12312314, "Sharon", "Tse");
            example.setFirstName("George");
            example.setLastName("Rawr");
            addStudent(example);
            addStudent(new Student(0, "John", "Smith"));
            addStudent(new Student(1, "Awesome", "Person"));
            addStudent(new Student(2));
            try {
                addStaff(new Staff(3, "Chief", "Lastname", 120, "0434969841"));
                addStaff(new Staff(4, "Chief2", "Lastname", 121, "0434969812"));
                addStaff(new Staff(5, "Chief32", "Lastname", 122, "0434969842"));
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch(DuplicationException ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    private void addStaff(Staff staffMember) throws DuplicationException
    {
        addStudent(staffMember);
    }
    private void displayUnits()
    {
        StringJoiner newLineJoiner = new StringJoiner("\n\n");
        for(Unit unit : units)
        {
            newLineJoiner.add(unit.description());
        }
        System.out.println(newLineJoiner.toString());
    }
    private void displayStudents()
    {
        Iterator<Map.Entry<Integer, Student>> i = students.entrySet().iterator();
        while(i.hasNext())
        {
            System.out.println(i.next().getValue().getDescription());
        }
    }

    private void displayMenu(String[][] options)
    {
        for(int i = 0; i < options.length; i++)
            System.out.println(options[i][0] + " = " + options[i][1]);
    }
    private void inputAddUnits(BufferedReader in)
    {
        boolean isAddingMoreUnits = true;
        while(isAddingMoreUnits)
        {
            String unitCode = readNonNullString(in, "Please enter the unit code");
            String unitName = readNonNullString(in, "Please enter the unit name");
            try {
                addUnit(new Unit(unitCode, unitName, new AssessmentScheme(new Exam(100, 60)), getStaff(4)));
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            String addMoreUnitsInput = readExplicitOptionsString(in,"Would you like to add more units? (y/n)", new String[]{"y", "n"});
            if(addMoreUnitsInput.equals("n"))
            {
                isAddingMoreUnits = false;
            }
        }
    }
    private void inputAddStudent(BufferedReader in)
    {
        boolean isAddingStudents = true;
        while(isAddingStudents)
        {
            int studentId= readInt(in, "Please enter the student's ID.");
            String studentFirstName = readNonNullString(in, "Please enter the student's first name");
            String studentLastName = readNonNullString(in, "Please enter teh student's last name");
            try {
                addStudent(new Student(studentId, studentFirstName, studentLastName));
            }
            catch(DuplicationException ex)
            {
                //ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
            String inputAddMoreStudents = readExplicitOptionsString(in, "Do you want to add another student? (y/n)", new String[]{"y","n"});
            if(inputAddMoreStudents.equals("n"))
            {
                isAddingStudents = false;
            }
        }
    }

    private Integer readInt(BufferedReader in, String question) {
        while (true) {
            try
            {
                return Integer.parseInt(readNonNullString(in, question));
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Please enter a number.");
            }
        }
    }
    private String readExplicitOptionsString(BufferedReader in,String question, String options[])
    {
        boolean invalidOption = true;
        String input = null;
        while(invalidOption)
        {
            input = readNonNullString(in, question).toLowerCase();
            for(String option: options)
            {
                if(input.equals(option.toLowerCase()))
                {
                    invalidOption = false;
                    break;
                }
            }
            if(invalidOption)
            {
                System.out.println( "You may only use: "+ String.join(", ", options) + " as options");
            }
        }
        return input;
    }
    private String readNonNullString(BufferedReader in, String question)
    {
        String input = null;
        while(input == null)
        {
            input = readString(in, question);
        }
        return input;
    }
    private String readString(BufferedReader in, String question)
    {
        System.out.println(question);
        return readString(in);
    }
    private String readString(BufferedReader in)
    {
        String s = null;
        try
        {
            s = in.readLine();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return s;
    }
}
