package com.assessment.meeting.models;

import com.assessment.meeting.utils.MeetingDto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Meeting {

    private List<Person> personList;
    @NotEmpty(message = "Name for the meeting is mandatory")
    private String name;
    private Person responsiblePerson;
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

    public Meeting() {}

    // For writing sample data
    public Meeting(List<Person> personList, String name, String description, Category category, Type type, Calendar startDate, Calendar endDate) {
        this.personList = personList;
        this.name = name;
        this.description = description;
        this.category = category;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Meeting(MeetingDto meetingDto) {
        if (personList == null) {
            this.personList = new ArrayList<>();
            personList.add(new Person(
                    meetingDto.getPersonsFirstName(),
                    meetingDto.getPersonsLastName(),
                    meetingDto.getPersonAddedTime(),
                    true));
        }
        this.name = meetingDto.getName();
        this.description = meetingDto.getDescription();
        this.category = meetingDto.getCategory();
        this.type = meetingDto.getType();
        this.startDate = meetingDto.getStartDate();
        this.endDate = meetingDto.getEndDate();
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> person) {
        this.personList = person;
    }

    public void addPerson(Person person) { this.personList.add(person); };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setStartDate(Date date) {
        this.startDate.setTime(date);
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(Date date) {
        this.endDate.setTime(date);
    }

    @Override
    public String   toString() {
        return "Meeting{" +
                "personList=" + personList +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", startDate=" + startDate.getTime() +
                ", endDate=" + endDate.getTime() +
                '}';
    }
}
