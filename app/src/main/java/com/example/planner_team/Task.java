package com.example.planner_team;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Task implements ITask{
    String name;
    String description;
    Duration duration;
    ArrayList<ITask> subTasks = new ArrayList<>();;

    public Task(String name,String description, Duration duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Duration getExpectedDuration() {
        return this.duration;
    }

    @Override
    public Iterable<ITask> getSubTasks() {
        return this.subTasks;
    }

    public void addSubTask(ITask newSubTask){
        subTasks.add(newSubTask);
    }

    @Override
    public void removeSubTasks(ITask t) throws NotFoundException {
        if (!this.subTasks.contains(t)) {
            throw new NotFoundException();
        }
        else{
            this.subTasks.remove(t);
        }
    }

    public void printSubTasks(){
        System.out.println("Sub Tasks are ");
        for (int counter = 0; counter < subTasks.size(); counter++) {
            System.out.println(subTasks.get(counter).getName());
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

    public static Task createXMLTask(Element e, Document document){
        String name = e.getElementsByTagName("name").item(0).getTextContent();
        String description = e.getElementsByTagName("description").item(0).getTextContent();
        String duration = e.getElementsByTagName("name").item(0).getTextContent();
        Duration duration_obj = java.time.Duration.parse(duration);
        // Duration using ofHours() method
        NodeList tasks = e.getElementsByTagName("task");

        Task newTask = new Task(name,description,duration_obj);

        for(Integer i = 0;i< tasks.getLength();i++){
            Node subTask = tasks.item(i);
            Task newSubTask = Task.createXMLTask((Element) subTask,document);
            newTask.addSubTask(newSubTask);
        }
        return newTask;
    }



}
