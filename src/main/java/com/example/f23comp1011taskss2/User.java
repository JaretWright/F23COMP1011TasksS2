package com.example.f23comp1011taskss2;

import java.util.regex.Pattern;

public class User {
    private String email, userName, phone;

    /**
     * This is the constructor, it will be called whenever we create
     * an instance of a User
     * @param email -must match valid email syntax
     * @param userName - what ever the user wishes to be called
     * @param phone - must align to the North American dialling plan
     */
    public User(String email, String userName, String phone) {
        setEmail(email);
        setUserName(userName);
        setPhone(phone);
    }

    public String getEmail() {
        return email;
    }

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    public void setEmail(String email) {
        if (isEmailValid(email))
            this.email = email;
        else
            throw new IllegalArgumentException("email must match RFC822 pattern");
    }

    public String getUserName() {
        return userName;
    }

    /**
     * This set's the users name, it cannot be blank
     * @param userName - what ever the user wants it to be
     */
    public void setUserName(String userName) {
        userName = userName.trim();  //remove leading and trailing whitespaces
        if (userName.matches("[A-z1-9]+"))
            this.userName = userName;
        else
            throw new IllegalArgumentException("username must have at least 1 character and/ or number");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
