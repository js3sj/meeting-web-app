package com.assessment.meeting.utils;

public class Errors {
    public final static String FORM_ERROR_START_TIME_IS_BIGGER =
            "End date and time must be bigger than start date and time.";
    public final static String FORM_ERROR_MEETING_TITLE_EXISTS =
            "Meeting with this name already exist.";
    public final static String FORM_ERROR_PERSON_EXISTS =
            "Person is already in the meeting, can not add it twice.";
    public final static String FORM_ERROR_PERSON_AUTH =
            "Entered Person is note responsible for this meeting.";
}
