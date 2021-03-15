package org.margomalanuha.spring.labs.repository.tools;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Dao {

    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;

    public Dao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Dao() {
        this.dataSource = new DriverManagerDataSource(Connector.URL, Connector.USERNAME, Connector.PASSWORD);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

}
