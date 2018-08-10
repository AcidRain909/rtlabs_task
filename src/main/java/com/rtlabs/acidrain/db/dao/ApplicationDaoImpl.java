package com.rtlabs.acidrain.db.dao;

import com.rtlabs.acidrain.db.connectionManager.ConnectionManager;
import com.rtlabs.acidrain.db.connectionManager.ConnectionManagerImpl;
import com.rtlabs.acidrain.pojo.Application;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class ApplicationDaoImpl implements ApplicationDao {
    private static ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();


    @Override
    public void addApplication(Application application) {


        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO application (service_id, declarer_id, created) " +
                             "VALUES (?, ?, ?);")) {
            preparedStatement.setInt(1, application.getServiceId());
            preparedStatement.setInt(2, application.getDeclarerId());
            java.sql.Date creationDate = new java.sql.Date(application.getCreated().getTime());
            preparedStatement.setDate(3, creationDate);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
