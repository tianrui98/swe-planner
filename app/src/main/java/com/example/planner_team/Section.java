package com.example.planner_team;

import java.util.ArrayList;
import java.util.List;
import com.example.planner_team.ISection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;

public class Section implements ISection {
    private String name;
    private List<ITask> tasks;

    public Section(String name) {
        this.name = name;
        this.tasks = new ArrayList<ITask>();
    }

    public String getName() {
        return this.name;
    }

    public Iterable<ITask> getTasks() {
        return this.tasks;
    }

    private boolean isPresent(ITask tFind) {
        for (ITask t : this.tasks) {
            if (t == tFind) return true;
        }
        return false;
    }

    public void addTask(ITask addT) throws AlreadyExistsException {
        if (this.isPresent(addT)) {
            throw new AlreadyExistsException();
        }
        this.tasks.add(addT);
    }

    public void removeTask(ITask removeT) throws NotFoundException {
        if (!this.isPresent(removeT)) throw new NotFoundException();
        this.tasks.remove(removeT);
    }

    public static Element createSectionElement(ISection s, Document document) {
        Element section = document.createElement("section");

        Attr name = document.createAttribute("name");
        name.setValue(s.getName());
        section.setAttributeNode(name);

        Iterable<ITask> tasks = s.getTasks();

        for (ITask t : tasks) {
            Element newTask = Task.createTaskElement(t, document);
            section.appendChild(newTask);
        }

        return section;
    }
}
