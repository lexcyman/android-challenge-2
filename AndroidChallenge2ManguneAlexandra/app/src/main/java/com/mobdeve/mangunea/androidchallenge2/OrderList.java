package com.mobdeve.mangunea.androidchallenge2;

public class OrderList {
    private String name;
    private String date;
    private String id;
    private String item;
    private String price;

    public OrderList(String name, String date, String id, String item, String price) {
        this.name = name;
        this.date = date;
        this.id = id;
        this.item = item;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public String getPrice() {
        return price;
    }
}
