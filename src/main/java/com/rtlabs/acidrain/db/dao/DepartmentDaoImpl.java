package com.rtlabs.acidrain.db.dao;

import com.rtlabs.acidrain.db.connectionManager.ConnectionManager;
import com.rtlabs.acidrain.db.connectionManager.ConnectionManagerImpl;
import com.rtlabs.acidrain.pojo.Department;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class DepartmentDaoImpl implements DepartmentDao {

    private static ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    @Override
    public ArrayList<Department> getAllDepartments() {
        ArrayList<Department> departmentArrayList = new ArrayList();

        Connection connection = connectionManager.getConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM department " +
                    "ORDER BY name")) {

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        departmentArrayList.add(new Department(
                                resultSet.getString("name"),
                                resultSet.getInt("code")
                        ));
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return departmentArrayList;
    }
}
