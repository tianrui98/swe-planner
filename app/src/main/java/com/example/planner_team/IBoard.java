import java.time.Duration;
import java.util.HashMap;
import AlreadyExistsException;
import NotFoundException;

public interface ITask {
    String getName();
    String getDescription();
    Duration getExpectedDuration();

    Iterable<ITask> getSubTasks();
    void addSubTask(ITask t) throws AlreadyExistsException;
    void removeSubTasks(ITask t) throws NotFoundException;
}

public interface ISection {
    String getName();
    Iterable<Task> getTasks();
    void addTask(ITask t) throws AlreadyExistsException;
    void removeTask(ITask t) throws NotFoundException;
}

public interface IBoard {
    String getName();
    Iterable<ISection> getSections();
    void addSection(ISection t) throws AlreadyExistsException;
    void removeSection(ISection t) throws NotFoundException;
    ISection getSection(String sectionName)  throws NotFoundException;

}

public class IBoard implements IBoard {
    private HashMap<String, ISection> sections = new HashMap<String, ISection>();
    private String name;

    public IBoard(){
        this.name = "";
    }

    public IBoard(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }

    Iterable<ISection> getSections(){
        return this.sections.values().iterator();
    }

    void addSection(ISection t) throws AlreadyExistsException {
        Section tmp = this.sections.get(t.getName());
        if (tmp != null) throw new AlreadyExistsException();
        this.sections.put(t.getName(), t);
    }

    void removeSection(ISection t) throws NotFoundException {
        Section tmp = this.sections.get(t.getName());
        if (tmp == null) throw new NotFoundException();
        this.sections.remove(t.getName());
    }
    ISection getSection(String sectionName)  throws NotFoundException {
        Section tmp = this.sections.get(t.getName());
        if (tmp == null) throw new NotFoundException();
        return tmp;
    }
}