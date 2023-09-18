package com.example.f23comp1011taskss2;

import java.time.LocalDate;

public class Task {
    private int taskID;
    private String title, description;
    private LocalDate dueDate, creationDate;
    private int estimatedLengthInMin;
    private User user;
    private Status status;

    public Task(String title, String description, LocalDate dueDate, int estimatedLengthInMin, User user) {
        creationDate = LocalDate.now();
        status = Status.CREATED;
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        setEstimatedLengthInMin(estimatedLengthInMin);
        setUser(user);
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getEstimatedLengthInMin() {
        return estimatedLengthInMin;
    }

    public void setEstimatedLengthInMin(int estimatedLengthInMin) {
        this.estimatedLengthInMin = estimatedLengthInMin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
