package com.example.planner_team;

import com.example.planner_team.AlreadyExistsException;
import com.example.planner_team.NotFoundException;

public interface ITask {
    String getName();
    String getDescription();
    Duration getExpectedDuration();

    Iterable<ITask> getSubTasks();
    void addSubTask(ITask t) throws AlreadyExistsException;
    void removeSubTasks(ITask t) throws NotFoundException;
}

public interface ISection {
    String getName();
    Iterable<Task> getTasks();
    void addTask(ITask t) throws AlreadyExistsException;
    void removeTask(ITask t) throws NotFoundException;
}

public interface IBoard {
    String getName();
    Iterable<ISection> getSections();
    void addSection(ISection t) throws AlreadyExistsException;
    void removeSection(ISection t) throws NotFoundException;
    ISection getSection(String sectionName)  throws NotFoundException;

}

public interface IProject {
    String getName();
    Iterable<ITask> getTasks();
    void addTask(ITask t) throws AlreadyExistsException;
    void removeTask(ITask t) throws NotFoundException;
}

public interface IPlanner {
    void addBoard(IBoard b) throws AlreadyExistsException;
    void addProject(IProject p) throws AlreadyExistsException;

    Iterable<IBoard> getBoards();
    Iterable<IProject> getProjects();

    public String writeXMLData();
    public void readXMLData(String data);
}