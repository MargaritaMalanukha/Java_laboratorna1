package org.margomalanuha.spring.labs.repository;

import lombok.SneakyThrows;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.repository.tools.Connector;
import org.margomalanuha.spring.labs.repository.tools.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends Repository {

    @SneakyThrows
    public int create(User user) {
        String sql = "insert into users(name, surname, email, password, user_type_id, is_active) values( '" + user.getName()
        + "' , '" + user.getSurname() + "' , '" + user.getEmail() + "' , '" + user.getPassword() + "' , " + user.getUserTypeId() + " , " + user.isActive() + " )";
        return jdbcTemplate.update(sql);
    }

    @SneakyThrows
    public int update(User user) {
        String sql = "update users set name='" + user.getName() +
                "', surname ='" + user.getSurname() +
                "', email= '" + user.getEmail() +
                "', password='" + user.getPassword() +
                "', user_type_id=" + user.getUserTypeId() +
                ", is_active=" + user.isActive() + " where id=" + user.getId();
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from users where id=" + id;
        return jdbcTemplate.update(sql);
    }

    public User getById(int id) {
        String sql = "select * from users where id=" + id;
        return jdbcTemplate.queryForObject(sql, User.class);
    }

    public List<User> getAll() {
        return jdbcTemplate.query("select * from users", (rs, row) -> {
            User e = new User();
            e.setId(rs.getInt(1));
            e.setName(rs.getString(2));
            e.setSurname(rs.getString(3));
            e.setEmail(rs.getString(4));
            e.setPassword(rs.getString(5));
            e.setUserTypeId(rs.getInt(6));
            e.setActive(rs.getBoolean(7));
            return e;
        });
    }
}
