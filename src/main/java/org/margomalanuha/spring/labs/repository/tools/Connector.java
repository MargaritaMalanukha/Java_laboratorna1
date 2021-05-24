package org.margomalanuha.spring.labs.repository.tools;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connector {

    public static final String URL = "jdbc:mysql://localhost:3306/java_db";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "margomalanuha";
    public static final String TEST_URL = "jdbc:mysql://localhost:3306/test_java_db";

}
