package com.example.planner_team;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import static java.time.temporal.ChronoUnit.HOURS;
public class Runner {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {

        Task newTask = new Task("Eat Tomatoes","Eat a lot of tomatoes", Duration.of(2, HOURS));
        System.out.print(newTask.name);
        System.out.print(newTask.description);

        Task newSubTask = new Task("Slice Tomatoes","Sub Task of Eating Tomatoes", Duration.of(2, HOURS));
        newTask.addSubTask(newSubTask);
        newTask.printSubTasks();

        /* Tests for Section */
        Section newSection = new Section("Greens");
        /* Test for non existent Task */
        try {
            newSection.removeTask(newTask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error Message caught");
        }

        /* Test for task that already exists */
        try {
            newSection.addTask(newTask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            newSection.addTask(newTask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot add 2 of the same task");
        }

        /* test Board */
        
        /* test print XML */

    }
}
