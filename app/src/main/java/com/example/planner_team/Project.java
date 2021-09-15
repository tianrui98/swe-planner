package com.example.planner_team;
import java.util.*;

public class Project implements IProject {
    private ArrayList<ITask> tasks = new ArrayList<ITask>();
    static String name;

    public static void setName(String name) {
        Project.name = name;
    }

    public String getName() {
        return name;
    }

    public Iterable<ITask> getTasks() {
        return this.tasks;
    }

    public void addTask(ITask t) throws AlreadyExistsException {
        if (this.tasks.contains(t)) throw new AlreadyExistsException();
        this.tasks.add(t);
    }

    public void removeTask(ITask t) throws NotFoundException {

        if (!this.tasks.contains(t)) throw new NotFoundException();
        this.tasks.remove(t);
    }

}