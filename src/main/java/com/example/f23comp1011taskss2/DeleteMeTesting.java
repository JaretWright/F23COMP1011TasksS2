package com.example.f23comp1011taskss2;

public class DeleteMeTesting {
    public static void main(String[] args) {
        User newUser = new User("frank@test.com","Frankie","7055551234");
        System.out.println(DBUtility.addUserToDB(newUser));
    }
}
