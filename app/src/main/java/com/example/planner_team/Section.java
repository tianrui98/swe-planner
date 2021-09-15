package com.example.planner_team;

import java.util.ArrayList;
import java.util.List;
import com.example.planner_team.ISection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;

public class Section implements ISection {
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

    public void addTask(Task addT) throws AlreadyExistsException {
        if (this.isPresent(addT)) {
            throw new AlreadyExistsException();
        }
        this.tasks.add(addT);
    }

    public void removeTask(Task removeT) throws NotFoundException {
        if (!this.isPresent(removeT)) throw new NotFoundException();
        this.tasks.remove(removeT);
    }

    public static Element createSectionElement(Section s, Document document) {
        Element section = document.createElement("section");

        Attr name = document.createAttribute("name");
        name.setValue(s.getName());
        section.setAttributeNode(name);

        Iterable<Task> tasks = s.getTasks();

        for (Task t : tasks) {
            Element newTask = t.createTaskElement();
            section.appendChild(newTask);
        }

        return section;
    }
}
