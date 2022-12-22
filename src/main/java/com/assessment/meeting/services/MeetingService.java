package com.assessment.meeting.services;

import com.assessment.meeting.models.Meeting;
import com.assessment.meeting.models.Person;
import com.assessment.meeting.repositories.MeetingRepository;
import com.assessment.meeting.utils.CategoryDto;
import com.assessment.meeting.utils.MeetingDto;
import com.assessment.meeting.utils.TypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.*;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    public List<Meeting> getAllMeetings() {
        return meetingRepository.read();
    }

    public List<MeetingDto> getAllMeetingsDto() {
        return meetingRepository.read().stream()
                .map(MeetingDto::new)
                .collect(Collectors.toList());
    }

    public boolean isMeetingAlreadyCreated(String name) {
        return meetingRepository.read().stream()
                .map(Meeting::getName)
                .filter(s->name.equals(s))
                .findAny()
                .isPresent();
    }

    public boolean save(MeetingDto meetingDto) {
        return meetingRepository.savePretty(new Meeting(meetingDto));
    }

    public Meeting findByName(String name) {
        if (!name.isEmpty()) {
            return meetingRepository.read().stream()
                    .filter(x -> name.equals(x.getName()))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }

    public boolean isPersonAlreadyInTheMeeting(String meetingName, Person person) {
        return findByName(meetingName).getPersonList().stream()
                .filter(x -> (x.getFirstName().toLowerCase().equals(person.getFirstName().toLowerCase())
                        && x.getLastName().toLowerCase().equals(person.getLastName().toLowerCase())))
                .findFirst()
                .isPresent();
    }

    public boolean addPerson(String meetingName, Person person) {
        var meeting = findByName(meetingName);
        meeting.getPersonList().add(person);
        removeMeetingByName(meetingName);
        return meetingRepository.savePretty(meeting);
    }

    public boolean removePerson(String meetingName, String firstName, String lastName) {
        var meeting = findByName(meetingName);
        var newPersonsList = meeting.getPersonList().stream()
                        .collect(Collectors.filtering(
                                x -> !(x.getFirstName().toLowerCase().equals(firstName.toLowerCase())
                                        && x.getLastName().toLowerCase().equals(lastName.toLowerCase())),
                                Collectors.toList()
                        ));
        meeting.setPersonList(newPersonsList);
        removeMeetingByName(meetingName);
        return meetingRepository.savePretty(meeting);
    }

    public boolean removeMeetingByName(String meetingName) {
        var result = getAllMeetings().stream()
                .collect(Collectors.filtering(
                        x -> !x.getName().equals(meetingName), Collectors.toList()
                ));
        return meetingRepository.savePretty(result);
    }

    public boolean isPersonResponsibleForTheMeeting(String meetingName, Person person) {
        return findByName(meetingName).getPersonList().stream()
                .filter(x -> ((x.getFirstName().toLowerCase().equals(person.getFirstName().toLowerCase()))
                        && (x.getLastName().toLowerCase().equals(person.getLastName().toLowerCase()))
                        && (x.isResponsible())))
                .findFirst()
                .isPresent();
    }

    public List<MeetingDto> filterByDescription(List<MeetingDto> res, String description) {
        return res.stream()
                .collect(Collectors.filtering(
                    x -> x.getDescription().toLowerCase().contains(description.toLowerCase()),
                        Collectors.toList()));
    }

    public List<MeetingDto> filterByCategory(List<MeetingDto> res, CategoryDto category) {
        return res.stream()
                .collect(Collectors.filtering(
                        x -> x.getCategory().getValueName() == category.getValueName(),
                        Collectors.toList()));
    }

    public List<MeetingDto> filterByType(List<MeetingDto> res, TypeDto type) {
        return res.stream()
                .collect(Collectors.filtering(
                        x -> x.getType().getValueName() == type.getValueName(),
                        Collectors.toList()));
    }

    public List<MeetingDto> filterByStartDate(List<MeetingDto> res, Calendar startDate) {
        return res.stream()
                .collect(Collectors.filtering(
                        x -> x.getStartDate().after(startDate),
                        Collectors.toList()));
    }

    public List<MeetingDto> filterByEndDate(List<MeetingDto> res, Calendar endDate) {
        return res.stream()
                .collect(Collectors.filtering(
                        x -> x.getEndDate().before(endDate),
                        Collectors.toList()));
    }

    public List<MeetingDto> filterByNumberOfPersons(List<MeetingDto> res, int numberOfPersons) {
        Predicate<MeetingDto> p;
        if (numberOfPersons < 10)
            p = x -> x.getPersonsList().size() == numberOfPersons;
        else
            p = x -> x.getPersonsList().size() >= numberOfPersons;

        return res.stream()
                .collect(Collectors.filtering(p, Collectors.toList()));
    }
}
