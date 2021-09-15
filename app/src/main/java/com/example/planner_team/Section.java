package com.example.planner_team;

import java.util.ArrayList;
import java.util.List;
import com.example.planner_team.ISection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    public static void createSectionElement(Section s, Document document) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        Element section = document.createElement("section");

        Attr name = document.createAttribute("name");
        attr.setValue(s.getName());
        section.setAttributeNode(name);

        Iterable<ITask> tasks= s.getTasks();

        for (ITask i : tasks){
            Element newTask = i.createTaskElement();
            section.appendChild(newTask);
        }

        return section;
}
