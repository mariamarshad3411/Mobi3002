package com.codelab.basics;

public class DataModel {
    public int number;
    public String name;
    public int powerLevel;
    public String description;
    public long accessCount;

    public DataModel(int number, String name, int powerLevel, String description, long accessCount) {
        this.number = number;
        this.name = name;
        this.powerLevel = powerLevel;
        this.description = description;
        this.accessCount = accessCount;
    }
}
