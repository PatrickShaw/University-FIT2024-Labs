package com.awesomepants;

/**
 * Created by eastd on 22/03/2016.
 */
public  class Tester {
    public static <T> void assertEqual(T value1, T value2)
    {
        if(value1 == value2)
        {
            System.out.println("Assertion success: " + value1.toString() + " == " + value2.toString());
        }
        else
        {
            System.out.println("Assertion failure: " + value1.toString() + " == " + value2.toString());
        }
    }
}
