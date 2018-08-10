package com.rtlabs.acidrain.service;

import com.rtlabs.acidrain.db.dao.DepartmentDao;
import com.rtlabs.acidrain.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public ArrayList<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}
