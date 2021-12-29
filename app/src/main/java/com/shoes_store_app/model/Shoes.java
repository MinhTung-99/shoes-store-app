package com.shoes_store_app.model;

public class Shoes {

    private int typeId;
    private String image;
    private String name;
    private String price;
    private String date;

    public Shoes(int typeId, String name, String price, String date) {
        this.typeId = typeId;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
