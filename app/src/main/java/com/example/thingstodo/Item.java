package com.example.thingstodo;

public class Item {
    private String id;
    private String listName;

    public Item(){

    }
    public Item(String id, String listName) {
        this.id = id;
        this.listName = listName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}