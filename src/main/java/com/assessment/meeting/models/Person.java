package com.assessment.meeting.models;

import jakarta.validation.constraints.NotEmpty;

import java.util.Calendar;

public class Person {

    @NotEmpty(message = "First name is mandatory")
    private String firstName;
    @NotEmpty(message = "Last name is mandatory")
    private String lastName;
    private Calendar date;
    private boolean responsible = false;

    public Person() {
    }

    public Person(String firstName, String lastName, Calendar date, boolean responsible) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.responsible = responsible;
    }
    public Person(String firstName, String lastName, boolean responsible) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = Calendar.getInstance();
        this.responsible = responsible;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = Calendar.getInstance();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDate() {this.date = Calendar.getInstance(); }

    public boolean isResponsible() {
        return responsible;
    }

    public void setResponsible(boolean responsible) {
        this.responsible = responsible;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date=" + date.getTime() +
                '}';
    }
}
