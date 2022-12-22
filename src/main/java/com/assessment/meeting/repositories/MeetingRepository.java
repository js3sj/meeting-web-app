package com.assessment.meeting.repositories;

import com.assessment.meeting.models.Meeting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Repository;

import java.io.Writer;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MeetingRepository {

    private final String PATH = "src/main/resources/";
    private final String FILE_NAME = "db.json";

    private final String DB_FILE_NAME = PATH + FILE_NAME;

    public MeetingRepository() {}

    public List<Meeting> read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(DB_FILE_NAME));
            var fileContent = gson.fromJson(reader, Meeting[].class);
            return (fileContent == null) ? new ArrayList<>() : Arrays.asList(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean savePretty(Meeting meeting) {
        List<Meeting> currentList = new ArrayList<>(read());
        currentList.add(meeting);
        savePretty(currentList);
        return true;
    }

    public boolean savePretty(List<Meeting> meetingList) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get(DB_FILE_NAME));
            gson.toJson(meetingList, writer);
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    * Below listed save() methods can be used instead of savePretty() in order to save space.
    * */
    public boolean save(Meeting meeting) {
        List<Meeting> currentList = new ArrayList<>(read());
        currentList.add(meeting);
        save(currentList);
        return true;
    }

    public boolean save(List<Meeting> meetingList) {
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get(DB_FILE_NAME));
            gson.toJson(meetingList, writer);
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    * Below listed toJson() and fromJsonToMeeting() methods can be used to play with Json's :)
    * */
    public String toJson(Meeting meeting) {
        try {
            return new Gson().toJson(meeting);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Meeting fromJsonToMeeting(String json) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, Meeting.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
