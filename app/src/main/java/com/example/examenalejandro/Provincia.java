package com.example.examenalejandro;

public class Provincia {
    private String name;
    private String img;
    private String location;
    private String web;

    public Provincia(String name, String img, String location, String web) {
        this.name = name;
        this.img = img;
        this.location = location;
        this.web = web;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getLocation() {
        return location;
    }

    public String getWeb() {
        return web;
    }
}
