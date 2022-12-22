package com.assessment.meeting.utils;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Calendar;

public class FilterDto {

    private String description;
    private CategoryDto category;
    private TypeDto type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar endDate;
    private int numberOfPersons;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public TypeDto getType() {
        return type;
    }

    public void setType(TypeDto type) {
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

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public String toString() {
        return "MeetingDto{" +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", startDate=" + startDate.getTime() +
                ", endDate=" + endDate.getTime() +
                ", numberOfPersons=" + numberOfPersons +
                '}';
    }
}
