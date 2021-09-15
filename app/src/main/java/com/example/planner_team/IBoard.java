package com.example.planner_team;

public interface IBoard {
    String getName();
    Iterable<ISection> getSections();
    void addSection(ISection t) throws AlreadyExistsException;
    void removeSection(ISection t) throws NotFoundException;
    ISection getSection(String sectionName)  throws NotFoundException;

}