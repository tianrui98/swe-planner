package com.example.planner_team;
import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Planner {
    private HashMap<String, IBoard> boards = new HashMap<String, IBoard>();
    private HashMap<String, IProject> projects = new HashMap<String, IProject>();

    private void addBoard(IBoard b) throws AlreadyExistsException{
        IBoard tmp = this.boards.get(b.getName());
        if (tmp != null) throw new AlreadyExistsException();
        this.boards.put(b.getName(), b);
    }
    private void addProject(IProject p) throws AlreadyExistsException{
        IProject tmp = this.projects.get(p.getName());
        if (tmp != null) throw new AlreadyExistsException();
        this.projects.put(p.getName(), p);
    }

    private Iterable<IBoard> getBoards() throws NotFoundException{
        return boards.values();
    }
    private Iterable<IProject> getProjects() throws NotFoundException{
        return projects.values();
    }

    public String writeXMLData(){
        try {

        }
    }
}
