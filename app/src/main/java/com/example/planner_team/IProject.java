package com.example.planner_team;

public interface IProject {
    String getName();
    Iterable<ITask> getTasks();
    void addTask(ITask t) throws AlreadyExistsException;
    void removeTask(ITask t) throws NotFoundException;
}
