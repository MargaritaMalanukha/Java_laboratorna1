package org.margomalanuha.spring.labs.repository;

import org.margomalanuha.spring.labs.models.pojo.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BasketItemRepository extends JpaRepository<BasketItem, Integer> {
}
