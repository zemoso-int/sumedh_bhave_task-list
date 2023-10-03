package com.codurance.training.tasks.service;

public interface SelectionService {

    void check(String idString);

    void uncheck(String idString);

    void setDone(String idString, boolean done);
}
