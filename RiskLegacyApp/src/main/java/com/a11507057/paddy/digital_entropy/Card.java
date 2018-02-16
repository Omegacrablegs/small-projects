package com.a11507057.paddy.digital_entropy;

public class Card {
    private int height, width, value, ID;
    private String name;

    public Card (String name,int height,int width,int value, int ID){
        this.height = height;
        this.width = width;
        this.value = value;
        this.name = name;
        this.ID = ID;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getValue(){
        return value;
    }

    public int getID() {
        return ID;
    }

    public String getName(){
        return name;
    }

    public void setCount(int ID) {
        this.ID = ID;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
