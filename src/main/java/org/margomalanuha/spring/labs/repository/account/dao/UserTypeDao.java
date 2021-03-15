package org.margomalanuha.spring.labs.repository.account.dao;

import org.margomalanuha.spring.labs.repository.account.pojo.UserType;
import org.margomalanuha.spring.labs.repository.tools.Dao;
import java.util.List;

public class UserTypeDao extends Dao {

    public int create(UserType userType) {
        String sql = "insert into user_types(title) values(" + userType.getTitle() + ");";
        return jdbcTemplate.update(sql);
    }

    public int update(UserType userType) {
        String sql = "update user_types set title=" + userType.getTitle() + " where id=" + userType.getId() + ";";
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from user_types where id=" + id + ";";
        return jdbcTemplate.update(sql);
    }

    public UserType getById(int id) {
        String sql = "select * from user_types where id=" + id + ";";
        return jdbcTemplate.queryForObject(sql, UserType.class);
    }

    public List<UserType> getAll() {
        return jdbcTemplate.query("select * from user_types", (rs, row) -> {
            UserType e = new UserType();
            e.setId(rs.getInt(1));
            e.setTitle(rs.getString(2));
            return e;
        });
    }

}
