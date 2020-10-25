package com.example.timer;

public class Timer {
    private int id;
    private String name;
    private int duration;

    public Timer(int id, String name, int duration){
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
    public Timer(String name, int duration){
        this.name = name;
        this.duration = duration;
    }
    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return name;
    }
}
