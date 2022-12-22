package com.assessment.meeting.controlers;

import com.assessment.meeting.models.Person;
import com.assessment.meeting.services.MeetingService;
import com.assessment.meeting.utils.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@Controller
public class MainControler {

    @Autowired
    private MeetingService meetingService;
    private String meetingName;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/add_meeting")
    public String addMeetingForm(Model model) {
        model.addAttribute("meetingDto", new MeetingDto());
        return "add/add-meeting";
    }

    @PostMapping("/add_meeting")
    public String addMeeting(@ModelAttribute @Valid MeetingDto meetingDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "add/add-meeting";
        }
        if (meetingService.isMeetingAlreadyCreated(meetingDto.getName())) {
            model.addAttribute("error_message_title", Errors.FORM_ERROR_MEETING_TITLE_EXISTS);
            return "add/add-meeting";
        }
        if ((meetingDto.getEndDate().compareTo(meetingDto.getStartDate()))<0){
            model.addAttribute("error_message_start_time", Errors.FORM_ERROR_START_TIME_IS_BIGGER);
            return "add/add-meeting";
        }
        meetingDto.setPersonAddedTime();
        if (meetingService.save(meetingDto)) {
            model.addAttribute("meetingDto", meetingDto);
            return "add/add-meeting-confirm";
        }
        else
            return "add/add-meeting";
    }

    @GetMapping("/add_person")
    public String addPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "add/add-person";
    }

    @PostMapping("/add_person")
    public String addPerson(@ModelAttribute @Valid Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "add/add-person";
        }
        person.setDate();
        if (meetingService.isPersonAlreadyInTheMeeting(meetingName, person)){
            model.addAttribute("error_message_add_person", Errors.FORM_ERROR_PERSON_EXISTS);
            return "add/add-person";
        } else {
            meetingService.addPerson(meetingName, person);
            return "add/add-person-confirm";
        }
    }

    @GetMapping("/delete_meeting/{name}")
    public String deleteMeeting(@PathVariable String name, Model model) {
        meetingName = name;
        model.addAttribute("person", new Person());
        return "remove/authenticate";
    }

    @PostMapping("/delete_meeting")
    public String deleteMeeting(@ModelAttribute @Valid Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "remove/authenticate";
        }

        if (!meetingService.isPersonResponsibleForTheMeeting(meetingName, person)){
            model.addAttribute("error_message_auth_person", Errors.FORM_ERROR_PERSON_AUTH);
            return "remove/authenticate";
        } else {
            meetingService.removeMeetingByName(meetingName);
            model.addAttribute("meetingName", meetingName);
            return "remove/remove-meeting-confirm";
        }
    }

    @GetMapping("/delete_person/{firstName}/{lastName}")
    public String deletePerson(@PathVariable String firstName, @PathVariable String lastName, Model model) {
        meetingService.removePerson(meetingName, firstName, lastName);
        model.addAttribute("meeting", meetingService.findByName(meetingName));
        model.addAttribute("confirm_message_person_removed",
                "Person [ " + firstName + " " + lastName + " ] was successfully deleted");
        return "list/list-persons";
    }

    @GetMapping("/list_persons/{name}")
    public String showAllPersonsForm(@PathVariable(required = false) String name, Model model) {
        if (name != null)
            meetingName = name;
        model.addAttribute("meeting", meetingService.findByName(meetingName));
        return "list/list-persons";
    }

    @GetMapping("/list_persons/")
    public String showAllPersonsForm(Model model) {
        model.addAttribute("meeting", meetingService.findByName(meetingName));
        return "list/list-persons";
    }

    @GetMapping("/list_all_meetings")
    public String showAllMeetings(Model model) {
        var res = meetingService.getAllMeetingsDto();
        res.sort(Comparator.comparing(MeetingDto::getStartDate));
        model.addAttribute("allMeetings", res);
        model.addAttribute("filterDto", new FilterDto());
        return "list/list-all";
    }

    @PostMapping("/filter_by")
    public String filterBy(@ModelAttribute FilterDto filterDto, BindingResult bindingResult, Model model) {
        var res = meetingService.getAllMeetingsDto();

        if (filterDto.getDescription() != null)
            res = meetingService.filterByDescription(res, filterDto.getDescription());
        if (filterDto.getCategory() != CategoryDto.CATEGORY)
            res = meetingService.filterByCategory(res, filterDto.getCategory());
        if (filterDto.getType() != TypeDto.TYPE)
            res = meetingService.filterByType(res, filterDto.getType());
        if (filterDto.getStartDate() != null)
            res = meetingService.filterByStartDate(res, filterDto.getStartDate());
        if (filterDto.getEndDate() != null)
            res = meetingService.filterByEndDate(res, filterDto.getEndDate());
        if (filterDto.getNumberOfPersons() != 0)
            res = meetingService.filterByNumberOfPersons(res, filterDto.getNumberOfPersons());

        res.sort(Comparator.comparing(MeetingDto::getStartDate));
        model.addAttribute("allMeetings", res);
        model.addAttribute("filterDto", new FilterDto());
        return "list/list-all";
    }
}
