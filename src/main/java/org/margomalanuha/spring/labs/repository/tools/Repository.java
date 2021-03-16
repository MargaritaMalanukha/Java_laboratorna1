package org.margomalanuha.spring.labs.repository.tools;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Repository {

    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;
    protected Connection connection;
    protected PreparedStatement statement;

    public Repository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        setConnection();
    }

    public Repository() {
        this.dataSource = new DriverManagerDataSource(Connector.URL, Connector.USERNAME, Connector.PASSWORD);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        setConnection();
    }

    protected void setConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
