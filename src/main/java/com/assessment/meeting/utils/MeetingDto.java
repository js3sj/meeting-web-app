package com.assessment.meeting.utils;

import com.assessment.meeting.models.Category;
import com.assessment.meeting.models.Meeting;
import com.assessment.meeting.models.Person;
import com.assessment.meeting.models.Type;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingDto {

    private List<Person> personsList;

    @NotEmpty(message = "Name for the meeting is mandatory")
    private String name;
    @NotEmpty(message = "Persons first name is mandatory")
    private String personsFirstName;
    @NotEmpty(message = "Persons last name is mandatory")
    private String personsLastName;
    private Calendar personAddedTime;
    @NotEmpty(message = "Description is mandatory")
    private String description;
    private Category category;
    private Type type;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Calendar startDate;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Calendar endDate;
    private String persons;

    public MeetingDto() {
        this.personsList = new ArrayList<>();
    }

    public MeetingDto(Meeting meeting) {
        this.name = meeting.getName();
        this.description = meeting.getDescription();
        this.category = meeting.getCategory();
        this.type = meeting.getType();
        this.startDate = meeting.getStartDate();
        this.endDate = meeting.getEndDate();
        this.personsList = meeting.getPersonList();
        this.setPersons();
    }

    public List<Person> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(List<Person> personsList) {
        this.personsList = personsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonsFirstName() {
        return personsFirstName;
    }

    public void setPersonsFirstName(String personsFirstName) {
        this.personsFirstName = personsFirstName;
    }

    public String getPersonsLastName() {
        return personsLastName;
    }

    public void setPersonsLastName(String personsLastName) {
        this.personsLastName = personsLastName;
    }

    public Calendar getPersonAddedTime() {
        return personAddedTime;
    }

    public void setPersonAddedTime() {
        this.personAddedTime = Calendar.getInstance();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public void setPersons() {
        if (personsList.size() > 0){
            this.persons = personsList.stream()
                    .map(e -> e.getFirstName() + " " + e.getLastName())
                    .collect(Collectors.joining(", "));
        }
    }

    public String getPersons() {
        return persons;
    }

    @Override
    public String toString() {
        return "MeetingDto{" +
                "personsList=" + personsList +
                ", name='" + name +
                ", personsFirstName='" + personsFirstName +
                ", personsLastName='" + personsLastName +
                ", personAddedTime=" + personAddedTime.getTime() +
                ", description='" + description +
                ", category=" + category +
                ", type=" + type +
                ", startDate=" + startDate.getTime() +
                ", endDate=" + endDate.getTime() +
                '}';
    }
}
