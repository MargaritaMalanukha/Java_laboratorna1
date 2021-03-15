package org.margomalanuha.spring.labs.repository.purchase.dao;

import org.margomalanuha.spring.labs.repository.purchase.pojo.BasketItem;
import org.margomalanuha.spring.labs.repository.tools.Dao;

import java.util.List;

public class BasketItemDao extends Dao {

    public int create(BasketItem basketItem) {
        String sql = "insert into basket_items(product_id, user_id) values(" + basketItem.getProductId()
                + "," + basketItem.getUserId() + ");";
        return jdbcTemplate.update(sql);
    }

    public int update(BasketItem basketItem) {
        String sql = "update basket_items set product_id=" + basketItem.getProductId() + ", user_id=" + basketItem.getUserId()
                + " where id=" + basketItem.getId() + ";";
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from basket_items where id=" + id + ";";
        return jdbcTemplate.update(sql);
    }

    public BasketItem getById(int id) {
        String sql = "select * from basket_items where id=" + id + ";";
        return jdbcTemplate.queryForObject(sql, BasketItem.class);
    }

    public List<BasketItem> getAll() {
        return jdbcTemplate.query("select * from basket_items", (rs, row) -> {
            BasketItem e = new BasketItem();
            e.setId(rs.getInt(1));
            e.setProductId(rs.getInt(2));
            e.setUserId(rs.getInt(3));
            return e;
        });
    }

}
