package com.example.planner_team;

import java.time.Duration;

public interface ITask {
    String getName();
    String getDescription();
    Duration getExpectedDuration();

    Iterable<ITask> getSubTasks();
    void addSubTask(ITask t) throws AlreadyExistsException;
    void removeSubTasks(ITask t) throws NotFoundException;
}
