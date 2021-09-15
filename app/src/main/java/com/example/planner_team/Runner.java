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
	Board b = new Board("Test Board 1");
	System.out.print("Board name: ");
	System.out.println(b.getName());
	b.addSection(newSection);
	System.out.print("After 1 sections added: ");
	System.out.println(b.getSections().toString());
	assert b.getSections().size() == 1;
	try {
	    b.addSection(newSection);
	} catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot add 2 of the same section");
        } 
        Section s2 = new Section("Test Section 2");
	b.addSection(s2);
	System.out.print("After 2 sections added: ");
	System.out.println(b.getSections().toString());
	assert b.getSections().size() == 2;

	System.out.print("Queried Greens: ");
	System.out.println(b.getSection("Greens").getName());
	System.out.print("After query: ");
	System.out.println(b.getSections().toString());
	assert b.getSections().size() == 2;

	try {
	    System.out.print("Queried Test Section 3: ");
	    System.out.println(b.getSection("Test Section 3").getName());
	} catch (Exception e) {
		System.out.print("After failed query: ");
		System.out.println(b.getSections().toString());
	}
	assert b.getSections().size() == 2;

	try {
	    System.out.print("Remove Test Section 3: ");
	    b.removeSection(new Section("Test Section 3"));
	} catch (Exception e) {
		System.out.print("After failed remove: ");
		System.out.println(b.getSections().toString());
	}
	assert b.getSections().size() == 2;

	System.out.print("Remove Test Section 2: ");
	b.removeSection(new Section("Test Section 2"));
	System.out.print("After remove: ");
	System.out.println(b.getSections().toString());
	assert b.getSections().size() == 1;
	
        /* test print XML */

    }
}
