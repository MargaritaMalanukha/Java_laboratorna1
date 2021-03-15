package org.margomalanuha.spring.labs.repository.account.dao;

import org.margomalanuha.spring.labs.repository.account.pojo.Right;
import org.margomalanuha.spring.labs.repository.tools.Dao;
import java.util.List;

public class RightDao extends Dao {

    public int create(Right right) {
        String sql = "insert into rights(title) values(" + right.getTitle() + ");";
        return jdbcTemplate.update(sql);
    }

    public int update(Right right) {
        String sql = "update rights set title=" + right.getTitle() + " where id=" + right.getId() + ";";
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from rights where id=" + id + ";";
        return jdbcTemplate.update(sql);
    }

    public Right getById(int id) {
        String sql = "select * from rights where id=" + id + ";";
        return jdbcTemplate.queryForObject(sql, Right.class);
    }

    public List<Right> getAll() {
        return jdbcTemplate.query("select * from rights", (rs, row) -> {
            Right e = new Right();
            e.setId(rs.getInt(1));
            e.setTitle(rs.getString(2));
            return e;
        });
    }

}
