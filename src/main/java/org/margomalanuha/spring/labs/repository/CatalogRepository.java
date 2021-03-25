package org.margomalanuha.spring.labs.repository;

import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.repository.tools.Repository;
import java.util.List;

public class CatalogRepository extends Repository {

    public int create(Catalog catalog) {
        String sql = "insert into catalogs(title) values('" + catalog.getTitle() + "', " + catalog.getUpperCatalogId() +")";
        return jdbcTemplate.update(sql);
    }

    public int update(Catalog catalog) {
        String sql = "update catalogs set title='" + catalog.getTitle() + "', set catalog_id='" + catalog.getUpperCatalogId() +
                "' where id=" + catalog.getId();
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from catalogs where id=" + id;
        return jdbcTemplate.update(sql);
    }

    public Catalog getById(int id) {
        String sql = "select * from catalogs where id=" + id;
        return jdbcTemplate.queryForObject(sql, Catalog.class);
    }

    public List<Catalog> getAll() {
        return jdbcTemplate.query("select * from catalogs", (rs, row) -> {
            Catalog e=new Catalog();
            e.setId(rs.getInt(1));
            e.setTitle(rs.getString(2));
            e.setUpperCatalogId(rs.getInt(3));
            return e;
        });
    }
}
