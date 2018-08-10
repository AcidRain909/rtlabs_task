package com.rtlabs.acidrain.db.dao;

import com.rtlabs.acidrain.db.connectionManager.ConnectionManager;
import com.rtlabs.acidrain.db.connectionManager.ConnectionManagerImpl;
import com.rtlabs.acidrain.pojo.Declarer;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class DeclarerDaoImpl implements DeclarerDao {
    private static ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    @Override
    public void addDeclarer(Declarer declarer) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO declarer (last_name, first_name, patronymic, phone, email, birth_date) " +
                             "VALUES (?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setString(1, declarer.getLastName());
            preparedStatement.setString(2, declarer.getFirstName());
            preparedStatement.setString(3, declarer.getPatronymic());
            preparedStatement.setString(4, declarer.getPhone());
            preparedStatement.setString(5, declarer.getEmail());
            preparedStatement.setDate(6, new java.sql.Date(declarer.getBirthDate().getTime()));

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


