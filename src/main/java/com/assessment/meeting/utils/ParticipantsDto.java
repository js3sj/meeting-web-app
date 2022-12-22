package com.assessment.meeting.utils;

public enum ParticipantsDto {
    PARTICIPANTS("Number of persons: "),
    ONE("CodeMonkey"),
    TWO("Hub"),
    THREE("Short"),
    FIVE("TeamBuilding");
    private final String valueName;
    private ParticipantsDto(String valueName) {
        this.valueName = valueName;
    }
    public String getValueName() {
        return this.valueName;
    }
}
