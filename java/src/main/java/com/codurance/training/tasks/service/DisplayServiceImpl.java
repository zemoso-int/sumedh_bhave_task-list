package com.codurance.training.tasks.service;

import com.codurance.training.tasks.model.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class DisplayServiceImpl implements DisplayService {

    private PrintWriter out;

    private Map<String, List<Task>> tasks;


    public DisplayServiceImpl(PrintWriter out, Map<String, List<Task>> tasks){
        this.out = out;
        this.tasks = tasks;
    }

    @Override
    public void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    @Override
    public void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    public void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

}
