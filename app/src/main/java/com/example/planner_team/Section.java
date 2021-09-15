package com.example.planner_team;

import java.util.ArrayList;
import java.util.List;

public class Section implements ISection{
    private String name;
    private List<Task> tasks;

    public Section(String name) {
        this.name = name;
        this.tasks = new ArrayList<Task>();
    }

    public String getName() {
        return this.name;
    }

    public Iterable<Task> getTasks() {
        return this.tasks;
    }

    private boolean isPresent(Task tFind) {
        for (Task t : this.tasks) {
            if (t == tFind) return true;
        }
        return false;
    }

    public void addTask(Task addT) throws AlreadyExistsException{
        if (this.isPresent(addT)) {
            throw new AlreadyExistsException();
        }
        this.tasks.add(addT);
    }

    public void removeTask(Task removeT) throws NotFoundException{
        if (!this.isPresent(removeT)) throw new NotFoundException();
        this.tasks.remove(removeT);
    }
}
