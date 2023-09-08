package com.codurance.training.tasks.service;

import com.codurance.training.tasks.model.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class SelectionServiceImpl implements SelectionService{


    private final PrintWriter out;

    private final Map<String, List<Task>> tasks;

    public SelectionServiceImpl(PrintWriter out, Map<String, List<Task>> tasks){
        this.out = out;
        this.tasks = tasks;
    }


    public void check(String idString) {
        setDone(idString, true);
    }

    public void uncheck(String idString) {
        setDone(idString, false);
    }

    public void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}
