package org.margomalanuha.spring.labs.repository;

import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
