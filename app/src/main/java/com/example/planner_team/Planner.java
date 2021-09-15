package com.example.planner_team;
import java.util.*;

public class Planner {
    private HashMap<String, IBoard> boards = new HashMap<String, IBoard>();
    private HashMap<String, IProject> projects = new HashMap<String, IProject>();

    private void addBoard(IBoard b) throws AlreadyExistsException{
        IBoard tmp = this.boards.get(b.getName());
        if (tmp != null) throw new AlreadyExistsException();
        this.boards.put(b.getName(), b);
    }

}
