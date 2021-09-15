package com.example.planner_team;

public interface ISection {
    String getName();
    Iterable<Task> getTasks();
    void addTask(Task t) throws AlreadyExistsException;
    void removeTask(Task t) throws NotFoundException;
}
