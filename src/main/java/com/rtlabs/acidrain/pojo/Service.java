package com.rtlabs.acidrain.pojo;

public class Service {
    private int id;
    private String name;
    private int departmentCode;

    public Service(int id, String name, int departmentCode) {
        this.id = id;
        this.name = name;
        this.departmentCode = departmentCode;
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

    public int getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(int departmentCode) {
        this.departmentCode = departmentCode;
    }
}
