package com.rtlabs.acidrain.db.dao;

import com.rtlabs.acidrain.db.connectionManager.ConnectionManager;
import com.rtlabs.acidrain.db.connectionManager.ConnectionManagerImpl;
import com.rtlabs.acidrain.pojo.Department;
import com.rtlabs.acidrain.pojo.Service;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ServiceDaoImpl implements ServiceDao{
    private static ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
    @Override
    public ArrayList<Service> getAllServices() {

            ArrayList<Service> serviceArrayList = new ArrayList();

            Connection connection = connectionManager.getConnection();
            try {
                try (PreparedStatement statement = connection.prepareStatement("SELECT * " +
                        "FROM service " +
                        "ORDER BY name")) {

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            serviceArrayList.add(new Service(
                                    resultSet.getString("name"),
                                    resultSet.getInt("department_code")
                            ));
                        }
                    }
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return serviceArrayList;

    }

    @Override
    public ArrayList<Service> getServicesByCode(int departmentCode) {
        ArrayList<Service> serviceArrayList = new ArrayList();

        Connection connection = connectionManager.getConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM service " +
                    "WHERE department_code = ? " +
                    "ORDER BY name")) {

                statement.setInt(1, departmentCode);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        serviceArrayList.add(new Service(
                                resultSet.getString("name"),
                                resultSet.getInt("department_code")
                        ));
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return serviceArrayList;
    }
}
