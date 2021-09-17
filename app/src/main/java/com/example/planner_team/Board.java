package com.example.planner_team;

import java.time.Duration;
import java.util.HashMap;
import com.example.planner_team.AlreadyExistsException;
import com.example.planner_team.NotFoundException;
import com.example.planner_team.IBoard;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Board implements IBoard {
    private HashMap<String, ISection> sections = new HashMap<String, ISection>();
    private String name = "";

    public Board(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public Iterable<ISection> getSections(){
        return this.sections.values();
    }

    public void addSection(ISection t) throws AlreadyExistsException {
        ISection tmp = this.sections.get(t.getName());
        if (tmp != null) throw new AlreadyExistsException();
        this.sections.put(t.getName(), t);
    }

    public void removeSection(ISection t) throws NotFoundException {
        ISection tmp = this.sections.get(t.getName());
        if (tmp == null) throw new NotFoundException();
        this.sections.remove(t.getName());
    }
    public ISection getSection(String sectionName)  throws NotFoundException {
        ISection tmp = this.sections.get(sectionName);
        if (tmp == null) throw new NotFoundException();
        return tmp;
    }

    public static Element createBoardElement(IBoard b, Document document) {
        Element board = document.createElement("section");

        Attr name = document.createAttribute("name");
        name.setValue(b.getName());
        board.setAttributeNode(name);

        Iterable<ISection> sections = b.getSections();

        for (ISection s : sections) {
            Element newSection = Section.createSectionElement(s, document);
            board.appendChild(newSection);
        }

        return board;
    }

    public static Board parseBoardElement(Element e, Document document) {

	String name = e.getElementsByTagName("name").item(0).getTextContent();
	Board b = new Board(name);

	NodeList sections = e.getElementsByTagName("section");
	for (int i=0; i< sections.getLength(); i++)
	    b.addSection(Section.parseSectionElement((Element)sections.get(i),
						     document));
	
	return b;
    }

}
