package com.rtlabs.acidrain.service;

import com.rtlabs.acidrain.db.dao.ServiceDao;
import com.rtlabs.acidrain.pojo.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceDao serviceDao;

    @Override
    public ArrayList<Service> getAllServices() {
        return serviceDao.getAllServices();
    }

    @Override
    public ArrayList<Service> getServicesByCode(int departmentCode) {
        return serviceDao.getServicesByCode(departmentCode);
    }
}
