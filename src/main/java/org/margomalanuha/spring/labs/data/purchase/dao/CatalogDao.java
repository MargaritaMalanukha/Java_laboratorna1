package org.margomalanuha.spring.labs.data.purchase.dao;

import org.margomalanuha.spring.labs.data.purchase.pojo.Catalog;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CatalogDao {

    JdbcTemplate jdbcTemplate;

    public int create(Catalog catalog) {
        String sql = "insert into catalogs(id, title) values(" + catalog.getId() + "," + catalog.getTitle() + ");";
        return jdbcTemplate.update(sql);
    }

    public int update(Catalog catalog) {
        String sql = "update catalogs set title=" + catalog.getTitle() + " where id=" + catalog.getId() + ";";
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from catalogs where id=" + id + ";";
        return jdbcTemplate.update(sql);
    }

    public Catalog getById(int id) {
        String sql = "select * from catalogs where id=" + id + ";";
        return jdbcTemplate.queryForObject(sql, Catalog.class);
    }

    public List<Catalog> getAll() {
        return jdbcTemplate.query("select * from catalogs", (rs, row) -> {
            Catalog e=new Catalog();
            e.setId(rs.getInt(1));
            e.setTitle(rs.getString(2));
            return e;
        });
    }
}
