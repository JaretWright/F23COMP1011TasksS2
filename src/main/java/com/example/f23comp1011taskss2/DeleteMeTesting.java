package com.example.f23comp1011taskss2;

import java.util.ArrayList;
import java.util.Collections;

public class DeleteMeTesting {
    public static void main(String[] args) {
        ArrayList<User> users = DBUtility.getUsersFromDB();
        Collections.sort(users);
        System.out.println(users);
    }
}
