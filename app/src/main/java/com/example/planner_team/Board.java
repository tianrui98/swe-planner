package com.example.planner_team;

import java.time.Duration;
import java.util.HashMap;
import com.example.planner_team.AlreadyExistsException;
import com.example.planner_team.NotFoundException;
import com.example.planner_team.IBoard;


public class Board implements IBoard {
    private HashMap<String, ISection> sections = new HashMap<String, ISection>();
    private String name;

    public Board(){
        this.name = "";
    }

    public Board(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public Iterable<ISection> getSections(){
        return this.sections.values();
    }

    public void addSection(ISection t) throws AlreadyExistsException {
        ISection tmp = this.sections.get(t.getName());
        if (tmp != null) throw new AlreadyExistsException();
        this.sections.put(t.getName(), t);
    }

    public void removeSection(ISection t) throws NotFoundException {
        ISection tmp = this.sections.get(t.getName());
        if (tmp == null) throw new NotFoundException();
        this.sections.remove(t.getName());
    }
    public ISection getSection(String sectionName)  throws NotFoundException {
        ISection tmp = this.sections.get(sectionName);
        if (tmp == null) throw new NotFoundException();
        return tmp;
    }




}