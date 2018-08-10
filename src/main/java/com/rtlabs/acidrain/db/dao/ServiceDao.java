package com.rtlabs.acidrain.db.dao;

import com.rtlabs.acidrain.pojo.Service;

import java.util.ArrayList;

public interface ServiceDao {
    ArrayList<Service> getAllServices();
    ArrayList<Service> getServicesByCode(int departmentCode);
}
