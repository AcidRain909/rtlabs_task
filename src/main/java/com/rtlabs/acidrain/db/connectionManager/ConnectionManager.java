package com.rtlabs.acidrain.db.connectionManager;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}
