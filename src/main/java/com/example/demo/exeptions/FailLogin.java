package com.example.demo.exeptions;

public class FailLogin extends Exception{
    @Override
    public String getMessage() {
        return "Wrong Login or Password";
    }
}
