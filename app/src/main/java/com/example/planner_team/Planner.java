package com.example.planner_team;
import java.io.StringWriter;
import java.util.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Planner {
    private ArrayList<IBoard> boards = new ArrayList<IBoard>();
    private ArrayList<IProject> projects = new ArrayList<IProject>();

    private void addBoard(IBoard b) throws AlreadyExistsException {
        if (boards.contains(b)) throw new AlreadyExistsException();
        this.boards.add(b);
    }
    private void addProject(IProject p) throws AlreadyExistsException{
        if (projects.contains(p)) throw new AlreadyExistsException();
        this.projects.add(p);
    }

    private Iterable<IBoard> getBoards() throws NotFoundException{
        return boards;
    }
    private Iterable<IProject> getProjects() throws NotFoundException{
        return projects;
    }

    public String writeXMLData() throws ParserConfigurationException {

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("planner");
            Element boards = document.createElement("boards");
            Element projects = document.createElement("projects");

            for (IBoard b : this.boards) {
                Element newBoard = Board.createBoardElement(b, document);
                boards.appendChild(newBoard);
            }
            for (IProject p : this.projects) {
                Element newProject = Project.createProjectElement(p, document);
                boards.appendChild(newProject);
            }

            root.appendChild(boards);
            root.appendChild(projects);

            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return null;
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
            return null;
        }
    }


    public void readXMLData(String data) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
	try {
          // parse XML file
          DocumentBuilder db = dbf.newDocumentBuilder();
	  InputSource is = new InputSource();
	  is.setCharacterStream(new StringReader(data));
          Document doc = db.parse(data);

          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
          doc.getDocumentElement().normalize();
          NodeList boards = doc.getElementsByTagName("board");
          for (int i = 0; i < boards.getLength(); i++)
	      this.addBoard(Board.parseBoardElement(boards.get(i), doc));

	  NodeList projects = doc.getElementsByTagName("project");
          for (int i = 0; i < boards.getLength(); i++)
	      this.addBoard(Project.parseProjectElement(projects.get(i), doc));

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }
    }
}
