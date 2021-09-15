package com.example.planner_team;

//public interface IProject{
//    String getName();
//    Iterable<ITask> getTasks();
//    void addTask(ITask t) throws AlreadyExistsException;
//    void removeTask(ITask t) throws NotFoundException;
//}

public class Project implements IProject {
    private HashMap<String, ITask> tasks = new HashMap<String, ITask>();
    static String name;

    public static void setName(String name) {
        Project.name = name;
    }

    public static String getName() {
        return name;
    }

    public static Iterable<ITask> getTasks() {
        return this.tasks.values().iterator();
    }

    public static void addTask(ITask t) throws AlreadyExistsException {
        Task tmp = this.tasks.get(t.getName());
        if (tmp != null) throw new AlreadyExistsException();
        this.tasks.put(t.getName(), t);
    }

    public static void removeTask(ITask t) throws NotFoundException {
        Task tmp = this.tasks.get(t.getName());
        if (tmp == null) throws new NotFoundException();
        return tmp;
    }


}