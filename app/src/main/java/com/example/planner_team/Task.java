package com.example.planner_team;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Task {
    String name;
    String description;
    Duration duration;
    List<Task> subTasks = new ArrayList<>();;

    public Task(String name,String description){
        this.name = name;
        this.description = description;
        this.duration = Duration.ofHours(1);
    }

    public Task(String name,String description,Duration duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public void addSubTask(Task newSubTask){
        subTasks.add(newSubTask);
    }

    public void printSubTasks(){
        System.out.println("Sub Tasks are ");
        for (int counter = 0; counter < subTasks.size(); counter++) {
            System.out.println(subTasks.get(counter).name);
        }

    }

}
