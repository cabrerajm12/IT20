/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication85;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Stack;
/**
 *
 * @author ITLAB2-PC10-STUDENT
 */
public class Todolistmanager {

    LinkedList<String> taskList = new LinkedList<>();
    LinkedList<String> completedTasks = new LinkedList<>(); 
    Stack<String> undo = new Stack<>();

    public static void main(String[] args) {
        
        Todolistmanager taskmanager = new Todolistmanager();
        String option;

        do {
            option = JOptionPane.showInputDialog(
                "Choose an Option:\n" +
                "1. Add Task\n" +
                "2. Mark Task Done\n" +
                "3. Undo\n" +
                "4. View To-Do-List\n" +
                "5. View Completed tasks \n" +
                "6. Exit"
            );

            if (option != null) {
                switch (option) {
                    case "1":
                        String task = JOptionPane.showInputDialog("Enter task to add:");
                        if (task != null && !task.trim().isEmpty()) {
                            taskmanager.addTask(task.trim());
                        }
                        break;
                    case "2":
                        String done = JOptionPane.showInputDialog("Enter task number to mark as done:");
                        if (done != null) {
                            try {
                                int number = Integer.parseInt(done) - 1;
                                taskmanager.markTaskDone(number);
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Invalid task number.");
                            }
                        }
                        break;
                    case "3":
                        taskmanager.undo();
                        break;
                    case "4":
                        taskmanager.viewToDoList();
                        break;
                    case "5": 
                        taskmanager.viewCompletedTasks(); 
                        break;
                    case "6":
                        JOptionPane.showMessageDialog(null, "Exiting todo list manager.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option.");
                }
            }

        } while (option != null && !option.equals("6"));
    }

    public void addTask(String task) {
        taskList.add(task);
        JOptionPane.showMessageDialog(null, "Added task: " + task);
    }

    public void markTaskDone(int number) {
        if (number >= 0 && number < taskList.size()) {
            String task = taskList.get(number);
            undo.push(task); 
            taskList.remove(number); 
            completedTasks.add(task + " [Done]"); 
            JOptionPane.showMessageDialog(null, "Marked task as done: " + task);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    public void undo() {
        if (!undo.isEmpty()) {
            String task = undo.pop();
            taskList.add(task);
            completedTasks.remove(task + " [Done]"); 
            JOptionPane.showMessageDialog(null, "Undid task: " + task);
        } else {
            JOptionPane.showMessageDialog(null, "No task to undo.");
        }
    }

    
    public void viewCompletedTasks() {
        String completedTasksList = "";
        for (String task : completedTasks) {
            completedTasksList += task + "\n";  
        }
        JOptionPane.showMessageDialog(null, completedTasksList.length() > 0 ? completedTasksList : "No completed tasks.");
    }

    public void viewToDoList() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += (i + 1) + ": " + taskList.get(i) + "\n";  
        }
        JOptionPane.showMessageDialog(null, tasks.length() > 0 ? tasks : "No tasks available.");
    }
}


                        