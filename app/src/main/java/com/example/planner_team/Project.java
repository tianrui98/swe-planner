package com.example.planner_team;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    public static Element createProjectElement(IProject b, Document document) {
	Element project = document.createElement("project");

	Attr name = document.createAttribute("name");
	name.setValue(b.getName());
	project.setAttributeNode(name);

	Iterable<ITask> tasks = b.getTasks();

	for (ITask t : tasks) {
	    Element newSection = Task.createTaskElement(t, document);
	    project.appendChild(newSection);
	}

	return project;
    }

    public static Project parseProjectElement(Element e, Document document) {
	String name = e.getElementsByTagName("name").item(0).getTextContent();
	Project p = new Project(name);

	NodeList tasks = e.getElementsByTagName("task");
	for (int i=0; i< tasks.getLength(); i++)
	    p.addTask(Task.parseTaskElement((Element)tasks.get(i), document));
	
	return s;
    }
}
