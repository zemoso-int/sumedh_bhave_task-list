package com.codurance.training.tasks;

import com.codurance.training.tasks.handler.TaskExecutionHandlerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";
    private BufferedReader in ;
    private final PrintWriter out;
    private final TaskExecutionHandlerImpl taskExecutionHandler;


    public TaskList(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
        this.taskExecutionHandler = new TaskExecutionHandlerImpl(out);
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            taskExecutionHandler.execute(command);
        }
    }


}
