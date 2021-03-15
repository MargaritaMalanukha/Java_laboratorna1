package org.margomalanuha.spring.labs.repository.purchase.dao;

import org.margomalanuha.spring.labs.repository.purchase.pojo.Purchase;
import org.margomalanuha.spring.labs.repository.tools.Dao;

import java.util.List;

public class PurchaseDao extends Dao {

    public int create(Purchase purchase) {
        String sql = "insert into purchases(user_id, price, time) values(" + purchase.getUserId()
                + "," + purchase.getPrice() + "," + purchase.getTime() + ");";
        return jdbcTemplate.update(sql);
    }

    public int update(Purchase purchase) {
        String sql = "update purchases set user_id=" + purchase.getUserId() + ", price=" + purchase.getPrice() +
                ", time=" + purchase.getTime() + " where id=" + purchase.getId() + ";";
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from purchases where id=" + id + ";";
        return jdbcTemplate.update(sql);
    }

    public Purchase getById(int id) {
        String sql = "select * from purchases where id=" + id + ";";
        return jdbcTemplate.queryForObject(sql, Purchase.class);
    }

    public List<Purchase> getAll() {
        return jdbcTemplate.query("select * from purchases", (rs, row) -> {
            Purchase e=new Purchase();
            e.setId(rs.getInt(1));
            e.setUserId(rs.getInt(2));
            e.setPrice(rs.getDouble(3));
            e.setTime(rs.getTimestamp(4));
            return e;
        });
    }

}
