package com.codurance.training.tasks.handler;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.service.*;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskExecutionHandlerImpl implements TaskExecutionHandler{
    private static Map<String, List<Task>> tasks;

    private static long lastId;
    private SelectionService selectionService;

    private DisplayService displayService;

    private AddService addService;
    public TaskExecutionHandlerImpl(PrintWriter out){
        tasks = new LinkedHashMap<>();
        lastId = 0;
        this.selectionService = new SelectionServiceImpl(out, tasks);
        this.displayService = new DisplayServiceImpl(out, tasks);
        this.addService = new AddServiceImpl(out, tasks, lastId);
    }

    public void execute(String commandLine){

        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                this.displayService.show();
                break;
            case "add":
                this.addService.add(commandRest[1]);
                break;
            case "check":
                this.selectionService.check(commandRest[1]);
                break;
            case "uncheck":
                this.selectionService.uncheck(commandRest[1]);
                break;
            case "help":
                this.displayService.help();
                break;
            default:
                this.displayService.error(command);
                break;
        }
    }
}
