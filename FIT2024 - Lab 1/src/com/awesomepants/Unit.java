package com.awesomepants;

import java.util.HashMap;

/**
 * Created by eastd on 8/03/2016.
 */
public class Unit {
    private String code;
    private String name;
    private HashMap<Integer, Student> enrolledStudents = new HashMap<>();
    public Unit(String code, String name)
    {
        this.code = code;
        this.name = name;
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

    public String getUnitDescription()
    {
        return code + " " + name;
    }
}
