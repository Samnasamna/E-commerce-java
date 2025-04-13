package com.shamna.Aesthetic.Shop.Repositories;

import com.shamna.Aesthetic.Shop.Entities.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDataRepository extends JpaRepository<OrderData,Integer> {
    Optional<OrderData> findByUserId(String userId);
}
