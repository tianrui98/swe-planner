package com.example.planner_team;

public class Runner {
    public static void main(String[] args) {
        Task newTask = new Task("Eat Tomatoes","Eat a lot of tomatoes");
        System.out.print(newTask.name);
        System.out.print(newTask.description);

        Task newSubTask = new Task("Slice Tomatoes","Sub Task of Eating Tomatoes");
        newTask.addSubTask(newSubTask);
        newTask.printSubTasks();
    }
}
