package com.assessment.meeting.utils;

public enum CategoryDto {
    CATEGORY("Category: "),
    CODE_MONKEY("CodeMonkey"),
    HUB("Hub"),
    SHORT("Short"),
    TEAM_BUILDING("TeamBuilding");
    private final String valueName;
    private CategoryDto(String valueName) {
        this.valueName = valueName;
    }
    public String getValueName() {
        return this.valueName;
    }
}
