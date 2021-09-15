package com.example.planner_team;

import java.time.Duration;
import java.util.HashMap;
import com.example.planner_team.AlreadyExistsException;
import com.example.planner_team.NotFoundException;
import com.example.planner_team.Interfaces.IBoard;
import com.example.planner_team.Interfaces.ISection;
import com.example.planner_team.Interfaces.ITask;


public class Board implements IBoard {
    private HashMap<String, ISection> sections = new HashMap<String, ISection>();
    private String name;

    public Board(){
        this.name = "";
    }

    public Board(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }

    Iterable<ISection> getSections(){
        return this.sections.values().iterator();
    }

    void addSection(ISection t) throws AlreadyExistsException {
        Section tmp = this.sections.get(t.getName());
        if (tmp != null) throw new AlreadyExistsException();
        this.sections.put(t.getName(), t);
    }

    void removeSection(ISection t) throws NotFoundException {
        Section tmp = this.sections.get(t.getName());
        if (tmp == null) throw new NotFoundException();
        this.sections.remove(t.getName());
    }
    ISection getSection(String sectionName)  throws NotFoundException {
        Section tmp = this.sections.get(t.getName());
        if (tmp == null) throw new NotFoundException();
        return tmp;
    }
}