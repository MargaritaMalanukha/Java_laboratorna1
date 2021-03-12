package org.margomalanuha.spring.labs.data.purchase.dao;

import org.margomalanuha.spring.labs.data.purchase.pojo.Product;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProductDao {

    JdbcTemplate jdbcTemplate;

    public int create(Product product) {
        String sql = "insert into products(id, title, price, calalog_id)" +
                "values(" + product.getId() + "," + product.getTitle() + "," +
                product.getPrice() + "," + product.getCatalogId() + ");";

        return jdbcTemplate.update(sql);
    }

    public int update(Product product) {
        String sql = "update products set title=" + product.getTitle() +
                ", price=" + product.getPrice() + ", catalog_id=" + product.getCatalogId() + " where id=" + product.getId() + ";";
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from products where id=" + id + ";";
        return jdbcTemplate.update(sql);
    }

    public Product getById(int id) {
        String sql = "select * from products where id=" + id + ";";
        return jdbcTemplate.queryForObject(sql, Product.class);
    }

    public List<Product> getAll() {
        return jdbcTemplate.query("select * from products", (rs, row) -> {
            Product e=new Product();
            e.setId(rs.getInt(1));
            e.setTitle(rs.getString(2));
            e.setPrice(rs.getDouble(3));
            e.setCatalogId(rs.getInt(4));
            return e;
        });
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
