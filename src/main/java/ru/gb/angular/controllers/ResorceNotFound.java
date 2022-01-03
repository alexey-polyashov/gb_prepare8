package ru.gb.angular.controllers;

public class ResorceNotFound extends RuntimeException{

    public ResorceNotFound(String msg) {
        super(msg);
    }
}
