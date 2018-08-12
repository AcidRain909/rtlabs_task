package com.rtlabs.acidrain.db.dao;

import com.rtlabs.acidrain.db.connectionManager.ConnectionManager;
import com.rtlabs.acidrain.db.connectionManager.ConnectionManagerImpl;
import com.rtlabs.acidrain.pojo.ApplicationView;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ApplicationViewDaoImpl implements AppicationViewDao{
    private static ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    @Override
    public ArrayList<ApplicationView> getAllApplicationView() {
        ArrayList<ApplicationView> applicationViewArrayList = new ArrayList();

        Connection connection = connectionManager.getConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement("SELECT application.id, created, concat(last_name, ' ', first_name, ' ', patronymic) as full_name, birth_date, concat(phone, ' / ', email) as contacts, service.name as service_name, department.name as department_name " +
                    "FROM application " +
                    "INNER JOIN declarer ON application.declarer_id = declarer.id " +
                    "INNER JOIN service ON application.service_id = service.id " +
                    "INNER JOIN department ON service.department_code = department.code " +
                    "ORDER BY application.id")) {

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        applicationViewArrayList.add(new ApplicationView(
                                resultSet.getInt("id"),
                                resultSet.getDate("created"),
                                resultSet.getString("full_name"),
                                resultSet.getDate("birth_date"),
                                resultSet.getString("contacts"),
                                resultSet.getString("service_name"),
                                resultSet.getString("department_name")
                        ));
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return applicationViewArrayList;
    }
}
