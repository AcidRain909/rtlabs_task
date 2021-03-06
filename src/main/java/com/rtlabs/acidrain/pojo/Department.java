package com.rtlabs.acidrain.pojo;

public class Department {
    private int id;
    private String name;
    private int code;

    public Department(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public Department(int id, String name, int code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
