package com.example.planner_team;

public interface IPlanner {
    void addBoard(IBoard b) throws AlreadyExistsException;
    void addProject(IProject p) throws AlreadyExistsException;

    Iterable<IBoard> getBoards();
    Iterable<IProject> getProjects();

    public String writeXMLData();
    public void readXMLData(String data);
}
