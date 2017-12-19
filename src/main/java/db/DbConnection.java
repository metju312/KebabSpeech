package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DbConnection {
    private static final Logger LOGGER = Logger.getLogger(DbConnection.class.getName());

    private Connection connection = null;

    public Connection getConnection() {
        LOGGER.info("Try get connection to deployed database");
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-221-207-143.compute-1.amazonaws.com:5432/d2gm2rcbfaia71?user=kzpqxcowscshbr&password=60c8ec5967e480de38d1fd6bdb883487ffad1d19cd2110dea6f2a07c22732ca5&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
                LOGGER.info("Connection to deployed database success!");
            } catch (SQLException e) {
                LOGGER.info("Connection to deployed database error!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
