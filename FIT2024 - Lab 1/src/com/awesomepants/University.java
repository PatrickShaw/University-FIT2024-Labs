package com.awesomepants;

import java.util.ArrayList;
public class University {
    private ArrayList<Unit> units = new ArrayList<>();
    /**
        Method for testing this class
     */
    public void run()
    {
        System.out.println("Welcome to Java University");
        System.out.println();
        createUnits();
        displayUnits();
        System.out.println();
        System.out.println("Thank you for using Java University");
    }
    private void createUnits() {
        units.add(new Unit("FIT2024", "Software Engineering Practice"));
        units.add(new Unit("FIT1008", "Introduction to Computer Science"));
        units.add(new Unit("FIT1010", "Introduction to Software Engineering"));
    }
    private void displayUnits()
    {
        for(Unit unit : units)
        {
            System.out.println(unit.getUnitDescription());
        }
    }
}
