package com.awesomepants;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.StringJoiner;

/**
 * Created by eastd on 5/04/2016.
 */
public class Staff extends Student {
    private int roomNumber;
    private String phoneNumber;
    public Staff(int staffID, String firstName, String lastName,
                 int roomNumber, String phoneNumber) throws Exception {
        super(staffID, firstName,lastName);
        setRoomNumber(roomNumber);
        setPhoneNumber(phoneNumber);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }
    public void setPhoneNumber(String phoneNumber) throws Exception
    {
        if(phoneNumber.length() != 8 && phoneNumber.length() != 10)
        {
            throw new Exception("Phone numbers must only be 8 or 10 digits long. Not " + Integer.toString(phoneNumber.length()));
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getDescription() {
        String description =  super.getDescription();
        StringJoiner lineJoiner = new StringJoiner("\n");
        lineJoiner.add(description);
        lineJoiner.add("Room number:\t" + Integer.toString(roomNumber));
        lineJoiner.add("Phone number:\t" + phoneNumber);
        return lineJoiner.toString();
    }
}
