package org.margomalanuha.spring.labs.repository.account.dao;

import org.margomalanuha.spring.labs.repository.account.pojo.UserRight;
import org.margomalanuha.spring.labs.repository.tools.Dao;
import java.util.List;

public class UserRightDao extends Dao {

    public int create(UserRight userRight) {
        String sql = "insert into user_rights(right_id, user_type_id) values(" + userRight.getRightId()
                + "," + userRight.getUserTypeId() + ");";
        return jdbcTemplate.update(sql);
    }

    public int update(UserRight userRight) {
        String sql = "update user_rights set right_id=" + userRight.getRightId() + ", user_type_id=" + userRight.getUserTypeId()
                + " where id=" + userRight.getId() + ";";
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from user_rights where id=" + id + ";";
        return jdbcTemplate.update(sql);
    }

    public UserRight getById(int id) {
        String sql = "select * from user_rights where id=" + id + ";";
        return jdbcTemplate.queryForObject(sql, UserRight.class);
    }

    public List<UserRight> getAll() {
        return jdbcTemplate.query("select * from user_rights", (rs, row) -> {
            UserRight e = new UserRight();
            e.setId(rs.getInt(1));
            e.setRightId(rs.getInt(2));
            e.setUserTypeId(rs.getInt(3));
            return e;
        });
    }

}
