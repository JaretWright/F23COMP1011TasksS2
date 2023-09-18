package com.example.f23comp1011taskss2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task;
    User fred;
    @BeforeEach
    void setUp() {
        fred = new User("fred@rocks.com","Freddie","7055551234");
        task = new Task("Do Quiz","See quiz in week 2",
                LocalDate.of(2023,9,23),15,fred);
    }

    @Test
    void setTaskID() {
        task.setTaskID(1);
        assertEquals(1,task.getTaskID());
    }
}