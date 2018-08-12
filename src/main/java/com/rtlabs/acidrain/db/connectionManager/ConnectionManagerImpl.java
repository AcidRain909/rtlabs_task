package com.rtlabs.acidrain.db.connectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {
    private static ConnectionManager connectionManager;

    public ConnectionManagerImpl() {

    }

    public static ConnectionManager getInstance(){
        if (connectionManager == null){
            connectionManager = new ConnectionManagerImpl();
        }

        return connectionManager;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/rtlabs_db?useUnicode=true&characterEncoding=utf-8",
                    "postgres",
                    "nothing");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
