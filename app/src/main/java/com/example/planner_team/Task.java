package com.example.planner_team;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    public static void createChildTextNode(String label, String value, Element parent, Document document) {
        Element name = document.createElement(label);
        name.appendChild(document.createTextNode(value));
        parent.appendChild(name);
    }

    public static Element createTaskElement(ITask t, Document document) {
        Element e = document.createElement("task");
        createChildTextNode("name", t.getName(), e, document);
        createChildTextNode("description", t.getDescription(), e, document);
        createChildTextNode("duration", t.getExpectedDuration().toString(), e, document);

        Iterable<ITask> subTask = t.getSubTasks();
        for(ITask i : subTask){
            Element newTask = createTaskElement(i,document);
            e.appendChild(newTask);
        }
        return e;
    }

}
