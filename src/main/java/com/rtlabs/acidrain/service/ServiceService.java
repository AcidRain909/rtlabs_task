package com.rtlabs.acidrain.service;

import com.rtlabs.acidrain.pojo.Service;

import java.util.ArrayList;

public interface ServiceService {
    ArrayList<Service> getAllServices();
    ArrayList<Service> getServicesByCode(int departmentCode);
}
