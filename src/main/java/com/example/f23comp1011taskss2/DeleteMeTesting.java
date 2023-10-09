package com.example.f23comp1011taskss2;

import java.util.ArrayList;
import java.util.Collections;

public class DeleteMeTesting {
    public static void main(String[] args) {
        ArrayList<Task> tasks = DBUtility.getTasksFromDB();
        System.out.println(tasks);
    }
}
